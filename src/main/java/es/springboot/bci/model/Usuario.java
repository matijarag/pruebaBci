package es.springboot.bci.model;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "created")
	private Date created;
	
	@Column(name = "modified")
	private Date modified;
	
	@Column(name = "last_login")
	private Date last_login;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "is_active")
	private boolean is_active;
	
	public Usuario() {
		
	}

	public Usuario(String name, String email, String password, String phone, Date created, Date modified,
			Date last_login, String token, boolean is_active) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.created = created;
		this.modified = modified;
		this.last_login = last_login;
		this.token = token;
		this.is_active = is_active;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone="
				+ phone + ", created=" + created + ", modified=" + modified + ", last_login=" + last_login + ", token="
				+ token + ", is_active=" + is_active + "]";
	}

	
}
