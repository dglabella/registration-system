package ar.edu.unsl.fmn.gida.apis.registration.model;

import java.beans.Transient;
import java.util.Arrays;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ar.edu.unsl.fmn.gida.apis.registration.enums.Privilege;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

@Entity
@Table(name = "users")
public class User implements UserDetails {
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

	@javax.persistence.Transient
	private String oldPassword;

	@Enumerated(EnumType.ORDINAL)
	private Privilege privilege = Privilege.ROLE_USER;

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

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getOldPassword() {
		return this.oldPassword;
	}

	public Privilege getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(privilege.name()));
	}

	@Override
	@Transient
	public String getUsername() {
		return this.account;
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		// account expiration not implemented yet
		return true;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		// locking account not implemented yet
		return true;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		// credentials expiration not implemented yet
		return true;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		return this.active;
	}

	public void setEnabled(boolean value) {
		this.active = value;
	}
}
