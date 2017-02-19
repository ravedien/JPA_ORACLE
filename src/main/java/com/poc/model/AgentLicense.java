package com.poc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class AgentLicense {
	
	@Id
	private Integer agentLicenseId;
	
	@ManyToOne
	private Agent agent;

	public Integer getAgentLicenseId() {
		return agentLicenseId;
	}

	public void setAgentLicenseId(Integer agentLicenseId) {
		this.agentLicenseId = agentLicenseId;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		return "AgentLicense [agentLicenseId=" + agentLicenseId + ", agent=" + agent + "]";
	}
	
}
