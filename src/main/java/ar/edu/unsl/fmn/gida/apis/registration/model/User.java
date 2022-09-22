package ar.edu.unsl.fmn.gida.apis.registration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import ar.edu.unsl.fmn.gida.apis.registration.enums.Privilege;

@Entity
@Table(name = "users")
public class User {
    // =================================== keys ===================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ================================ attributes ================================
    @NotBlank
    @Size(max = 30, message = "must be between 1 and 30 chars")
    @Column(name = "last_name", nullable = false, length = 30)
    private String userLastName;

    @NotBlank
    @Size(max = 30, message = "must be between 1 and 30 chars")
    @Column(name = "name", nullable = false, length = 30)
    private String userName;

    @NotBlank
    @Size(max = 30, message = "must be between 1 and 30 chars")
    @Column(nullable = false, length = 10, unique = true)
    private String dni;

    @NotBlank
    @Size(max = 60, message = "cannot exceed 60 chars")
    @Email(message = "not a valid email",
            regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$|^$")
    @Column(nullable = false, length = 60)
    private String email;

    @NotBlank
    @Size(max = 30, message = "must be between 1 and 30 chars")
    @Column(nullable = false, length = 20, unique = true)
    private String account;

    @NotBlank
    @Size(min = 8, max = 20, message = "must be between 8 and 20 chars")
    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 20)
    private Privilege privileges = Privilege.USER;

    // ================================== extras ==================================

    // active initial value = false. The user must be activated manually from DB by an ADMIN until
    // an automatic mechanism is developed.
    @NotNull
    @Column(nullable = false)
    private boolean active = false;

    // ============================ model associations ============================

    // =============================== constructors ===============================
    public User() {}

    // ============================ getters and setters ===========================
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserLastName() {
        return this.userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Privilege getPrivileges() {
        return this.privileges;
    }

    public void setPrivileges(Privilege privileges) {
        this.privileges = privileges;
    }


    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
