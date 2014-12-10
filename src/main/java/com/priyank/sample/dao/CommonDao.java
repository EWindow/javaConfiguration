package com.priyank.sample.dao;

import java.util.List;

public interface CommonDao<T> {

	T save(T t);

	T update(T t);

	T get(Long id);

	boolean delete(T t);

	boolean delete(Long id);

	List<T> getAll();

}
