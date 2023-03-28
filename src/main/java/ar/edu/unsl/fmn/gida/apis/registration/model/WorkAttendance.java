package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.time.LocalDate;
import javax.persistence.Column;
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
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import ar.edu.unsl.fmn.gida.apis.registration.enums.WorkAttendanceState;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

@Entity
@Table(name = "work_attendances")
public class WorkAttendance {
    // =================================== keys ===================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = Constraints.WorkAttendance.PERSON_ID_NULLABLE)
    private Integer personId;

    // ================================ attributes ================================
    @Column(nullable = Constraints.WorkAttendance.DATE_NULLABLE)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate date;

    @Enumerated(EnumType.ORDINAL)
    private WorkAttendanceState state;

    @Column(nullable = Constraints.WorkAttendance.JUSTIFIED_NULLABLE)
    private boolean justified = false;

    @Column(nullable = Constraints.WorkAttendance.REASON_NULLABLE)
    private String reason;

    // ================================== extras ==================================
    @Column(nullable = false)
    private boolean active = true;

    // ============================ model associations ============================
    @JoinColumn(name = "personId", referencedColumnName = "id", insertable = false,
            updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Person person;

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    // =============================== constructors ===============================

    public WorkAttendance() {}

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

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public WorkAttendanceState getState() {
        return this.state;
    }

    public void setState(WorkAttendanceState state) {
        this.state = state;
    }

    public boolean isJustified() {
        return this.justified;
    }

    public void setJustified(boolean justified) {
        this.justified = justified;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
