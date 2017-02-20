package com.poc.repository;

import java.io.Serializable;
import java.util.List;

import com.poc.model.Agent;

public interface IAgentDao{
	
	Agent findOne(Serializable pk);

    List<Agent> findAll();

}
