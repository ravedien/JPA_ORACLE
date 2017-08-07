package com.poc.repository.impl;

import org.springframework.stereotype.Repository;

import com.poc.model.PolicyValueSummary;
import com.poc.repository.PolicyValueSummaryDao;

@Repository
public class PolicyValueSummaryDaoImpl extends AbstractJpaDAOImpl<PolicyValueSummary,Long> implements PolicyValueSummaryDao{

}
