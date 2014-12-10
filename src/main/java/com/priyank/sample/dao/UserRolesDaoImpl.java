package com.priyank.sample.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.priyank.sample.domain.UserRoles;

@Component
@Transactional
public class UserRolesDaoImpl extends CommonDaoImpl<UserRoles> implements
		UserRolesDao {

	@Autowired
	public UserRolesDaoImpl(HibernateTemplate hibernateTemplate) {
		super.hibernateTemplate = hibernateTemplate;
		super.className = UserRoles.class;
	}

}
