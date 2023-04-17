package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.enums.WorkAttendanceState;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Responsibility;
import ar.edu.unsl.fmn.gida.apis.registration.model.WorkAttendance;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.WorkAttendanceRepository;

@Service
@Transactional
public class WorkAttendanceService {

	@Autowired
	private WorkAttendanceRepository repository;

	public WorkAttendance getOne(int id) {
		return this.repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getWorkAttendanceServiceMessenger()
								.notFound(WorkAttendance.class.getSimpleName(), id),
						HttpStatus.NOT_FOUND));
	}

	public WorkAttendance getFromWeeklyIdAndDate(Integer weeklyId, LocalDate date) {
		return this.repository.findByWeeklyIdAndDateAndActiveTrue(weeklyId, date)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getWorkAttendanceServiceMessenger()
								.notFoundByWeeklyIdAndDate(weeklyId, date),
						HttpStatus.NOT_FOUND));
	}

	public List<WorkAttendance> getAllBetweenDates(String from, String to) {
		LocalDate fromDate = null;
		LocalDate toDate = null;

		try {
			fromDate = (from != null && from.trim().length() != 0) ? LocalDate.parse(from)
					: LocalDate.MIN;

			toDate = (to != null && to.trim().length() != 0) ? LocalDate.parse(to)
					: LocalDate.now();
			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getWorkAttendanceServiceMessenger().crossDate(), HttpStatus.BAD_REQUEST);


		} catch (DateTimeParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getWorkAttendanceServiceMessenger().dateFormatSpecificationError(),
					HttpStatus.BAD_REQUEST);
		}

		List<WorkAttendance> workAttendances =
				this.repository.findAllByDateBetweenAndActiveTrue(fromDate, toDate);

		return workAttendances;
	}

	public List<WorkAttendance> getAllBetweenDates(LocalDate from, LocalDate to) {

		if (from.compareTo(to) > 0)
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getWorkAttendanceServiceMessenger().crossDate(), HttpStatus.BAD_REQUEST);

		List<WorkAttendance> workAttendances =
				this.repository.findAllByDateBetweenAndActiveTrue(from, to);

		return workAttendances;
	}

	public Page<WorkAttendance> getAllBetweenDates(LocalDate from, LocalDate to, int page,
			int size) {

		if (from.compareTo(to) > 0)
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getWorkAttendanceServiceMessenger().crossDate(), HttpStatus.BAD_REQUEST);

		Page<WorkAttendance> workAttendances = this.repository
				.findAllByDateBetweenAndActiveTrue(from, to, PageRequest.of(page, size));

		return workAttendances;
	}

	public Page<WorkAttendance> getAllBetweenDates(String from, String to, int page, int size) {
		LocalDate fromDate = null;
		LocalDate toDate = null;

		try {
			fromDate = (from != null && from.trim().length() != 0) ? LocalDate.parse(from)
					: LocalDate.MIN;

			toDate = (to != null && to.trim().length() != 0) ? LocalDate.parse(to)
					: LocalDate.now();

			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getWorkAttendanceServiceMessenger().crossDate(), HttpStatus.BAD_REQUEST);

		} catch (DateTimeParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getWorkAttendanceServiceMessenger().dateFormatSpecificationError(),
					HttpStatus.BAD_REQUEST);
		}

		Page<WorkAttendance> workAttendances = this.repository
				.findAllByDateBetweenAndActiveTrue(fromDate, toDate, PageRequest.of(page, size));

		return workAttendances;
	}

	public List<WorkAttendance> getAllByStateBetweenDates(WorkAttendanceState state, String from,
			String to) {
		LocalDate fromDate = null;
		LocalDate toDate = null;

		try {
			fromDate = (from != null && from.trim().length() != 0) ? LocalDate.parse(from)
					: LocalDate.MIN;

			toDate = (to != null && to.trim().length() != 0) ? LocalDate.parse(to)
					: LocalDate.now();
			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getWorkAttendanceServiceMessenger().crossDate(), HttpStatus.BAD_REQUEST);


		} catch (DateTimeParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getWorkAttendanceServiceMessenger().dateFormatSpecificationError(),
					HttpStatus.BAD_REQUEST);
		}

		List<WorkAttendance> workAttendances =
				this.repository.findAllByStateAndDateBetweenAndActiveTrue(state, fromDate, toDate);

		return workAttendances;
	}

	public Page<WorkAttendance> getAllByStateBetweenDates(WorkAttendanceState state, String from,
			String to, int page, int size) {
		throw new ErrorResponse("not implemented yet", HttpStatus.NOT_IMPLEMENTED);
	}

	public List<WorkAttendance> getAllFromWeeklyBetweenDates(Integer weeklyId, LocalDate from,
			LocalDate to) {
		return this.repository.findAllByWeeklyIdAndActiveTrueAndDateBetween(weeklyId, from, to);
	}

	public List<WorkAttendance> getAllFromWeeklyByStateBetweenDates(Integer weeklyId,
			WorkAttendanceState state, LocalDate from, LocalDate to) {
		return this.repository.findAllByWeeklyIdAndStateAndActiveTrueAndDateBetween(weeklyId, state,
				from, to);
	}

	public WorkAttendance insert(WorkAttendance requestBody) {
		throw new ErrorResponse("work attendance insert operation not implemented",
				HttpStatus.NOT_IMPLEMENTED);
	}

	public List<WorkAttendance> createWorkAttendancesBetweenDates(int weeklyId, LocalDate from,
			LocalDate to, List<Responsibility> responsibilities) {
		// 0 for monday, 6 for sunday
		boolean[] days = {false, false, false, false, false, false, false};

		for (Responsibility r : responsibilities)
			days[r.getDay().ordinal()] = true;

		List<WorkAttendance> workAttendances = new ArrayList<>();

		LocalDate localDate = from;
		WorkAttendance workAttendance;
		while (localDate.compareTo(to) <= 0) {

			if (days[localDate.getDayOfWeek().ordinal()]) {
				workAttendance = new WorkAttendance();
				workAttendance.setWeeklyId(weeklyId);
				workAttendance.setDate(localDate);
				workAttendance.setState(WorkAttendanceState.ABSENT);

				workAttendances.add(workAttendance);
			}

			localDate = localDate.plusDays(1);
		}

		return this.repository.saveAll(workAttendances);
	}

	public WorkAttendance update(int id) {
		throw new ErrorResponse("work attendances update not implemented",
				HttpStatus.NOT_IMPLEMENTED);
	}

	public void delete(int id) {
		throw new ErrorResponse("work attendances delete not implemented",
				HttpStatus.NOT_IMPLEMENTED);
	}
}
