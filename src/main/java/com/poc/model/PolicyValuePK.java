package com.poc.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class PolicyValuePK implements Serializable {

	private static final long serialVersionUID = -5941541733958769955L;

	// @Column(name = "POLICY_VALUE_SUMMARY_ID")
	private Long policyValueSummaryId;

	// @Column(name = "PLAN_ATR_ID")
	private Long planAttributeId;

	public Long getPolicyValueSummaryId() {
		return policyValueSummaryId;
	}

	public void setPolicyValueSummaryId(Long policyValueSummaryId) {
		this.policyValueSummaryId = policyValueSummaryId;
	}

	public Long getPlanAttributeId() {
		return planAttributeId;
	}

	public void setPlanAttributeId(Long planAttributeId) {
		this.planAttributeId = planAttributeId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PolicyValuePK))
			return false;
		PolicyValuePK that = (PolicyValuePK) obj;
		return Objects.equals(getPolicyValueSummaryId(), that.getPolicyValueSummaryId())
				&& Objects.equals(getPlanAttributeId(), that.getPlanAttributeId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPolicyValueSummaryId(), getPlanAttributeId());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PolicyValuePK [policyValueSummaryId=").append(policyValueSummaryId).append(", planAttributeId=")
				.append(planAttributeId).append("]");
		return builder.toString();
	}

}
