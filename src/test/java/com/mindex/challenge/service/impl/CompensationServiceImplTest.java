package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.*; 
import com.mindex.challenge.service.CompensationService;
import org.junit.*; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired; 
import java.math.BigDecimal; 
import java.time.LocalDate;
import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) 
@SpringBootTest
public class CompensationServiceImplTest {

    @Autowired 
    private CompensationService service;

    @Test 
    public void testCreateRead() {
        String id = "16a596ae-edd3-4847-99fe-c4518e82c86f"; 
        Compensation c = new Compensation();
        Employee e = new Employee(); 

        e.setEmployeeId(id); 
        c.setEmployee(e);
        c.setSalary(new BigDecimal("100200.23")); 
        c.setEffectiveDate(LocalDate.parse("2025-08-01"));
        
        // Create checks
        Compensation created = service.create(c); 

        assertNotNull(created.getId());
        assertEquals(id, created.getEmployee().getEmployeeId());
        assertEquals(0, c.getSalary().compareTo(created.getSalary()));
        assertEquals(c.getEffectiveDate(), created.getEffectiveDate());
        
        // Read checks
        Compensation read = service.read(id);

        assertEquals(0, created.getSalary().compareTo(read.getSalary()));
        assertEquals(created.getEffectiveDate(), read.getEffectiveDate());
    }
}