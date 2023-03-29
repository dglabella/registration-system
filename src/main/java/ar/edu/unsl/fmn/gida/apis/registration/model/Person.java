package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ar.edu.unsl.fmn.gida.apis.registration.enums.Role;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

@Entity
@Table(name = "persons")
public class Person {
	// =================================== keys ===================================
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = Constraints.Person.DEPENDENCY_ID_NULLABLE)
	private Integer dependencyId;

	// ================================ attributes ================================
	@Column(name = "name", nullable = Constraints.Person.NAME_NULLABLE,
			length = Constraints.Person.NAME_MAX_LENGHT)
	private String personName;

	@Column(name = "last_name", nullable = Constraints.Person.LAST_NAME_NULLABLE,
			length = Constraints.Person.LAST_NAME_MAX_LENGHT)
	private String personLastName;

	@Column(nullable = Constraints.Person.DNI_NULLABLE, unique = Constraints.Person.DNI_UNIQUE,
			length = Constraints.Person.DNI_MAX_LENGHT)
	private String dni;

	// ================================== extras ==================================
	@Column(nullable = false)
	private boolean active = true;

	// ============================ model associations ============================
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "dependencyId", referencedColumnName = "id", insertable = false,
			updatable = false)
	private Dependency dependency;

	@Transient
	@JsonManagedReference
	private Weekly currentWeekly;

	@Transient
	@JsonManagedReference
	private Credential credential;

	@Transient
	@JsonManagedReference
	private List<Register> registers;

	@Enumerated(EnumType.ORDINAL)
	@ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
	private List<Role> roles;

	// =============================== constructors ===============================
	public Person() {}

	// ============================ getters and setters ===========================
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDependencyId() {
		return this.dependencyId;
	}

	public void setDependencyId(Integer dependencyId) {
		this.dependencyId = dependencyId;
	}

	public String getPersonLastName() {
		return this.personLastName;
	}

	public void setPersonLastName(String personLastName) {
		this.personLastName = personLastName;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Dependency getDependency() {
		return this.dependency;
	}

	public void setDependency(Dependency dependency) {
		this.dependency = dependency;
	}

	public Weekly getCurrentWeekly() {
		return this.currentWeekly;
	}

	public void setCurrentWeekly(Weekly currentWeekly) {
		this.currentWeekly = currentWeekly;
	}

	public Credential getCredential() {
		return this.credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public List<Register> getRegisters() {
		return this.registers;
	}

	public void setRegisters(List<Register> registers) {
		this.registers = registers;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
