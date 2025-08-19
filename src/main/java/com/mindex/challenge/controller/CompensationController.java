package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for compensation
 */
@RestController
public class CompensationController {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired 
    private CompensationService service;

    /**
     * POST endpoint for creating a compensation record
     * 
     * @param c the compensation record to create
     * @return the created compensation record
     */
    @PostMapping("/compensation") 
    public Compensation create(@RequestBody Compensation c){ 
        LOG.debug("Creating compensation [{}]", c);

        return service.create(c); 
    }

    /**
     * GET endpoint for reading a compensation record
     * 
     * @param employeeId the id of the employee
     * @return the compensation record for the employee
     */
    @GetMapping("/compensation/{employeeId}") 
    public Compensation read(@PathVariable String employeeId){ 
        LOG.debug("Reading compensation for employee with id [{}]", employeeId);

        return service.read(employeeId); 
    }
}