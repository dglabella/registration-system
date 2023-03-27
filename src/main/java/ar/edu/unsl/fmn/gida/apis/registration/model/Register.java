package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

@Entity
@Table(name = "registers")
public class Register {
	// =================================== keys ===================================
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = Constraints.Register.PERSON_ID_NULLABLE)
	private Integer personId;

	@Column(nullable = Constraints.Register.ACCESS_ID_NULLABLE)
	private Integer accessId;

	// ================================ attributes ================================
	@Column(nullable = Constraints.Register.TIME_NULLABLE)
	private LocalDateTime time;

	// ================================== extras ==================================
	@Column(nullable = false)
	private boolean active = true;

	// ============================ model associations ============================
	@JoinColumn(name = "personId", referencedColumnName = "id", insertable = false,
			updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Person person;

	@JoinColumn(name = "accessId", referencedColumnName = "id", insertable = false,
			updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
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

	public LocalDateTime getTime() {
		return this.time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
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
