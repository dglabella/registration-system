package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.io.Serializable;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import ar.edu.unsl.fmn.gida.apis.registration.enums.Role;

@Entity
@Table(name = "persons")
public class Person implements Serializable {
    // =================================== keys ===================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private Integer dependencyFk;

    // ================================ attributes ================================
    @NotBlank
    @Size(max = 50, message = "must be between 1 and 50 chars")
    @Column(name = "last_name", nullable = false, length = 50)
    private String personLastName;

    @NotBlank
    @Size(max = 50, message = "must be between 1 and 50 chars")
    @Column(name = "name", nullable = false, length = 50)
    private String personName;

    @NotNull
    @Column(nullable = false, unique = true)
    private Integer dni;

    // ================================== extras ==================================
    @NotNull
    @Column(nullable = false)
    private boolean active = true;

    // ============================ model associations ============================
    @JoinColumn(name = "dependencyFk", referencedColumnName = "id", insertable = false,
            updatable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Dependency dependency;

    @NotNull
    @Transient
    private Weekly currentWeekly;

    @NotNull
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

    public Integer getDni() {
        return this.dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public boolean isActive() {
        return this.active;
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
