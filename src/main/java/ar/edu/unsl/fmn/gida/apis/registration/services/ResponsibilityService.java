package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Responsibility;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.ResponsibilityRepository;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.ResponsibilityValidator;

@Service
@Transactional
public class ResponsibilityService {

	@Autowired
	private ResponsibilityRepository repository;

	private final ResponsibilityValidator validator =
			new ResponsibilityValidator(new CustomExpressionValidator(),
					RegistrationSystemApplication.MESSENGER.getResponsibilityValidationMessenger());

	public Responsibility getOne(int id) {
		return this.repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getPersonServiceMessenger()
								.notFound(Responsibility.class.getSimpleName(), id),
						HttpStatus.NOT_FOUND));
	}

	public List<Responsibility> getAllByWeeklyId(Integer weeklyId) {
		return this.repository.findAllByWeeklyIdAndActiveTrue(weeklyId);
	}

	public Responsibility insert(Integer weeklyId, Responsibility body) {
		body.setWeeklyId(weeklyId);
		this.validator.validateInsert(body);

		if (body.getEntranceTime().compareTo(body.getDepartureTime()) > 0)
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getResponsibilityServiceMessenger().crossTimes(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		List<Responsibility> responsibilities =
				this.repository.findAllByWeeklyIdAndDayAndActiveTrue(weeklyId, body.getDay());

		if (responsibilities.size() > 0) {
			for (Responsibility r : responsibilities) {
				if (r.getEntranceTime().compareTo(body.getEntranceTime()) <= 0
						&& body.getEntranceTime().compareTo(r.getDepartureTime()) < 0)
					throw new ErrorResponse(
							RegistrationSystemApplication.MESSENGER
									.getResponsibilityServiceMessenger().overlappedTimes(),
							HttpStatus.CONFLICT);
			}
		}

		this.repository.save(body);

		return body;
	}

	public List<Responsibility> deleteAllFromWeekly(Integer weeklyId) {
		List<Responsibility> ret = this.repository.findAllByWeeklyIdAndActiveTrue(weeklyId);
		if (ret.size() == 0)
			throw new ErrorResponse(
					RegistrationSystemApplication.MESSENGER.getResponsibilityServiceMessenger()
							.deleteNonExistentEntityCorruptDB(Weekly.class.getSimpleName(),
									Responsibility.class.getSimpleName(), weeklyId),
					HttpStatus.NOT_FOUND);

		for (Responsibility r : ret)
			r.setActive(false);

		return ret;
	}
}
