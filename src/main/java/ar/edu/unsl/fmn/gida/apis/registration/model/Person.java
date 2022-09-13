package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import ar.edu.unsl.fmn.gida.apis.registration.enums.Role;

@Entity
@Table(name = "persons")
public class Person {
    // =================================== keys ===================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ================================ attributes ================================
    @NotBlank
    @Size(max = 30, message = "must be between 1 and 30 chars")
    @Column(nullable = false, length = 30)
    private String lastName;

    @NotBlank
    @Size(max = 30, message = "must be between 1 and 30 chars")
    @Column(nullable = false, length = 30)
    private String name;

    @NotBlank
    @Size(max = 30, message = "must be between 1 and 30 chars")
    @Column(nullable = false, length = 10, unique = true)
    private String dni;

    // ================================== extras ==================================
    @Column(nullable = false)
    private boolean active = true;

    // ============================ model associations ============================
    @ManyToOne(fetch = FetchType.EAGER)
    private Dependency dependency;

    @OneToMany
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

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
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

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
