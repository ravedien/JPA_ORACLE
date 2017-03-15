package com.poc.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public interface BaseDao<T,PK>{
	
	public T findOne(PK pk);

	public List<T> findAll();
    
	public void create(T t);

	public CriteriaBuilder getBuilder();

	public CriteriaQuery<T> getBuilderCriteriaQuery();

	List<T> findWithNamedQuery(String named, Map<String, Object> params);

	void executeWithNamedQuery(String named, Map<String, Object> params);

}
