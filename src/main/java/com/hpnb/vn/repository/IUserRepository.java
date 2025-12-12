package com.hpnb.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hpnb.vn.models.UsersModels;


public interface IUserRepository extends JpaRepository<UsersModels, String>{
	
	UsersModels findByUsername(String username);
}
