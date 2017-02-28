package com.poc.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public interface BaseDao<T,PK>{
	
	public T findOne(PK pk);

	public List<T> findAll();
    
	public void create(T t);

	public CriteriaBuilder getBuilder();

	public CriteriaQuery<T> getBuilderCriteriaQuery();

}
