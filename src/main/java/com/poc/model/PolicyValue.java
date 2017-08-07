package com.poc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "POLICY_VALUE")
public class PolicyValue implements Serializable{

	private static final long serialVersionUID = 1L;

	@AttributeOverrides({
			@AttributeOverride(name = "policyValueSummaryId", column = @Column(name = "POLICY_VALUE_SUMMARY_ID")),
			@AttributeOverride(name = "planAttributeId", column = @Column(name = "PLAN_ATR_ID")) })
	@EmbeddedId
	private PolicyValuePK policyValuePK;

	@MapsId("policyValueSummaryId")
	@JoinColumn(name = "POLICY_VALUE_SUMMARY_ID", referencedColumnName = "POLICY_VALUE_SUMMARY_ID", insertable = false, updatable = false)
	@ManyToOne
	private PolicyValueSummary policyValueSummary;

	@Column(name = "POLICY_VALUE_AMT")
	private BigDecimal policyValueAmount;

	public PolicyValue() {
	}

	public PolicyValue(PolicyValuePK policyValuePK, PolicyValueSummary policyValueSummary,
			BigDecimal policyValueAmount) {
		super();
		this.policyValuePK = policyValuePK;
		this.policyValueSummary = policyValueSummary;
		this.policyValueAmount = policyValueAmount;
	}

	public PolicyValuePK getPolicyValuePK() {
		return policyValuePK;
	}

	public void setPolicyValuePK(PolicyValuePK policyValuePK) {
		this.policyValuePK = policyValuePK;
	}

	public PolicyValueSummary getPolicyValueSummary() {
		return policyValueSummary;
	}

	public void setPolicyValueSummary(PolicyValueSummary policyValueSummary) {
		this.policyValueSummary = policyValueSummary;
	}

	public BigDecimal getPolicyValueAmount() {
		return policyValueAmount;
	}

	public void setPolicyValueAmount(BigDecimal policyValueAmount) {
		this.policyValueAmount = policyValueAmount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PolicyValue [policyValuePK=").append(policyValuePK).append(", policyValueSummary=")
				.append(policyValueSummary).append(", policyValueAmount=").append(policyValueAmount).append("]");
		return builder.toString();
	}

}
