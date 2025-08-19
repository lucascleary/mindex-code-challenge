package com.mindex.challenge.service.impl;

import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.*; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) 
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    @Autowired 
    private ReportingStructureService service;

    @Test 
    public void testRead() {
        String id = "16a596ae-edd3-4847-99fe-c4518e82c86f"; 
        ReportingStructure rs = service.read(id);

        assertNotNull(rs);
        assertNotNull(rs.getEmployee());
        assertEquals(id, rs.getEmployee().getEmployeeId());
        assertEquals(4, rs.getNumberOfReports());
    }
}