package com.poc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poc.model.Agent;
import com.poc.model.AgentPK;

public interface AgentRepository extends JpaRepository<Agent, AgentPK>{
    
    @Query("SELECT a FROM Agent a JOIN FETCH a.agentLicenses al where a.agentId = :agentId and a.startDateTime = :startDateTime")
    public List<Agent> fetchAgentAndLicense(@Param("agentId")Integer agentId, @Param("startDateTime")Date startDateTime);
    
}
