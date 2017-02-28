package com.poc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class AgentPK implements Serializable{

	private static final long serialVersionUID = 4168129429453803296L;
	
	private Integer agentId;
	
	private Date startDateTime;
	
	public AgentPK() {}

    public AgentPK(Integer agentId, Date startDateTime) {
        this.agentId = agentId;
        this.startDateTime = startDateTime;
    }
	
    @Column(name = "AGENT_ID", nullable = false, precision = 10, scale = 0)
	public Integer getAgentId() {
		return agentId;
	}
    
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	
	@Column(name = "START_DTTM", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if (!(obj instanceof AgentPK)) return false;
        AgentPK that = (AgentPK) obj;
        return Objects.equals(getAgentId(), that.getAgentId()) &&
               Objects.equals(getStartDateTime(), that.getStartDateTime());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getAgentId(), getStartDateTime());
	}
}
