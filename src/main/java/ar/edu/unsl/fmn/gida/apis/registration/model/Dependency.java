package ar.edu.unsl.fmn.gida.apis.registration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dependencies")
public class Dependency {
    // =================================== keys ===================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ================================ attributes ================================
    @Column(name = "name", nullable = false, unique = true, length = 60)
    private String dependencyName;

    @Column(length = 200)
    private String description;

    // ================================== extras ==================================
    @Column(nullable = false)
    private boolean active = true;

    // ============================ model associations ============================

    // =============================== constructors ===============================
    public Dependency() {}

    // ============================ getters and setters ===========================
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDependencyName() {
        return this.dependencyName;
    }

    public void setDependencyName(String dependencyName) {
        this.dependencyName = dependencyName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
