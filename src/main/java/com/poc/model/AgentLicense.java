package com.poc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AGENT_LICENSE")
public class AgentLicense implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 160280185401633177L;

	
	private Integer agentLicenseId;
	private Agent agent;

	@Id
	@Column(name = "AGENT_LICENSE_ID", unique = true, nullable = false)
	public Integer getAgentLicenseId() {
		return agentLicenseId;
	}

	public void setAgentLicenseId(Integer agentLicenseId) {
		this.agentLicenseId = agentLicenseId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "AGENT_ID", referencedColumnName = "AGENT_ID"),
		@JoinColumn(name = "AGENT_START_DTTM", referencedColumnName = "START_DTTM") })
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
