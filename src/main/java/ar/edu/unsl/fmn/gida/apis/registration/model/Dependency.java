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
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ================================ attributes ================================
    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = true, length = 100)
    private String description;

    // ================================== extras ==================================
    @Column(nullable = false)
    private boolean active;

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
