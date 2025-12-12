package com.hpnb.vn.models;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleId")
    private Long roleId;

    @Column(name = "RoleName", length = 50, nullable = false)
    private String roleName;

    @Column(name = "Description", length = 255)
    private String description;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore  // Ngăn vòng lặp JSON
    private Set<UsersModels> users;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<UsersModels> getUsers() {
		return users;
	}

	public void setUsers(Set<UsersModels> users) {
		this.users = users;
	}

    
    
}
