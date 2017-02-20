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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(AgentPK.class)
@NamedQuery(name= Agent.QUERY_FIND_AGENTPK, 
			query="SELECT a FROM Agent a JOIN FETCH a.agentLicenses al where a.agentId = :"+Agent.PARAM_AGENT_ID+ " and a.startDateTime = :"+Agent.PARAM_START_DATE_TIME)
@NamedEntityGraph(name="GRAPH_AGENT_LICENSES",
			attributeNodes = @NamedAttributeNode(value="agentLicenses")
		)
public class Agent {
	
	public static final String QUERY_FIND_AGENTPK="Agent.findById";
	public static final String PARAM_AGENT_ID="agentId";
	public static final String PARAM_START_DATE_TIME="startDateTime";
	
	public static final String GRAPH_AGENT_LICENSES = "graph.Agent.licenses";
	
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
