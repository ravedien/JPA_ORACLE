package com.poc.repository;

import com.poc.model.Agent;
import com.poc.model.AgentLicense;
import com.poc.model.AgentPK;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        System.out.println(license);
    }
    
//    @Test
    public void simpleAgentSearch() throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Agent agent = agentRepository.findOne(new AgentPK(1, sdf.parse("2017-02-19 20:38:31.731")));
        System.out.println("com.poc.repository.AgentRepositoryTest.simpleAgentSearch()"+agent.toString());
    }
    
//    @Test
    public void fetchAgentAndLicenseRepo() throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        List<Agent> agentList = agentRepository.fetchAgentAndLicense(1, sdf.parse("2017-02-19 20:38:31.731"));
        System.out.println("com.poc.repository.AgentRepositoryTest.fetchAgentAndLicense()"+agentList);
    }
    
//    @Test
    public void fetchAgentAndLicense() throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        TypedQuery<Agent> query = em.createQuery("SELECT a FROM Agent a JOIN FETCH a.agentLicenses al where a.agentId = :agentId and a.startDateTime = :startDateTime", Agent.class);
        query.setParameter("agentId", 1);
        query.setParameter("startDateTime", sdf.parse("2017-02-19 20:38:31.731"));
        Agent agent = query.getSingleResult();
        System.out.println("com.poc.repository.AgentRepositoryTest.fetchAgentAndLicense()"+agent);
    }

    @Test
    public void fetchAgentAndLicenseApi() throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Agent> cq = cb.createQuery(Agent.class);
        Root<Agent> root = cq.from(Agent.class);
        root.fetch("agentLicenses");
        Predicate pre1 = cb.equal(root.get("agentId"), 1);
        Predicate pre2 = cb.equal(root.get("startDateTime"), sdf.parse("2017-02-19 20:38:31.731"));
        cq.where(cb.and(pre1,pre2));
        
        TypedQuery<Agent> aQuery = em.createQuery(cq);
        Agent agent = aQuery.getSingleResult();
        System.out.println("com.poc.repository.AgentRepositoryTest.fetchAgentAndLicense()"+agent);
        System.out.println("com.poc.repository.AgentRepositoryTest.fetchAgentAndLicenseApi()"+agent.getAgentLicenses());
        
//        Define Graph POJO
//        graph.addSubgraph()
//
    }
}
