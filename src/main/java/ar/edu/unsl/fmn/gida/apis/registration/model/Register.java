package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

@Entity
@Table(name = "registers")
public class Register {
	// =================================== keys ===================================
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = Constraints.Register.PERSON_FK_NULLABLE)
	private Integer personId;

	@Column(nullable = Constraints.Register.ACCESS_FK_NULLABLE)
	private Integer accessId;

	// ================================ attributes ================================
	@Column(nullable = Constraints.Register.CHECK_IN_NULLABLE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkIn;

	@Column(nullable = Constraints.Register.CHECK_OUT_NULLABLE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkOut;

	// ================================== extras ==================================
	@Column(nullable = false)
	private boolean active = true;

	// ============================ model associations ============================
	@JoinColumn(name = "personId", referencedColumnName = "id", insertable = false,
			updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Person person;

	@JoinColumn(name = "accessId", referencedColumnName = "id", insertable = false,
			updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Access access;

	// =============================== constructors ===============================
	public Register() {}

	// ============================ getters and setters ===========================
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCheckIn() {
		return this.checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return this.checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Access getAccess() {
		return this.access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}

	public Integer getPersonId() {
		return this.personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getAccessId() {
		return this.accessId;
	}

	public void setAccessId(Integer accessId) {
		this.accessId = accessId;
	}
}
