package com.priyank.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

import com.priyank.sample.dao.CommonDao;
import com.priyank.sample.dao.EmployeeDao;
import com.priyank.sample.domain.Employee;

@Service
public class EmployeeServiceImpl extends CommonServiceImpl<Employee> implements
		EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	public EmployeeServiceImpl(CommonDao<Employee> commonDao) {
		super(commonDao);
	}

	@Override
	public Employee getByEmail(String data) {
		return employeeDao.getByEmail(data);
	}
	
	@Override
	@PostFilter("hasPermission(filterObject, 'read')")
	public List<Employee> getAll() {
		return super.getAll();
	}

}
