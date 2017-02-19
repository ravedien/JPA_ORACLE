package com.poc.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(AgentPK.class)
public class Agent {
	
	@Id
	@Column(name="AGENT_ID")
	private Integer agentId;
	
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_DTTM")
	private Date startDateTime;

	@Column(name="AGENT_CD")
	private String agentCode;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumns({
		@JoinColumn(name="AGENT_AGENT_ID", referencedColumnName="AGENT_ID"),
                @JoinColumn(name="AGENT_START_DTTM", referencedColumnName="START_DTTM")
	})
	private Set<AgentLicense> agentLicenses;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="END_DTTM")
	private Date endDateTime;
	
	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	
	public Set<AgentLicense> getAgentLicenses() {
		return agentLicenses;
	}

	public void setAgentLicenses(Set<AgentLicense> agentLicenses) {
		this.agentLicenses = agentLicenses;
	}

	@Override
	public String toString() {
		return "Agent [agentId=" + agentId + ", startDateTime=" + startDateTime + ", agentCode=" + agentCode
				+ ", endDateTime=" + endDateTime + "]";
	}

}
