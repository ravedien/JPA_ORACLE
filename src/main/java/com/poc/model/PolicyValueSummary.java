package com.poc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "POLICY_VALUE_SUMMARY")
public class PolicyValueSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "policyValueSummarySeq1", sequenceName = "POLICY_VALUE_SUMMARY_S1")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policyValueSummarySeq1")
	@Column(name = "POLICY_VALUE_SUMMARY_ID")
	private Long policyValueSummaryId;

	@OneToMany(mappedBy = "policyValueSummary", cascade = CascadeType.PERSIST)
	private Set<PolicyValue> policyValue;

	@Temporal(TemporalType.DATE)
	@Column(name = "EFFECTIVE_DT")
	private Date effectiveDate;

	@Column(name = "LOAD_DTTM")
	private Date loadDateTime;

	@Column(name = "OWNER_NAME")
	private String ownerName;

	@Column(name = "POLICY_STATUS_CD")
	private String policyStatusCd;

	@Column(name = "COVERAGE_CNT")
	private int coverageCount;

	@Temporal(TemporalType.DATE)
	@Column(name = "VALUATION_DT")
	private Date valudationDate;

	public PolicyValueSummary() {
	}

	public PolicyValueSummary(Long policyValueSummaryId, Set<PolicyValue> policyValue, Date effectiveDate,
			Date loadDateTime, String ownerName, String policyStatusCd, int coverageCount, Date valudationDate) {
		this.policyValueSummaryId = policyValueSummaryId;
		this.policyValue = policyValue;
		this.effectiveDate = effectiveDate;
		this.loadDateTime = loadDateTime;
		this.ownerName = ownerName;
		this.policyStatusCd = policyStatusCd;
		this.coverageCount = coverageCount;
		this.valudationDate = valudationDate;
	}

	public Long getPolicyValueSummaryId() {
		return policyValueSummaryId;
	}

	public void setPolicyValueSummaryId(Long policyValueSummaryId) {
		this.policyValueSummaryId = policyValueSummaryId;
	}

	public Set<PolicyValue> getPolicyValue() {
		return policyValue;
	}

	public void setPolicyValue(Set<PolicyValue> policyValue) {
		this.policyValue = policyValue;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getLoadDateTime() {
		return loadDateTime;
	}

	public void setLoadDateTime(Date loadDateTime) {
		this.loadDateTime = loadDateTime;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPolicyStatusCd() {
		return policyStatusCd;
	}

	public void setPolicyStatusCd(String policyStatusCd) {
		this.policyStatusCd = policyStatusCd;
	}

	public int getCoverageCount() {
		return coverageCount;
	}

	public void setCoverageCount(int coverageCount) {
		this.coverageCount = coverageCount;
	}

	public Date getValudationDate() {
		return valudationDate;
	}

	public void setValudationDate(Date valudationDate) {
		this.valudationDate = valudationDate;
	}

	public void addPolicyValue(PolicyValue child) {
		if (null == this.policyValue) {
			this.policyValue = new HashSet<PolicyValue>();
		}
		child.setPolicyValueSummary(this);
		this.policyValue.add(child);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PolicyValueSummary [policyValueSummaryId=").append(policyValueSummaryId)
				.append(", policyValue=").append(policyValue).append(", effectiveDate=").append(effectiveDate)
				.append(", loadDateTime=").append(loadDateTime).append(", ownerName=").append(ownerName)
				.append(", policyStatusCd=").append(policyStatusCd).append(", coverageCount=").append(coverageCount)
				.append(", valudationDate=").append(valudationDate).append("]");
		return builder.toString();
	}

}
