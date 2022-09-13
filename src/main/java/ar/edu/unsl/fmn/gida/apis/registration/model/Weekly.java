package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "weeklies")
public class Weekly {
    // =================================== keys ===================================
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ================================ attributes ================================
    @Column(nullable = false)
    private boolean monday;

    @Column(nullable = false)
    private boolean tuesday;

    @Column(nullable = false)
    private boolean wednesday;

    @Column(nullable = false)
    private boolean thursday;

    @Column(nullable = false)
    private boolean friday;

    @Column(nullable = false)
    private boolean saturday;

    @Column(nullable = false)
    private boolean sunday;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    // ================================== extras ==================================
    @Column(nullable = false)
    private boolean active;

    // ============================ model associations ============================

    // =============================== constructors ===============================
    public Weekly() {}

    // ============================ getters and setters ===========================
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
