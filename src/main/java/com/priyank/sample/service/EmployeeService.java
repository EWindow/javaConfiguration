package com.priyank.sample.service;

import com.priyank.sample.domain.Employee;

public interface EmployeeService extends CommonService<Employee> {

	Employee getByEmail(String data);
}
