package com.priyank.sample.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.priyank.sample.dao.CommonDao;
import com.priyank.sample.domain.UserRoles;

public class UserRolesServiceImpl extends CommonServiceImpl<UserRoles>
		implements UserRolesService {
	@Autowired
	public UserRolesServiceImpl(CommonDao<UserRoles> commonDao) {
		super(commonDao);
	}

}
