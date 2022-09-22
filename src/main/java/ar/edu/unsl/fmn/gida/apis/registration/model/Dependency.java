package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "dependencies")
public class Dependency implements Cloneable {
    // =================================== keys ===================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ================================ attributes ================================
    @NotBlank
    @Size(max = 30, message = "must be between 1 and 30 chars")
    @Column(name = "name", nullable = false, unique = true, length = 30)
    private String dependencyName;

    @Size(max = 100, message = "must be between 0 and 100 chars")
    @Column(length = 100)
    private String description;

    // ================================== extras ==================================
    @NotNull
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

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public Dependency clone() {
        Dependency d = new Dependency();

        d.setId(this.id);
        d.setDependencyName(this.dependencyName);
        d.setDescription(this.description);
        d.setActive(this.active);

        return d;
    }
}
