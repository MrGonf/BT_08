package com.hpnb.vn.services;

import com.hpnb.vn.models.UsersModels;

public interface IUserService  {
	UsersModels findByUsername(String username);

}
