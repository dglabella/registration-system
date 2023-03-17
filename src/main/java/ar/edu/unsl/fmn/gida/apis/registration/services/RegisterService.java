package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

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

			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().dateValueSpecificationErrorMessage(),
						HttpStatus.BAD_REQUEST);


		} catch (ParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationErrorMessage(),
					HttpStatus.BAD_REQUEST);
		}
		return this.repository.findAllByTimeBetweenAndActiveTrueOrderByIdDesc(fromDate, toDate,
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

			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().dateValueSpecificationErrorMessage(),
						HttpStatus.BAD_REQUEST);


		} catch (ParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationErrorMessage(),
					HttpStatus.BAD_REQUEST);
		}
		return this.repository.findAllByPersonIdAndActiveTrueAndTimeBetween(personId, fromDate,
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

			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().dateValueSpecificationErrorMessage(),
						HttpStatus.BAD_REQUEST);

		} catch (ParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationErrorMessage(),
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
		Date fromDate = null;
		Date toDate = null;

		try {
			fromDate = (from != null && from.trim().length() != 0) ? this.dateFormatter.parse(from)
					: new Date(Long.MIN_VALUE);
			toDate = (to != null && to.trim().length() != 0) ? this.dateFormatter.parse(to)
					: new Date();

			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().dateValueSpecificationErrorMessage(),
						HttpStatus.BAD_REQUEST);


		} catch (ParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationErrorMessage(),
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

	public Register insert(Check requestBody) {
		this.validator.validateInsert(requestBody);

		// int personId = Integer.parseInt(this.cypher.decrypt(requestBody.getEncryptedData()));
		// Optional<Register> optional =
		// this.repository.findByPersonIdAndCheckOutIsNullAndActiveTrue(personId);

		// if (optional.isPresent()) {

		// } else {

		// }

		return null;
	}

	public Register update(int id, Register requestBody) {
		throw new ErrorResponse("update register operation not available...",
				HttpStatus.NOT_IMPLEMENTED);
	}

	public void delete(int personId) {

		List<Register> registers = this.repository.findAllByPersonIdAndActiveTrue(personId);

		if (registers.size() > 0)
			for (int i = 0; i < registers.size(); i++)
				registers.get(i).setActive(false);
	}
}
