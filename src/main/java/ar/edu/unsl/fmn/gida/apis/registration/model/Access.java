package ar.edu.unsl.fmn.gida.apis.registration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

@Entity
@Table(name = "accesses")
public class Access {
	// =================================== keys ===================================
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// ================================ attributes ================================
	@Column(name = "name", nullable = Constraints.Access.NAME_NULLABLE,
			length = Constraints.Access.NAME_MAX_LENGHT)
	private String accessName;

	@Column(nullable = Constraints.Access.DESCRIPTION_NULLABLE,
			length = Constraints.Access.DESCRIPTION_MAX_LENGHT)
	private String description;

	// ================================== extras ==================================
	@Column(nullable = false)
	private boolean active = true;

	// ============================ model associations ============================;

	// =============================== constructors ===============================
	public Access() {}

	// ============================ getters and setters ===========================
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccessName() {
		return this.accessName;
	}

	public void setAccessName(String accesseName) {
		this.accessName = accesseName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
