package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.enums.WorkAttendanceState;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;
import ar.edu.unsl.fmn.gida.apis.registration.model.Responsibility;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.model.WorkAttendance;
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

	@Autowired
	private WorkAttendanceService workAttendanceService;

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

	// public Weekly getCurrentWeeklyFromPerson(Integer personId) {
	// LocalDate currentDate = LocalDate.now();

	// return this.repository
	// .findByPersonIdAndActiveTrueAndStartLessThanEqualAndEndGreaterThanEqual(personId,
	// currentDate, currentDate)
	// .orElse(null);
	// }

	// public Weekly getCurrentWeeklyFromPersonWithResponsibilities(Integer personId) {
	// Weekly ret = null;
	// LocalDate currentDate = LocalDate.now();
	// Optional<Weekly> optional = this.repository
	// .findByPersonIdAndActiveTrueAndStartLessThanEqualAndEndGreaterThanEqual(personId,
	// currentDate, currentDate);

	// if (optional.isPresent()) {
	// ret = optional.get();
	// ret.setResponsibilities(this.responsibilityService.getAllByWeeklyId(ret.getId()));
	// }

	// return ret;
	// }

	public Weekly getOneFromPersonContainingDate(int personId, LocalDate now) {
		return this.repository
				.findByPersonIdAndActiveTrueAndStartLessThanEqualAndEndGreaterThanEqual(personId,
						now, now)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getWeeklyServiceMessenger()
								.notFoundByPersonIdAndContainingDate(personId, now.toString()),
						HttpStatus.NOT_FOUND));
	}

	public Weekly getWeeklyWithResponsibilitiesFromPersonContainingDate(int personId,
			LocalDate localDate) {
		Weekly ret = null;
		Optional<Weekly> optional = this.repository
				.findByPersonIdAndActiveTrueAndStartLessThanEqualAndEndGreaterThanEqual(personId,
						localDate, localDate);

		if (optional.isPresent()) {
			ret = optional.get();
			ret.setResponsibilities(this.responsibilityService.getAllByWeeklyId(ret.getId()));
		}

		return ret;
	}

	public List<Weekly> getAllFromPerson(int personId) {
		return this.repository.findAllByPersonIdAndActiveTrue(personId);
	}

	public Page<Weekly> getAllFromPerson(int personId, int page, int quantity) {
		return this.repository.findAllByPersonIdAndActiveTrue(personId,
				PageRequest.of(page, quantity));
	}

	public Page<Weekly> getAllFromPersonEachWithResponsibilities(int personId, int page,
			int quantity) {

		Page<Weekly> weekliesPage = this.repository.findAllByPersonIdAndActiveTrue(personId,
				PageRequest.of(page, quantity));

		for (Weekly weekly : weekliesPage.getContent()) {
			weekly.setResponsibilities(this.responsibilityService.getAllByWeeklyId(weekly.getId()));
		}

		return weekliesPage;
	}

	public List<Weekly> getAllFromPersonEachWithWorkAttendancesBetweenDates(Integer personId,
			LocalDate from, LocalDate to) {

		List<Weekly> weeklies = new ArrayList<>();

		// get a weekly that 'from date' is in [start, end]
		Optional<Weekly> optionalWeekly = this.repository
				.findByPersonIdAndActiveTrueAndStartLessThanEqualAndEndGreaterThanEqual(personId,
						from, from);

		if (optionalWeekly.isPresent())
			weeklies.add(optionalWeekly.get());

		weeklies.addAll(this.repository
				.findAllByPersonIdAndActiveTrueAndStartGreaterThanEqualAndEndLessThanEqual(personId,
						from, to));

		// get a weekly that 'to date' is in [start, end]
		optionalWeekly = this.repository
				.findByPersonIdAndActiveTrueAndStartLessThanEqualAndEndGreaterThanEqual(personId,
						to, to);

		if (optionalWeekly.isPresent())
			weeklies.add(optionalWeekly.get());

		for (Weekly w : weeklies) {
			w.setWorkAttendances(this.workAttendanceService
					.getAllFromWeeklyAndBetweenDates(w.getId(), from, to));
		}

		return weeklies;
	}

	public List<Weekly> getAllFromPersonEachWithWorkAttendancesByStateBetweenDates(Integer personId,
			WorkAttendanceState state, LocalDate from, LocalDate to) {
		List<Weekly> weeklies = new ArrayList<>();

		// get a weekly that 'from date' is in [start, end]
		Optional<Weekly> optionalWeekly = this.repository
				.findByPersonIdAndActiveTrueAndStartLessThanEqualAndEndGreaterThanEqual(personId,
						from, from);

		if (optionalWeekly.isPresent())
			weeklies.add(optionalWeekly.get());

		weeklies.addAll(this.repository
				.findAllByPersonIdAndActiveTrueAndStartGreaterThanEqualAndEndLessThanEqual(personId,
						from, to));

		// get a weekly that 'to date' is in [start, end]
		optionalWeekly = this.repository
				.findByPersonIdAndActiveTrueAndStartLessThanEqualAndEndGreaterThanEqual(personId,
						to, to);

		if (optionalWeekly.isPresent())
			weeklies.add(optionalWeekly.get());


		for (Weekly w : weeklies) {
			w.setWorkAttendances(this.workAttendanceService
					.getAllFromWeeklyAndStateBetweenDates(w.getId(), state, from, to));
		}

		return weeklies;
	}

	public Weekly insert(Integer personId, Weekly requestBody) {
		requestBody.setPersonId(personId);
		this.validator.validateInsert(requestBody);

		if (requestBody.getEnd() != null
				&& requestBody.getStart().compareTo(requestBody.getEnd()) >= 0)
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getWeeklyServiceMessenger().crossDates(), HttpStatus.UNPROCESSABLE_ENTITY);

		Optional<Weekly> optional =
				this.repository.findTopByPersonIdAndActiveTrueOrderByIdDesc(personId);

		if (optional.isPresent()) {
			Weekly lastWeekly = optional.get();
			if (requestBody.getStart().compareTo(lastWeekly.getEnd()) <= 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getWeeklyServiceMessenger().overlappedDates(),
						HttpStatus.UNPROCESSABLE_ENTITY);
		}

		Weekly ret = this.repository.save(requestBody);

		// insert each responsibility
		for (Responsibility r : requestBody.getResponsibilities())
			this.responsibilityService.insert(ret.getId(), r);

		// create all work attendances for this weekly
		this.workAttendanceService.createWorkAttendancesBetweenDates(ret.getId(), ret.getStart(),
				ret.getEnd(), ret.getResponsibilities());

		return requestBody;
	}

	public Weekly closeWeekly(Integer weeklyId, LocalDate end) {
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

		// ret.setEnd(LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()));
		ret.setEnd(LocalDate.now());

		return ret;
	}

	public void delete(int id) {
		Weekly weekly = this.repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getWeeklyServiceMessenger()
								.deleteNonExistentEntity(Weekly.class.getSimpleName(), id),
						HttpStatus.NOT_FOUND));

		weekly.setActive(false);
		this.responsibilityService.deleteAll(id);
	}

	public void deleteAll(int personId) {
		List<Weekly> weeklies = this.repository.findAllByPersonIdAndActiveTrue(personId);

		if (weeklies.size() > 0)
			for (int i = 0; i < weeklies.size(); i++) {
				weeklies.get(i).setActive(false);
				this.responsibilityService.deleteAll(weeklies.get(i).getId());
			}
	}

	public void workAttendanceCalculation(Weekly weekly, LocalDate date,
			List<Register> dateRegisters) {

		List<Responsibility> dateResponsibilities = new ArrayList<>();
		for (Responsibility r : weekly.getResponsibilities())
			if (r.getDay() == date.getDayOfWeek())
				dateResponsibilities.add(r);

		WorkAttendance workAttendance =
				this.workAttendanceService.getOneFromWeeklyIdAndDate(weekly.getId(), date);

		// automaton here

		// if (workAttendance.getState() == WorkAttendanceState.ABSENT) {
		// workAttendance.setState(WorkAttendanceState.INCONSISTENT);
		// } else if (workAttendance.getState() == WorkAttendanceState.INCONSISTENT
		// && isFulfilledAtLeastOneResponsibility(dateResponsibilities, dateRegisters)
		// && !isFullAttendance(dateResponsibilities, dateRegisters)) {
		// workAttendance.setState(WorkAttendanceState.PARTIAL);
		// } else if (workAttendance.getState() == WorkAttendanceState.PARTIAL
		// && isFullAttendance(dateResponsibilities, dateRegisters)) {
		// workAttendance.setState(WorkAttendanceState.FULL);
		// }
	}

	private boolean isFulfilledAtLeastOneResponsibility(List<Responsibility> dateResponsibilities,
			List<Register> dateRegisters) {

		for (Register register : dateRegisters) {

			for (Responsibility responsibility : dateResponsibilities) {
				Duration.between(null, null);
			}

		}

		return false;
	}

}
