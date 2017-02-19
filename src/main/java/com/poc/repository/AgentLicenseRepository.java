/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poc.repository;

import com.poc.model.Agent;
import com.poc.model.AgentLicense;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author XSD001
 */
public interface AgentLicenseRepository extends JpaRepository<AgentLicense, Integer>{
    
}
