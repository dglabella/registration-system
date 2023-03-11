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
		return this.repository.findByPersonIdAndEndIsNullAndActiveTrue(personId)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getWeeklyServiceMessenger()
								.currentWeeklyNotFound(personId),
						HttpStatus.INTERNAL_SERVER_ERROR));
	}

	public Weekly getCurrentWeeklyFromPersonWithResponsibilities(Integer personId) {
		Weekly ret = this.repository.findByPersonIdAndEndIsNullAndActiveTrue(personId)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getWeeklyServiceMessenger()
								.currentWeeklyNotFound(personId),
						HttpStatus.INTERNAL_SERVER_ERROR));

		ret.setResponsibilities(this.responsibilityService.getAllByWeeklyId(ret.getId()));
		return ret;
	}

	public Page<Weekly> getAll(int page, int quantity) {
		return this.repository.findAllByActiveTrue(PageRequest.of(page, quantity));
	}

	public Page<Weekly> getAllFromPerson(int personId, int page, int quantity) {
		return this.repository.findAllByPersonIdAndActiveTrue(personId,
				PageRequest.of(page, quantity));
	}

	public Weekly insert(Integer personId, Weekly requestBody) {
		requestBody.setPersonId(personId);
		this.validator.validateInsert(requestBody);

		// check if start date is ok for the new weekly
		if (requestBody.getStart().compareTo(new Date()) < 0)
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getWeeklyServiceMessenger().wrongWeeklyDatetime(),
					HttpStatus.UNPROCESSABLE_ENTITY);

		Weekly ret = this.repository.save(requestBody);

		this.responsibilityService.insertAll(ret.getId(), requestBody.getResponsibilities());

		return requestBody;
	}

	public Weekly update(Integer personId, Weekly requestBody) {
		this.validator.validateUpdate(requestBody);

		Weekly ret = requestBody;

		// check if start date is ok for the new weekly
		if (requestBody.getStart().compareTo(new Date()) < 0)
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getWeeklyServiceMessenger().wrongWeeklyDatetime(),
					HttpStatus.UNPROCESSABLE_ENTITY);

		Weekly weekly = this.repository.findByPersonIdAndEndIsNullAndActiveTrue(personId)
				.orElseThrow(() -> new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getWeeklyServiceMessenger().currentWeeklyNotFound(personId),
						HttpStatus.NOT_FOUND));

		if (!weekly.equals(requestBody)) {

			weekly.setEnd(new Date());

			ret = this.repository.save(requestBody);
			this.responsibilityService.insertAll(ret.getId(), requestBody.getResponsibilities());
		}

		return requestBody;
	}

	public void delete(int personId) {
		List<Weekly> weeklies = this.repository.findAllByPersonIdAndActiveTrue(personId);

		if (weeklies.size() > 0) {
			for (int i = 0; i < weeklies.size(); i++)
				weeklies.get(i).setActive(false);
		} else {
			throw new ErrorResponse(
					RegistrationSystemApplication.MESSENGER.getCredentialServiceMessenger()
							.deleteNonExistentEntityCorruptDB(Weekly.class.getSimpleName(),
									Person.class.getSimpleName(), personId),
					HttpStatus.NOT_FOUND);
		}
	}
}
