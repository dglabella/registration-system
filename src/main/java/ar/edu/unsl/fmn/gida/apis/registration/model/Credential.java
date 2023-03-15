package ar.edu.unsl.fmn.gida.apis.registration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

@Entity
@Table(name = "credentials")
public class Credential {
	// =================================== keys ===================================
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = Constraints.Credential.PERSON_ID_NULLABLE)
	private Integer personId;

	// ================================ attributes ================================
	@Column(nullable = Constraints.Credential.DATA_NULLABLE,
			length = Constraints.Credential.DATA_MAX_LENGHT)
	private String data;

	@Lob
	@Column(nullable = Constraints.Credential.IMG_NULLABLE)
	private byte[] img;

	// ================================== extras ==================================
	@Column(nullable = false)
	private boolean active = true;

	// ============================ model associations ============================
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "personId", referencedColumnName = "id", insertable = false,
			updatable = false)
	@JsonBackReference
	private Person person;

	// =============================== constructors ===============================
	public Credential() {}

	// ============================ getters and setters ===========================
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPersonId() {
		return this.personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public byte[] getImg() {
		return this.img;
	}

	public void setImg(byte[] img) {
		this.img = img;
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
}
