package com.hkta.educentresystem.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

public interface BaseCrudService<T, ID extends Serializable> {
	public void delete(T deleted);

	public List<T> findAll();

	public T findOne(ID id);

	public T save(T persisted);

	public Page<T> findAllPaged(int page, int size);
}
