package com.poc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "AGENT")
//@NamedQuery(name= Agent.QUERY_FIND_AGENTPK, 
//query="SELECT a FROM Agent a JOIN FETCH a.agentLicenses al where a.agentId = :"+Agent.PARAM_AGENT_ID+ " and a.startDateTime = :"+Agent.PARAM_START_DATE_TIME)
//@NamedEntityGraph(name="GRAPH_AGENT_LICENSES",
//attributeNodes = @NamedAttributeNode(value="agentLicenses")
//)
public class Agent implements Serializable{

//	public static final String QUERY_FIND_AGENTPK="Agent.findById";
//	public static final String PARAM_AGENT_ID="agentId";
//	public static final String PARAM_START_DATE_TIME="startDateTime";
//	
//	public static final String GRAPH_AGENT_LICENSES = "graph.Agent.licenses";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8607159185588739951L;

	private AgentPK agentPK;
	private String agentCode;
	private Set<AgentLicense> agentLicenses = new HashSet<AgentLicense>();
	private Date endDateTime;

	public Agent() {
	}

	public Agent(AgentPK agentPK) {
		super();
		this.agentPK = agentPK;
	}

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "agentId", column = @Column(name = "AGENT_ID", nullable = false)),
		@AttributeOverride(name = "startDateTime", column = @Column(name = "START_DTTM", nullable = false)) })
	public AgentPK getAgentPK() {
		return agentPK;
	}

	public void setAgentPK(AgentPK agentPK) {
		this.agentPK = agentPK;
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DTTM")
	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	@Column(name = "AGENT_CD")
	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "agent",cascade=CascadeType.ALL)
	public Set<AgentLicense> getAgentLicenses() {
		return agentLicenses;
	}

	public void setAgentLicenses(Set<AgentLicense> agentLicenses) {
		this.agentLicenses = agentLicenses;
	}
	
	public void addAgentLicense(AgentLicense license){
		this.agentLicenses.add(license);
	}

	@Override
	public String toString() {
		return "Agent [agentPK=" + agentPK + ", agentCode=" + agentCode + ", agentLicenses=" + agentLicenses
				+ ", endDateTime=" + endDateTime + "]";
	}

}
