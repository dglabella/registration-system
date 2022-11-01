package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.util.Date;
import javax.persistence.CascadeType;
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
import com.fasterxml.jackson.annotation.JsonBackReference;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

@Entity
@Table(name = "weeklies")
public class Weekly {
    // =================================== keys ===================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = Constraints.Weekly.PERSONFK_NULLABLE)
    private Integer personFk;

    // ================================ attributes ================================
    @Column(nullable = Constraints.Weekly.MONDAY_NULLABLE)
    private boolean monday = true;

    @Column(nullable = Constraints.Weekly.TUESDAY_NULLABLE)
    private boolean tuesday = true;

    @Column(nullable = Constraints.Weekly.WEDNESDAY_NULLABLE)
    private boolean wednesday = true;

    @Column(nullable = Constraints.Weekly.THURDAY_NULLABLE)
    private boolean thursday = true;

    @Column(nullable = Constraints.Weekly.FRIDAY_NULLABLE)
    private boolean friday = true;

    @Column(nullable = Constraints.Weekly.SATURDAY_NULLABLE)
    private boolean saturday = false;

    @Column(nullable = Constraints.Weekly.SUNDAY_NULLABLE)
    private boolean sunday = false;

    @Column(nullable = Constraints.Weekly.START_NULLABLE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date start = new Date();

    @Column(nullable = Constraints.Weekly.END_NULLABLE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    // ================================== extras ==================================
    @Column(nullable = false)
    private boolean active = true;

    // ============================ model associations ============================
    @JoinColumn(name = "personFk", referencedColumnName = "id", insertable = false,
            updatable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonBackReference
    private Person person;

    // =============================== constructors ===============================
    public Weekly() {}

    // ============================ getters and setters ===========================
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonFk() {
        return this.personFk;
    }

    public void setPersonFk(Integer personFk) {
        this.personFk = personFk;
    }

    public boolean isMonday() {
        return this.monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isTuesday() {
        return this.tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return this.wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean isThursday() {
        return this.thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean isFriday() {
        return this.friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public boolean isSaturday() {
        return this.saturday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public boolean isSunday() {
        return this.sunday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

    public Date getStart() {
        return this.start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return this.end;
    }

    public void setEnd(Date end) {
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

    /**
     * IN THIS IMPLEMENTATION: a weekly is equals to another one if and only if all days matchs.
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Weekly)) {
            return false;
        }
        Weekly weekly = (Weekly) o;
        return monday == weekly.monday && tuesday == weekly.tuesday && wednesday == weekly.wednesday
                && thursday == weekly.thursday && friday == weekly.friday
                && saturday == weekly.saturday && sunday == weekly.sunday;
    }
}
