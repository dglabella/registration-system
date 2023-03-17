package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.WeeklyRepository;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.WeeklyValidator;

@Service
@Transactional
public class WeeklyService {

	@PersistenceContext
	private EntityManager entityManager;

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

	public Weekly getOneWithResponsibilities(int id) {
		Weekly ret = null;
		Optional<Weekly> optional = this.repository.findByIdAndActiveTrue(id);

		if (optional.isPresent()) {
			ret = optional.get();
			ret.setResponsibilities(this.responsibilityService.getAllByWeeklyId(id));

		} else {
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getWeeklyServiceMessenger().notFound(Weekly.class.getSimpleName(), id),
					HttpStatus.NOT_FOUND);
		}

		return ret;
	}

	public Weekly getCurrentWeeklyFromPerson(Integer personId) {
		Date currentDate = new Date();

		return this.repository
				.findByPersonIdAndActiveTrueAndStartLessThanEqualAndEndGreaterThanEqual(personId,
						currentDate, currentDate)
				.orElse(null);
	}

	public Weekly getCurrentWeeklyFromPersonWithResponsibilities(Integer personId) {
		Weekly ret = null;
		Date currentDate = new Date();
		Optional<Weekly> optional = this.repository
				.findByPersonIdAndActiveTrueAndStartLessThanEqualAndEndGreaterThanEqual(personId,
						currentDate, currentDate);

		if (optional.isPresent()) {
			ret = optional.get();
			ret.setResponsibilities(this.responsibilityService.getAllByWeeklyId(ret.getId()));
		}

		return ret;
	}

	public Page<Weekly> getAllFromPerson(int personId, int page, int quantity) {
		return this.repository.findAllByPersonIdAndActiveTrue(personId,
				PageRequest.of(page, quantity));
	}

	public Page<Weekly> getAllFromPersonWithResponsibilities(int personId, int page, int quantity) {

		Page<Weekly> weekliesPage = this.repository.findAllByPersonIdAndActiveTrue(personId,
				PageRequest.of(page, quantity));

		for (Weekly weekly : weekliesPage.getContent()) {
			weekly.setResponsibilities(this.responsibilityService.getAllByWeeklyId(weekly.getId()));
		}

		return weekliesPage;
	}

	public Weekly insert(Integer personId, Weekly requestBody) {
		requestBody.setPersonId(personId);
		this.validator.validateInsert(requestBody);

		if (requestBody.getEnd() != null
				&& requestBody.getStart().compareTo(requestBody.getEnd()) >= 0)
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getWeeklyServiceMessenger().crossDates(), HttpStatus.UNPROCESSABLE_ENTITY);

		Date currentDate = new Date();
		Optional<Weekly> optional = this.repository
				.findByPersonIdAndActiveTrueAndStartLessThanEqualAndEndGreaterThanEqual(personId,
						currentDate, currentDate);

		if (optional.isPresent()) {
			Weekly currentWeekly = optional.get();
			if (requestBody.getStart().compareTo(currentWeekly.getStart()) <= 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getWeeklyServiceMessenger().wrongWeeklyStartDates(),
						HttpStatus.UNPROCESSABLE_ENTITY);

			if (currentWeekly.getEnd() != null
					&& requestBody.getStart().compareTo(currentWeekly.getEnd()) != 0) {
				throw new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getWeeklyServiceMessenger()
								.startDateNotqualToCurrentWeeklyEndDate(),
						HttpStatus.UNPROCESSABLE_ENTITY);
			} else {
				currentWeekly.setEnd(requestBody.getStart());
			}

		}

		Weekly ret = this.repository.save(requestBody);
		this.responsibilityService.insertAll(ret.getId(), requestBody.getResponsibilities());

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

		if (weeklies.size() > 0)
			for (int i = 0; i < weeklies.size(); i++) {
				weeklies.get(i).setActive(false);
				this.responsibilityService.deleteAllFromWeekly(weeklies.get(i).getId());
			}

	}
}
