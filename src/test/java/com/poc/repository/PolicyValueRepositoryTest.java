package com.poc.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.poc.model.PolicyValue;
import com.poc.model.PolicyValuePK;
import com.poc.model.PolicyValueSummary;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PolicyValueRepositoryTest {

	private final static Logger logger = LoggerFactory.getLogger(PolicyValueRepositoryTest.class);

	@Autowired
	private PolicyValueSummaryDao dao;

	@Test
	public void save() {
		PolicyValueSummary summary = new PolicyValueSummary();
		Date now = new Date();
		// summary.setPolicyValueSummaryId(new Long(1));
		summary.setEffectiveDate(now);
		summary.setLoadDateTime(now);
		summary.setOwnerName("HP");
		summary.setPolicyStatusCd("OK");
		summary.setCoverageCount(1);
		summary.setValudationDate(now);

		PolicyValuePK pk = new PolicyValuePK();
		// pk.setPolicyValueSummaryId(new Long(1));
		pk.setPlanAttributeId(new Long(2));

		PolicyValue value = new PolicyValue();
		value.setPolicyValuePK(pk);
		value.setPolicyValueAmount(new BigDecimal("1"));

		summary.addPolicyValue(value);
		dao.create(summary);

	}
}
