package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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

		return body;
	}

	public List<Responsibility> insertAll(Integer weeklyId, Iterable<Responsibility> iterable) {
		for (Responsibility r : iterable) {
			r.setWeeklyId(weeklyId);
			this.validator.validateInsert(r);
		}

		this.repository.saveAll(iterable);

		return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
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
