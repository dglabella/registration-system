package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.time.LocalTime;
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
import ar.edu.unsl.fmn.gida.apis.registration.enums.Day;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

@Entity
@Table(name = "responsibilities")
public class Responsibility {

    // =================================== keys ===================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = Constraints.Responsibility.WEEKLY_ID_NULLABLE)
    private Integer weeklyId;

    // ================================ attributes ================================
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = Constraints.Responsibility.DAY_NULLABLE)
    private Day day;

    @Column(nullable = Constraints.Responsibility.ENTRANCE_TIME_NULLABLE)
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime entranceTime;

    @Column(nullable = Constraints.Responsibility.DEPARTURE_TIME_NULLABLE)
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime departureTime;

    // ================================ extras ================================
    @Column(nullable = false)
    private boolean active = true;

    // ============================ model associations ============================
    @JoinColumn(name = "weeklyId", referencedColumnName = "id", insertable = false,
            updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Weekly weekly;

    // =============================== constructors ===============================
    public Responsibility() {}

    // ============================ getters and setters ===========================
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeeklyId() {
        return this.weeklyId;
    }

    public void setWeeklyId(Integer weeklyId) {
        this.weeklyId = weeklyId;
    }

    public Day getDay() {
        return this.day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public LocalTime getEntranceTime() {
        return this.entranceTime;
    }

    public void setEntranceTime(LocalTime entranceTime) {
        this.entranceTime = entranceTime;
    }

    public LocalTime getDepartureTime() {
        return this.departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Weekly getWeekly() {
        return this.weekly;
    }

    public void setWeekly(Weekly weekly) {
        this.weekly = weekly;
    }

    @Override
    public boolean equals(Object responsibility) {
        if (responsibility == this)
            return true;
        if (!(responsibility instanceof Responsibility)) {
            return false;
        }

        Responsibility r = (Responsibility) responsibility;

        boolean ret = false;

        if (this.day == r.day && this.entranceTime.equals(r.entranceTime)
                && this.departureTime.equals(r.departureTime))
            ret = true;

        return ret;
    }
}
