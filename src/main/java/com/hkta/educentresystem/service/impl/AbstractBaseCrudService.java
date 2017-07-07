package com.hkta.educentresystem.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hkta.educentresystem.service.BaseCrudService;

public abstract class AbstractBaseCrudService<T, ID extends Serializable> implements BaseCrudService<T, ID> {
	
	abstract PagingAndSortingRepository<T, ID> getRepository();
	
	@Override
	public void delete(T deleted) {
		this.getRepository().delete(deleted);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.getRepository().findAll();
	}

	@Override
	public T findOne(ID id) {
		return this.getRepository().findOne(id);
	}

	@Override
	public T save(T persisted) {
		return this.getRepository().save(persisted);
	}
	
	@Override
	public Page<T> findAllPaged(int page, int size) {
		return (Page<T>) this.getRepository().findAll(new PageRequest(page, size));
	}
}
