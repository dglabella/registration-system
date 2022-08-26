package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "accesses")
public class Access {
    // =================================== keys ===================================
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // ================================ attributes ================================
    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = true, length = 100)
    private String description;

    // ================================== extras ==================================
    @Column(nullable = false)
    private boolean active;

    // ============================ model associations ============================
    @OneToMany(mappedBy = "access", fetch = FetchType.LAZY)
    private List<Register> registers;

    // =============================== constructors ===============================
    public Access() {}

    // ============================ getters and setters ===========================
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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

    public List<Register> getRegisters() {
        return this.registers;
    }

    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }
}
