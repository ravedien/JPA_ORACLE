package com.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.model.Agent;

public interface AgentRepository extends JpaRepository<Agent, Integer>{

}
