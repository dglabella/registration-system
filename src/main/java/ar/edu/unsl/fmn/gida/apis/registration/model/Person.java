package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import ar.edu.unsl.fmn.gida.apis.registration.enums.Role;

@Entity
@Table(name = "persons")
public class Person {
    // =================================== keys ===================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer dependencyFk;

    // ================================ attributes ================================
    @Column(name = "last_name", nullable = false, length = 60)
    private String personLastName;

    @Column(name = "name", nullable = false, length = 60)
    private String personName;

    @Column(nullable = false, unique = true)
    private String dni;

    // ================================== extras ==================================
    @Column(nullable = false)
    private boolean active = true;

    // ============================ model associations ============================
    @JoinColumn(name = "dependencyFk", referencedColumnName = "id", insertable = false,
            updatable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Dependency dependency;

    @Transient
    private Weekly currentWeekly;

    @Enumerated
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

    public Integer getDependencyFk() {
        return this.dependencyFk;
    }

    public void setDependencyFk(Integer dependencyFk) {
        this.dependencyFk = dependencyFk;
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

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
