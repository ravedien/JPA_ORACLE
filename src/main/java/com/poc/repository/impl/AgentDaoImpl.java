package com.poc.repository.impl;

import org.springframework.stereotype.Repository;

import com.poc.model.Agent;
import com.poc.model.AgentPK;
import com.poc.repository.AgentDao;

@Repository
public class AgentDaoImpl extends AbstractJpaDAOImpl<Agent,AgentPK> implements AgentDao{

}
