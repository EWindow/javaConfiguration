package com.priyank.sample.service;

import java.util.List;

public interface CommonService<T> {
	List<T> getAll();

	T save(T t);

	T update(T t);

	T get(Long id);

	boolean delete(T t);

	boolean delete(Long id);
}
