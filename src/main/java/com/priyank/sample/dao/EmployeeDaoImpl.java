package com.priyank.sample.dao;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.priyank.sample.domain.Employee;

@Component
@Transactional
public class EmployeeDaoImpl extends CommonDaoImpl<Employee> implements
		EmployeeDao {

	@Autowired
	public EmployeeDaoImpl(HibernateTemplate hibernateTemplate) {
		super.hibernateTemplate = hibernateTemplate;
		super.className = Employee.class;
	}

	@Override
	public Employee getByEmail(String data) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		criteria.add(Restrictions.eq("email", data));
		criteria.setFetchMode("userRoleses", FetchMode.JOIN);
		return (Employee) hibernateTemplate.findByCriteria(criteria).get(0);
	}
}
