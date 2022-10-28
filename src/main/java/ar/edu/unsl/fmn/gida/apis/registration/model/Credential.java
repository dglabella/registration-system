package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credentials")
public abstract class Credential {
    // =================================== keys ===================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // ================================ attributes ================================
    @Column(nullable = false)
    private int petitionsCounter;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private Blob img;

    // ================================== extras ==================================
    @Column(nullable = false)
    private boolean active = true;

    // ============================ model associations ============================

    // =============================== constructors ===============================
    public Credential() {}

    // ============================ getters and setters ===========================
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPetitionsCounter() {
        return this.petitionsCounter;
    }

    public void setPetitionsCounter(int petitionsCounter) {
        this.petitionsCounter = petitionsCounter;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Blob getImg() {
        return this.img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }
}
