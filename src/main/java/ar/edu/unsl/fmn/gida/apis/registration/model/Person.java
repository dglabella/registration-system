package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import ar.edu.unsl.fmn.gida.apis.registration.enums.Role;

@Entity
@Table(name = "persons")
public class Person {
    // =================================== keys ===================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @Column(nullable = false)
    // private Integer dependency_fk;

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
    @NotNull
    @Column(nullable = false)
    private boolean active = true;

    // ============================ model associations ============================


    @Column
    @Enumerated
    @ElementCollection(targetClass = Role.class)
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



}
