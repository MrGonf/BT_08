package com.hpnb.vn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpnb.vn.models.UsersModels;
import com.hpnb.vn.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	@Override
	public UsersModels findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

}
