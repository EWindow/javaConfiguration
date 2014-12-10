package com.priyank.sample.service;

import java.util.List;

import com.priyank.sample.dao.CommonDao;

public class CommonServiceImpl<T> implements CommonService<T> {

	private CommonDao<T> commonDao;

	public CommonServiceImpl(CommonDao<T> commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public T save(T t) {
		return commonDao.save(t);
	}

	@Override
	public T update(T t) {
		return commonDao.update(t);
	}

	@Override
	public T get(Long id) {
		return commonDao.get(id);
	}

	@Override
	public boolean delete(T t) {
		return commonDao.delete(t);
	}

	@Override
	public boolean delete(Long id) {
		return commonDao.delete(id);
	}

	@Override
	public List<T> getAll() {
		return commonDao.getAll();
	}

}
