package com.poc.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.poc.model.Agent;
import com.poc.model.AgentLicense;
import com.poc.model.AgentPK;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AgentRepositoryTest {

    private final static Logger logger = LoggerFactory.getLogger(AgentRepositoryTest.class);

    @Autowired
    AgentRepository agentRepository;
    
    @Autowired
    AgentLicenseRepository licenseRepository;
    
    @Autowired
    EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private IAgentDao agentDao;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

//    @Test
    public void save() throws ParseException {

        Agent agent = new Agent();
        agent.setAgentId(1);
        agent.setAgentCode("C1");
        agent.setStartDateTime(new Date());
        
        AgentLicense license = new AgentLicense();
        license.setAgentLicenseId(69);
        
        Set<AgentLicense> licenseList = new HashSet<>();
        licenseList.add(license);
        agent.setAgentLicenses(licenseList);
        
        agentRepository.save(agent);
        
    }
    
//    @Test
    public void simpleLicenseSearch(){
       AgentLicense license = licenseRepository.findOne(69);
        logger.info(license.toString());
    }
    
//    @Test
    public void simpleAgentSearch() throws ParseException{
        Agent agent = agentRepository.findOne(new AgentPK(1, sdf.parse("2017-02-20 13:08:22.091")));
        logger.info("com.poc.repository.AgentRepositoryTest.simpleAgentSearch()"+agent.toString());
    }
    
//    @Test
    public void fetchAgentAndLicenseRepo() throws ParseException{
        List<Agent> agentList = agentRepository.fetchAgentAndLicense(1, sdf.parse("2017-02-20 13:08:22.091"));
        logger.info("com.poc.repository.AgentRepositoryTest.fetchAgentAndLicense()"+agentList);
    }
    
//    @Test
    public void fetchAgentAndLicense() throws ParseException{
        TypedQuery<Agent> query = em.createQuery("SELECT a FROM Agent a JOIN FETCH a.agentLicenses al where a.agentId = :agentId and a.startDateTime = :startDateTime", Agent.class);
        query.setParameter("agentId", 1);
        query.setParameter("startDateTime", sdf.parse("2017-02-20 13:08:22.091"));
        Agent agent = query.getSingleResult();
        logger.info("com.poc.repository.AgentRepositoryTest.fetchAgentAndLicense()"+agent);
    }

//    @Test
    public void fetchAgentAndLicenseApi() throws ParseException{
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Agent> cq = cb.createQuery(Agent.class);
        Root<Agent> root = cq.from(Agent.class);
        root.fetch("agentLicenses");
        Predicate pre1 = cb.equal(root.get("agentId"), 1);
        Predicate pre2 = cb.equal(root.get("startDateTime"), sdf.parse("2017-02-20 13:08:22.091"));
        cq.where(cb.and(pre1,pre2));
        
        TypedQuery<Agent> aQuery = em.createQuery(cq);
        Agent agent = aQuery.getSingleResult();
        logger.info("com.poc.repository.AgentRepositoryTest.fetchAgentAndLicense()"+agent);
        logger.info("com.poc.repository.AgentRepositoryTest.fetchAgentAndLicenseApi()"+agent.getAgentLicenses());
        
    }
    
//    @Test
    public void fetchAgentAndLicenseEntityGraph() throws ParseException{
    	EntityGraph<?> agentGraph = em.getEntityGraph(Agent.GRAPH_AGENT_LICENSES);
    	Map<String,Object> hints = new HashMap<>();
    	hints.put("javax.persistence.fetchgraph", agentGraph);
    	Agent agent = em.find(Agent.class, new AgentPK(1, sdf.parse("2017-02-20 13:08:22.091")), hints);
    	logger.info(agent.toString());
    	logger.info(agent.getAgentLicenses().toString());
    }
    
//    @Test
    public void fetchAgentAndLicenseDynamicEntityGraph() throws ParseException{
    	EntityGraph<Agent> agentGraph =em.createEntityGraph(Agent.class);
    	agentGraph.addAttributeNodes("agentLicenses");
    	
    	Map<String,Object> hints = new HashMap<>();
    	hints.put("javax.persistence.loadgraph", agentGraph);
    	Agent agent = em.find(Agent.class, new AgentPK(1, sdf.parse("2017-02-20 13:08:22.091")), hints);
    	logger.info(agent.toString());
    	logger.info(agent.getAgentLicenses().toString());
    }
    
//    @Test
    public void findOneByDao() throws ParseException{
    	Agent agent = agentDao.findOne(new AgentPK(1, sdf.parse("2017-02-20 13:08:22.091")) );
    	logger.info(agent.toString());
    	
    }
    
//    @Test
    public void namedQueryFindById() throws ParseException{
    	TypedQuery<Agent> query = em.createNamedQuery(Agent.QUERY_FIND_AGENTPK , Agent.class);
    	query.setParameter(Agent.PARAM_AGENT_ID, 1);
    	query.setParameter(Agent.PARAM_START_DATE_TIME, sdf.parse("2017-02-20 13:08:22.091"));
    	Agent agent = query.getSingleResult();
    	System.out.println("AGENT :: "+agent.toString());
    	System.out.println("AGENT LICENSE :: "+agent.getAgentLicenses());
    }
}
