package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.RegisterRepository;

@Service
@Transactional
public class RegisterService {

	@Autowired
	private RegisterRepository repository;

	private final String TIME_PART_FROM = "T00:00:00";
	private final String TIME_PART_TO = "T00:00:00";

	public Register getOne(int id) {
		Register r = this.repository.findById(id)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getPersonServiceMessenger()
								.notFound(Register.class.getSimpleName(), id),
						HttpStatus.NOT_FOUND));
		return r;
	}

	public Page<Register> getAll(String from, String to, int page, int size) {
		LocalDateTime fromDate = null;
		LocalDateTime toDate = null;

		try {
			if (from != null && from.trim().length() != 0) {
				from += this.TIME_PART_FROM;
				fromDate = LocalDateTime.parse(from);
			} else {
				fromDate = LocalDateTime.MIN;
			}

			if (to != null && to.trim().length() != 0) {
				to += this.TIME_PART_TO;
				toDate = LocalDateTime.parse(to);
			} else {
				toDate = LocalDateTime.now();
			}

			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().crossDates(),
						HttpStatus.BAD_REQUEST);

		} catch (DateTimeParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationError(),
					HttpStatus.BAD_REQUEST);
		}

		Page<Register> registersPage =
				this.repository.findAllByTimeBetweenAndActiveTrueOrderByIdDesc(fromDate, toDate,
						PageRequest.of(page, size));

		return registersPage;
	}

	public List<Register> getAllFromPersonWithDate(int personId, LocalDate date) {
		return this.repository.findAllByPersonIdAndActiveTrueAndTimeBetween(personId,
				date.atStartOfDay(), date.plusDays(1).atStartOfDay());
	}

	public List<Register> getAllFromPerson(Integer personId, String from, String to) {
		LocalDateTime fromDate = null;
		LocalDateTime toDate = null;

		try {
			if (from != null && from.trim().length() != 0) {
				from += this.TIME_PART_FROM;
				fromDate = LocalDateTime.parse(from);
			} else {
				fromDate = LocalDateTime.MIN;
			}

			if (to != null && to.trim().length() != 0) {
				to += this.TIME_PART_TO;
				toDate = LocalDateTime.parse(to);
			} else {
				toDate = LocalDateTime.now();
			}

			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().crossDates(),
						HttpStatus.BAD_REQUEST);


		} catch (DateTimeParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationError(),
					HttpStatus.BAD_REQUEST);
		}

		return this.repository.findAllByPersonIdAndActiveTrueAndTimeBetween(personId, fromDate,
				toDate);
	}

	public Page<Register> getAllFromPerson(Integer personId, String from, String to, int page,
			int size) {
		LocalDateTime fromDate = null;
		LocalDateTime toDate = null;

		try {
			if (from != null && from.trim().length() != 0) {
				from += this.TIME_PART_FROM;
				fromDate = LocalDateTime.parse(from);
			} else {
				fromDate = LocalDateTime.MIN;
			}

			if (to != null && to.trim().length() != 0) {
				to += this.TIME_PART_TO;
				toDate = LocalDateTime.parse(to);
			} else {
				toDate = LocalDateTime.now();
			}

			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().crossDates(),
						HttpStatus.BAD_REQUEST);


		} catch (DateTimeParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationError(),
					HttpStatus.BAD_REQUEST);
		}

		return this.repository.findAllByPersonIdAndActiveTrueAndTimeBetween(personId, fromDate,
				toDate, PageRequest.of(page, size));
	}

	public List<Register> getAllFromPersonByDniApproach(String dniPattern, String from, String to) {
		LocalDateTime fromDate = null;
		LocalDateTime toDate = null;

		try {
			fromDate = (from != null && from.trim().length() != 0) ? LocalDateTime.parse(from)
					: LocalDateTime.MIN;

			toDate = (to != null && to.trim().length() != 0) ? LocalDateTime.parse(to)
					: LocalDateTime.now();
			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().crossDates(),
						HttpStatus.BAD_REQUEST);


		} catch (DateTimeParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationError(),
					HttpStatus.BAD_REQUEST);
		}

		List<Register> registers =
				this.repository.findAllByTimeBetweenAndActiveTrue(fromDate, toDate);

		List<Register> ret = new ArrayList<>();

		for (Register r : registers) {
			if (r.getPerson().getDni().contains(dniPattern)) {
				// clean extra data
				// r.setAccess(null);
				// r.setPerson(null);
				ret.add(r);
			}
		}

		return ret;
	}

	public Page<Register> getAllFromPersonByDniApproach(String dniPattern, String from, String to,
			int page, int size) {
		LocalDateTime fromDate = null;
		LocalDateTime toDate = null;

		try {
			fromDate = (from != null && from.trim().length() != 0) ? LocalDateTime.parse(from)
					: LocalDateTime.MIN;

			toDate = (to != null && to.trim().length() != 0) ? LocalDateTime.parse(to)
					: LocalDateTime.now();
			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().crossDates(),
						HttpStatus.BAD_REQUEST);


		} catch (DateTimeParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationError(),
					HttpStatus.BAD_REQUEST);
		}

		List<Register> registers =
				this.repository.findAllByTimeBetweenAndActiveTrue(fromDate, toDate);

		List<Register> ret = new ArrayList<>();

		for (Register r : registers) {
			if (r.getPerson().getDni().contains(dniPattern)) {
				// clean extra data
				// r.setAccess(null);
				// r.setPerson(null);
				ret.add(r);
			}
		}

		int fromIndex = page * size;
		int toIndex = fromIndex + size;

		if (fromIndex >= ret.size()) {
			ret = new ArrayList<>();
		} else {
			if (toIndex >= ret.size()) {
				ret = ret.subList(fromIndex, ret.size());
			} else {
				ret = ret.subList(fromIndex, toIndex);
			}
		}

		PageImpl<Register> pageRet = new PageImpl<>(ret, PageRequest.of(page, size), ret.size());

		return pageRet;
	}

	public Register insert(int personId, int accessId) {
		Register register = new Register();

		register.setAccessId(accessId);
		register.setPersonId(personId);
		register.setTime(LocalDateTime.now());

		Register ret = this.repository.save(register);

		return ret;
	}

	public Register update(int id, Register requestBody) {
		throw new ErrorResponse("update register operation not available...",
				HttpStatus.NOT_IMPLEMENTED);
	}

	public void delete(int id) {
		throw new ErrorResponse("delete register operation not available...",
				HttpStatus.NOT_IMPLEMENTED);
	}

	public void deleteAll(int personId) {

		List<Register> registers = this.repository.findAllByPersonIdAndActiveTrue(personId);

		if (registers.size() > 0)
			for (int i = 0; i < registers.size(); i++)
				registers.get(i).setActive(false);
	}
}
