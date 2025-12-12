package com.hpnb.vn.models;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomUsersDetail implements UserDetails{


	private static final long serialVersionUID = 1L;
	
	private UsersModels users;
	private  Collection<? extends GrantedAuthority> authorities;
	
	
	
	public CustomUsersDetail() {
		super();
	}

	public CustomUsersDetail(UsersModels users, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.users = users;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return users.getUsername();
	}

}
