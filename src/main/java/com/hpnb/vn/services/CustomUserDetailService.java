package com.hpnb.vn.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hpnb.vn.models.CustomUsersDetail;
import com.hpnb.vn.models.RoleModels;
import com.hpnb.vn.models.UsersModels;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersModels users = userServiceImpl.findByUsername(username);
		if(users == null) {
			throw new UsernameNotFoundException("Sai");
		}
		Collection<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
		
		Set<RoleModels> roles = users.getRoles();
		
		for (RoleModels roleModels : roles) {
			grantedAuthoritySet.add(new SimpleGrantedAuthority(roleModels.getRoleName()));
		}
		return new CustomUsersDetail(users, grantedAuthoritySet);
	}

}
