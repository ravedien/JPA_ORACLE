package com.poc.repository;

import org.springframework.stereotype.Repository;

import com.poc.model.Agent;

@Repository
public class AgentDao extends AbstractJpaDAO<Agent> implements IAgentDao{

	public AgentDao() {
        super();

        setClazz(Agent.class);
    }
}
