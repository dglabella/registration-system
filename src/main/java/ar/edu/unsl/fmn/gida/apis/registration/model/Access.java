package ar.edu.unsl.fmn.gida.apis.registration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "accesses")
public class Access {
    // =================================== keys ===================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ================================ attributes ================================
    @NotBlank
    @Size(max = 30, message = "must be between 1 and 30 chars")
    @Column(name = "name", nullable = false, length = 30)
    private String accesseName;

    @Size(max = 100, message = "must be between 0 and 100 chars")
    @Column(length = 100)
    private String description;

    // ================================== extras ==================================
    @NotNull
    @Column(nullable = false)
    private boolean active = true;

    // ============================ model associations ============================;

    // =============================== constructors ===============================
    public Access() {}

    // ============================ getters and setters ===========================
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccesseName() {
        return this.accesseName;
    }

    public void setAccesseName(String accesseName) {
        this.accesseName = accesseName;
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
