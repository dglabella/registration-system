package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

@Entity
@Table(name = "weeklies")
public class Weekly {
	// =================================== keys ===================================
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = Constraints.Weekly.PERSON_ID_NULLABLE)
	private Integer personId;

	// ================================ attributes ================================

	@Column(nullable = Constraints.Weekly.START_NULLABLE)
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private LocalDate start;

	@Column(nullable = Constraints.Weekly.END_NULLABLE)
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private LocalDate end;

	// ================================== extras ==================================
	@Column(nullable = false)
	private boolean active = true;

	// ============================ model associations ============================
	@JoinColumn(name = "personId", referencedColumnName = "id", insertable = false,
			updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Person person;

	@Transient
	@JsonManagedReference
	private List<Responsibility> responsibilities;

	@Transient
	@JsonManagedReference
	private List<WorkAttendance> workAttendances;

	// =============================== constructors ===============================
	public Weekly() {}

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

	public LocalDate getStart() {
		return this.start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return this.end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
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

	public List<Responsibility> getResponsibilities() {
		return this.responsibilities;
	}

	public void setResponsibilities(List<Responsibility> responsibilities) {
		this.responsibilities = responsibilities;
	}

	public List<WorkAttendance> getWorkAttendances() {
		return this.workAttendances;
	}

	public void setWorkAttendances(List<WorkAttendance> workAttendances) {
		this.workAttendances = workAttendances;
	}

	@Override
	public boolean equals(Object weekly) {
		if (weekly == this)
			return true;

		if (!(weekly instanceof Weekly)) {
			return false;
		}

		Weekly w = (Weekly) weekly;

		if (this.id != null && w.getId() != null && this.id != w.getId()) {
			return false;
		}

		boolean ret = true;

		if (this.start.compareTo(w.start) != 0
				|| this.responsibilities.size() != w.responsibilities.size()) {
			ret = false;
		} else {
			for (int i = 0; i < this.responsibilities.size(); i++) {
				if (!this.responsibilities.get(i).equals(w.responsibilities.get(i))) {
					ret = false;
					i = this.responsibilities.size();
				}
			}
		}

		return ret;
	}
}
