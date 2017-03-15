package com.poc.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.poc.repository.BaseDao;

public abstract class AbstractJpaDAOImpl<T extends Serializable, PK extends Serializable> implements BaseDao<T,PK>{
	
    @PersistenceContext
    protected EntityManager entityManager;
    private Class<T> clazz;

    @SuppressWarnings("unchecked")
	public AbstractJpaDAOImpl() {
		try {

			Type t = getClass().getGenericSuperclass();
			ParameterizedType pt = (ParameterizedType) t;
			this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void create(T entity){
    	entityManager.persist(entity);
    }
    
    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public T findOne(PK pk) {
        return entityManager.find(clazz, pk);
    }

    @Override
    @SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }
    
    @Override
	public CriteriaBuilder getBuilder() {
		return entityManager.getCriteriaBuilder();
	}
    
    @Override
    public CriteriaQuery<T> getBuilderCriteriaQuery(){        
        return getBuilder().createQuery(clazz);
	}
    
    @Override
    public List<T> findWithNamedQuery(String named, Map<String,Object> params){
    	TypedQuery<T> query = entityManager.createNamedQuery(named, clazz);
    	if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, Object> entrySet : params.entrySet()) {
                query.setParameter(entrySet.getKey(), entrySet.getValue());
            }
        }

        return query.getResultList();
    	
    }
    
    @Override
    public void executeWithNamedQuery(String named, Map<String, Object> params) {
        Query q = entityManager.createNamedQuery(named);

        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, Object> entrySet : params.entrySet()) {
                q.setParameter(entrySet.getKey(), entrySet.getValue());
            }
        }

        q.executeUpdate();
    }
}
