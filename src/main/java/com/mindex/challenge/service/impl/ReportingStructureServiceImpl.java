package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);
    
    @Autowired 
    private EmployeeRepository employeeRepository;

    /**
     * computes the reporting structure for an employee
     * 
     * @param employeeId the id of the employee
     * @return the reporting structure for the employee
     */
    @Override
    public ReportingStructure read(String employeeId) {  
        LOG.debug("Creating reporting structure for employee with id [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        int numOfReports = countReports(employee);
        return new ReportingStructure(employee, numOfReports);
    }

    /**
     * counts the number of reports for an employee
     * 
     * @param rootEmployee the employee to count reports for
     * @return the number of reports for the employee
     */
    private int countReports(Employee rootEmployee) {
        if (rootEmployee.getDirectReports() == null) return 0;
        
        int count = 0;
        Set<String> visited = new HashSet<>(); 
        Deque<Employee> stack = new ArrayDeque<>();

        // add the root employee's reports to the stack
        for (Employee report : rootEmployee.getDirectReports()) {
            Employee reportEmployee = employeeRepository.findByEmployeeId(report.getEmployeeId());
            if (reportEmployee != null) stack.push(reportEmployee);
        }

        while (!stack.isEmpty()) {
            Employee current = stack.pop();

            if (current == null || !visited.add(current.getEmployeeId())) continue;
            
            count++; // count the current employee

            // add the current employee's reports to the stack
            if (current.getDirectReports() != null) {
                for (Employee report : current.getDirectReports()) {
                    Employee reportEmployee = employeeRepository.findByEmployeeId(report.getEmployeeId());
                    if (reportEmployee != null && !visited.contains(reportEmployee.getEmployeeId())) stack.push(reportEmployee);
                }
            }
        }

        return count;
    }
}