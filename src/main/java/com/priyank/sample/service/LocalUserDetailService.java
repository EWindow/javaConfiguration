package com.priyank.sample.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.priyank.sample.bean.CustomUserBean;
import com.priyank.sample.domain.Employee;
import com.priyank.sample.domain.UserRoles;

@Component
public class LocalUserDetailService implements UserDetailsService {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public CustomUserBean loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		Employee employee = employeeService.getByEmail(userName);
		if (employee == null) {
			throw new UsernameNotFoundException("User with user name :"
					+ userName + " not found");
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (UserRoles roles : employee.getUserRoleses()) {
			authorities.add(new SimpleGrantedAuthority(roles.getRoleName()));
		}
		CustomUserBean user = new CustomUserBean(userName,
				employee.getPassword(), true, true, true, true, authorities,
				employee);
		return user;
	}

}
