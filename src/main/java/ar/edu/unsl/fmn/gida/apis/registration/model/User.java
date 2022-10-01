package ar.edu.unsl.fmn.gida.apis.registration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import ar.edu.unsl.fmn.gida.apis.registration.enums.Privilege;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

@Entity
@Table(name = "users")
public class User {
    // =================================== keys ===================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ================================ attributes ================================
    @Column(name = "last_name", nullable = Constraints.User.LAST_NAME_NULLABLE,
            length = Constraints.User.LAST_NAME_MAX_LENGHT)
    private String userLastName;

    @Column(name = "name", nullable = Constraints.User.NAME_NULLABLE,
            length = Constraints.User.NAME_MAX_LENGHT)
    private String userName;
    
    @Column(nullable = Constraints.User.NAME_NULLABLE, unique = Constraints.User.DNI_UNIQUE,
            length = Constraints.User.DNI_MAX_LENGHT)
    private String dni;

    @Column(nullable = Constraints.User.EMAIL_NULLABLE, unique = Constraints.User.EMAIL_UNIQUE,
            length = Constraints.User.EMAIL_MAX_LENGHT)
    private String email;

    @Column(nullable = Constraints.User.ACCOUNT_NULLABLE, unique = Constraints.User.ACCOUNT_UNIQUE,
            length = Constraints.User.ACCOUNT_MAX_LENGHT)
    private String account;

    @Column(nullable = Constraints.User.PASSWORD_NULLABLE,
            length = Constraints.User.PASSWORD_MAX_LENGHT)
    private String password;

    @Column(nullable = Constraints.User.PRIVILEGE_NULLABLE)
    private Privilege privileges = Privilege.USER;

    // ================================== extras ==================================

    // active initial value = false. The user must be activated manually from DB by an ADMIN until
    // an automatic mechanism is developed.
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

    public void setActive(boolean active) {
        this.active = active;
    }
}
