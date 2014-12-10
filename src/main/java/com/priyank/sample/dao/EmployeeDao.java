package com.priyank.sample.dao;

import com.priyank.sample.domain.Employee;

public interface EmployeeDao extends CommonDao<Employee> {

	Employee getByEmail(String data);

}
