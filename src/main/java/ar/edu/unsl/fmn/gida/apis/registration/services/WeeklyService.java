package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.WeeklyRepository;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.WeeklyValidator;

@Service
@Transactional
public class WeeklyService {

	@Autowired
	private WeeklyRepository repository;

	@Autowired
	private ResponsibilityService responsibilityService;

	private final WeeklyValidator validator = new WeeklyValidator(new CustomExpressionValidator(),
			RegistrationSystemApplication.MESSENGER.getWeeklyValidationMessenger());

	public Weekly getOne(int id) {
		return this.repository.findByIdAndActiveTrue(id)
				.orElseThrow(
						() -> new ErrorResponse(
								RegistrationSystemApplication.MESSENGER.getWeeklyServiceMessenger()
										.notFound(Weekly.class.getSimpleName(), id),
								HttpStatus.NOT_FOUND));
	}

	public Weekly getCurrentWeeklyFromPerson(Integer personId) {
		Date currentDate = new Date();

		Weekly ret = this.repository
				.findByPersonIdAndActiveTrueAndStartLessThanEqualAndEndGreaterThanEqual(personId,
						currentDate, currentDate)
				.orElse(null);

		return ret;
	}

	public Weekly getCurrentWeeklyFromPersonWithResponsibilities(Integer personId) {
		Weekly ret = null;
		Date currentDate = new Date();
		Weekly weekly = this.repository
				.findByPersonIdAndActiveTrueAndStartLessThanEqualAndEndGreaterThanEqual(personId,
						currentDate, currentDate)
				.orElse(null);

		if (weekly != null) {
			ret = new Weekly();
			ret.setId(weekly.getId());
			ret.setPersonId(weekly.getPersonId());
			ret.setStart(weekly.getStart());
			ret.setEnd(weekly.getEnd());
			ret.setResponsibilities(this.responsibilityService.getAllByWeeklyId(ret.getId()));
		}

		return ret;
	}

	public Page<Weekly> getAll(int page, int quantity) {
		return this.repository.findAllByActiveTrue(PageRequest.of(page, quantity));
	}

	public Page<Weekly> getAllFromPersonWithResponsibilities(int personId, int page, int quantity) {
		// this.repository.findAllByPersonIdAndActiveTrue(personId, PageRequest.of(page, quantity));
		return this.repository.findAllByPersonIdAndActiveTrue(personId,
				PageRequest.of(page, quantity));
	}

	public Weekly initPersonWeekly(Integer personId, Weekly requestBody) {
		requestBody.setPersonId(personId);
		this.validator.validateInsert(requestBody);

		if (requestBody.getStart().compareTo(new Date()) <= 0)
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getWeeklyServiceMessenger().wrongWeeklyStartDate(),
					HttpStatus.UNPROCESSABLE_ENTITY);

		return requestBody;
	}

	public Weekly insert(Integer personId, Weekly requestBody) {
		requestBody.setPersonId(personId);
		this.validator.validateInsert(requestBody);

		// // check if start date is ok for the new weekly
		// if (requestBody.getStart().compareTo(new Date()) < 0)
		// throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
		// .getWeeklyServiceMessenger().wrongWeeklyDatetime(),
		// HttpStatus.UNPROCESSABLE_ENTITY);

		Date currentDate = new Date();
		Weekly currentWeekly = this.repository
				.findByPersonIdAndActiveTrueAndStartLessThanEqualAndEndGreaterThanEqual(personId,
						currentDate, currentDate)
				.orElse(null);

		if (currentWeekly != null) {
			if (requestBody.getStart().compareTo(new Date()) < 0
					|| requestBody.getStart().compareTo(currentWeekly.getStart()) <= 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getWeeklyServiceMessenger().wrongWeeklyStartDate(),
						HttpStatus.UNPROCESSABLE_ENTITY);

			Weekly ret = this.repository.save(requestBody);

			this.responsibilityService.insertAll(ret.getId(), requestBody.getResponsibilities());
		}

		return requestBody;
	}

	public Weekly closeWeekly(Integer weeklyId, Date end) {
		Weekly ret = this.repository.findByIdAndActiveTrue(weeklyId)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getWeeklyServiceMessenger()
								.updateNonExistentEntity(Weekly.class.getSimpleName(), weeklyId),
						HttpStatus.UNPROCESSABLE_ENTITY));

		ret.setEnd(end);

		return ret;
	}

	public Weekly closeWeeklyToday(Integer weeklyId) {
		Weekly ret = this.repository.findByIdAndActiveTrue(weeklyId)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getWeeklyServiceMessenger()
								.updateNonExistentEntity(Weekly.class.getSimpleName(), weeklyId),
						HttpStatus.UNPROCESSABLE_ENTITY));

		ret.setEnd(new Date());

		return ret;
	}

	public void delete(int personId) {
		List<Weekly> weeklies = this.repository.findAllByPersonIdAndActiveTrue(personId);

		if (weeklies.size() == 0)
			throw new ErrorResponse(
					RegistrationSystemApplication.MESSENGER.getCredentialServiceMessenger()
							.deleteNonExistentEntityCorruptDB(Weekly.class.getSimpleName(),
									Person.class.getSimpleName(), personId),
					HttpStatus.NOT_FOUND);

		for (int i = 0; i < weeklies.size(); i++) {
			weeklies.get(i).setActive(false);
			this.responsibilityService.deleteAllFromWeekly(weeklies.get(i).getId());
		}
	}
}
