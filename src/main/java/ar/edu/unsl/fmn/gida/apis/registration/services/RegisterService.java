package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;
import ar.edu.unsl.fmn.gida.apis.registration.model.auxiliaries.Check;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.RegisterRepository;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CheckValidator;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.QrCypher;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.Cypher;

@Service
@Transactional
public class RegisterService {

	@Autowired
	private RegisterRepository repository;

	private final CheckValidator validator = new CheckValidator(new CustomExpressionValidator(),
			RegistrationSystemApplication.MESSENGER.getRegisterValidationMessenger());

	private final Cypher cypher = new QrCypher();

	private final SimpleDateFormat dateFormatter =
			new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

	public Register getOne(int id) {
		Register r = this.repository.findById(id)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getPersonServiceMessenger()
								.notFound(Register.class.getSimpleName(), id),
						HttpStatus.NOT_FOUND));
		return r;
	}

	public Page<Register> getAll(String from, String to, int page, int size) {
		Date fromDate = null;
		Date toDate = null;

		try {
			fromDate = (from != null && from.trim().length() != 0) ? this.dateFormatter.parse(from)
					: new Date(Long.MIN_VALUE);
			toDate = (to != null && to.trim().length() != 0) ? this.dateFormatter.parse(to)
					: new Date();

			if (fromDate.compareTo(toDate) > 0) {
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().dateValueSpecificationErrorMessage(),
						HttpStatus.BAD_REQUEST);
			}

		} catch (ParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationErrorMessage(),
					HttpStatus.BAD_REQUEST);
		}
		return this.repository.findAllByCheckInBetweenAndActiveTrueOrderByIdDesc(fromDate, toDate,
				PageRequest.of(page, size));
	}

	public Page<Register> getAllFromPerson(Integer personId, String from, String to, int page,
			int size) {
		Date fromDate = null;
		Date toDate = null;

		try {
			fromDate = (from != null && from.trim().length() != 0) ? this.dateFormatter.parse(from)
					: new Date(Long.MIN_VALUE);
			toDate = (to != null && to.trim().length() != 0) ? this.dateFormatter.parse(to)
					: new Date();

			if (fromDate.compareTo(toDate) > 0) {
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().dateValueSpecificationErrorMessage(),
						HttpStatus.BAD_REQUEST);
			}

		} catch (ParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationErrorMessage(),
					HttpStatus.BAD_REQUEST);
		}
		return this.repository.findAllByPersonIdAndActiveTrueAndCheckInBetween(personId, fromDate,
				toDate, PageRequest.of(page, size));
	}

	public List<Register> getAllFromPersonByDniApproach(String dniPattern, String from, String to) {
		Date fromDate = null;
		Date toDate = null;

		try {
			fromDate = (from != null && from.trim().length() != 0) ? this.dateFormatter.parse(from)
					: new Date(Long.MIN_VALUE);
			toDate = (to != null && to.trim().length() != 0) ? this.dateFormatter.parse(to)
					: new Date();

			if (fromDate.compareTo(toDate) > 0) {
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().dateValueSpecificationErrorMessage(),
						HttpStatus.BAD_REQUEST);
			}

		} catch (ParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationErrorMessage(),
					HttpStatus.BAD_REQUEST);
		}

		List<Register> registers =
				this.repository.findAllByCheckInBetweenAndActiveTrue(fromDate, toDate);

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

		Date fromDate = null;
		Date toDate = null;

		try {
			fromDate = (from != null && from.trim().length() != 0) ? this.dateFormatter.parse(from)
					: new Date(Long.MIN_VALUE);
			toDate = (to != null && to.trim().length() != 0) ? this.dateFormatter.parse(to)
					: new Date();

			if (fromDate.compareTo(toDate) > 0) {
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().dateValueSpecificationErrorMessage(),
						HttpStatus.BAD_REQUEST);
			}

		} catch (ParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationErrorMessage(),
					HttpStatus.BAD_REQUEST);
		}

		List<Register> registers =
				this.repository.findAllByCheckInBetweenAndActiveTrue(fromDate, toDate);

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

	// public Register insert(Register requestBody) {
	// this.validator.validateInsert(requestBody);

	// Integer personId = null;
	// Register r1 = new Register();
	// Register r1Aux = new Register();
	// Register r2 = null;
	// Register r2Aux = new Register();
	// Optional<Register> optional;

	// personId = Integer.parseInt(this.cypher.decrypt(requestBody.getEncryptedData()));

	// optional = this.repository.findByPersonIdAndCheckOutIsNullAndActiveTrue(person.getId());

	// if (optional.isPresent()) {
	// // do check out
	// r1.setId(optional.get().getId());
	// r1.setPersonId(optional.get().getPersonId());
	// r1.setAccessId(optional.get().getAccessId());
	// r1.setCheckIn(optional.get().getCheckIn());
	// r1.setCheckOut(new Date());

	// r1Aux.setId(r1.getId());
	// r1Aux.setPersonId(r1.getPersonId());
	// r1Aux.setAccessId(r1.getAccessId());
	// r1Aux.setCheckIn(r1.getCheckIn());
	// r1Aux.setCheckOut(r1.getCheckOut());
	// this.repository.save(r1);

	// r1Aux.setPerson(person);
	// r1Aux.setAccess(optional.get().getAccess());

	// if (requestBody.getAccessId() != optional.get().getAccessId()) {
	// r2 = new Register();
	// r2.setPersonId(person.getId());
	// r2.setAccessId(requestBody.getAccessId());
	// r2.setCheckIn(new Date());

	// r2Aux.setId(r2.getId());
	// r2Aux.setPersonId(r2.getPersonId());
	// r2Aux.setAccessId(r2.getAccessId());
	// r2Aux.setCheckIn(r2.getCheckIn());
	// r2Aux.setCheckOut(r2.getCheckOut());
	// this.repository.save(r2);

	// r2Aux.setPerson(person);
	// r2Aux.setAccess(requestBody.getAccess());
	// }
	// } else {
	// r1.setPersonId(person.getId());
	// r1.setAccessId(requestBody.getAccessId());
	// r1.setCheckIn(new Date());

	// r1Aux.setId(r1.getId());
	// r1Aux.setPersonId(r1.getPersonId());
	// r1Aux.setAccessId(r1.getAccessId());
	// r1Aux.setCheckIn(r1.getCheckIn());
	// r1Aux.setCheckOut(r1.getCheckOut());
	// this.repository.save(r1);

	// r1Aux.setPerson(person);
	// r1Aux.setAccess(requestBody.getAccess());
	// }

	// return r2 == null ? r1Aux : r2Aux;
	// }


	public Register insert(Check requestBody) {
		this.validator.validateInsert(requestBody);

		int personId = Integer.parseInt(this.cypher.decrypt(requestBody.getEncryptedData()));
		Optional<Register> optional =
				this.repository.findByPersonIdAndCheckOutIsNullAndActiveTrue(personId);

		if (optional.isPresent()) {

		} else {

		}

		return null;
	}

	public Register update(int id, Register requestBody) {
		throw new ErrorResponse("update register operation not available...",
				HttpStatus.NOT_IMPLEMENTED);
	}

	public void delete(int personId) {

		List<Register> registers = this.repository.findAllByPersonIdAndActiveTrue(personId);

		if (registers.size() > 0) {
			for (int i = 0; i < registers.size(); i++)
				registers.get(i).setActive(false);
		}
	}
}
