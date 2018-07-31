package br.com.uaijug.appex.appex.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole extends AudityEntity {

	private static final long serialVersionUID = -2719226491222823385L;

	@Column(name = "role_name")
	private String roleName;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public UserRole() {
	}

	public UserRole(String roleName, User user) {
		this.roleName = roleName;
		this.user = user;
	}

	public Long getId() {
		return super.getId();
	}

	public void setId(Long id) {
		super.setId(id);
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}