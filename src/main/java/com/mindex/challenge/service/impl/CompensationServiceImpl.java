package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired private CompensationRepository compensationRepository;

    @Autowired private EmployeeRepository employeeRepository;

    /**
     * creates a compensation record for an employee
     * 
     * @param compensation the compensation record to create
     * @return the created compensation record
     */
    @Override
    public Compensation create(Compensation compensation) { 
        LOG.debug("Creating conpensation [{}]", compensation);

        if (compensation.getEmployee() == null || compensation.getEmployee().getEmployeeId() == null) {
            throw new RuntimeException("Compensation must reference an employee with employeeId");
        }

        Employee attached = employeeRepository.findByEmployeeId(compensation.getEmployee().getEmployeeId());
        
        if (attached == null) {
            throw new RuntimeException("Invalid employeeId: " + compensation.getEmployee().getEmployeeId());
        }

        compensation.setEmployee(attached);
        return compensationRepository.insert(compensation);
    }

    /**
     * reads a compensation record for an employee
     * 
     * @param id the id of the employee
     * @return the compensation record for the employee
     */
    @Override
    public Compensation read(String id) { 
        LOG.debug("Creating conpensation for employee with id [{}]", id);

        Compensation compensation = compensationRepository.findByEmployee_EmployeeId(id);

        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return compensation;
    }
}