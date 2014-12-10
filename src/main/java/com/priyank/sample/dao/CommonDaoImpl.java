/**
 * 
 */
package com.priyank.sample.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author priyank
 * 
 */
public class CommonDaoImpl<T> implements CommonDao<T> {

	public HibernateTemplate hibernateTemplate;
	public Class<T> className;

//	public CommonDaoImpl(HibernateTemplate hibernateTemplate, Class<T> className) {
//		this.hibernateTemplate = hibernateTemplate;
//		this.className = className;
//	}

	@Override
	public T save(T t) {
		Serializable id = hibernateTemplate.save(t);
		return (T) hibernateTemplate.get(className, id);
	}

	@Override
	public T update(T t) {
		hibernateTemplate.update(t);
		return t;
	}

	@Override
	public T get(Long id) {
		return hibernateTemplate.get(className, id.intValue());
	}

	@Override
	public boolean delete(T t) {
		hibernateTemplate.delete(t);
		return true;
	}

	@Override
	public boolean delete(Long id) {
		T t = hibernateTemplate.get(className, id.intValue());
		hibernateTemplate.delete(t);
		return true;
	}

	@Override
	public List<T> getAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(className);;
		return (List<T>)hibernateTemplate.findByCriteria(criteria);
	}

}
