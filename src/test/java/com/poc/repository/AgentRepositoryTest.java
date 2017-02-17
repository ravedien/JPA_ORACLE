package com.poc.repository;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.poc.model.Agent;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AgentRepositoryTest {

	private final static Logger logger = LoggerFactory.getLogger(AgentRepositoryTest.class);
	
	@Autowired
	AgentRepository agentRepository;
	
	@PersistenceContext
    private EntityManager em;

	@Test
	public void findById() {
		
//		Agent agent = new Agent();
//		agent.setAgentId(1);
//		agent.setAgentCode("Code1");
//		agent.setStartDateTime(new Date());
//		
//		agentRepository.save(agent);
		
//		Agent agentRespo = agentRepository.findOne(1);
		
//		logger.info(" :: ");
//		logger.info(agent.toString());
		
//		Agent agent = agentRepository.findOne(1);
//		
//		Agent AgentEM = em.find(Agent.class, 1);
//		
//		logger.info("Agent :: "+AgentEM.toString());
//		// should not be null
//		assertNotNull(agent);
//
//		// should equal
//		assertEquals("try", 1, agent.getAgentId());
	}

}
