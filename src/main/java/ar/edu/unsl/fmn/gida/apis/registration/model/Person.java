package ar.edu.unsl.fmn.gida.apis.registration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {
    // =================================== keys ===================================
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ================================ attributes ================================
    @Column(nullable = false, length = 30)
    private String lastName;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 10, unique = true)
    private String dni;

    // ================================== extras ==================================
    @Column(nullable = false)
    private boolean active;

    // ============================ model associations ============================
    @ManyToOne(fetch = FetchType.EAGER)
    private Dependency dependency;

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
}
