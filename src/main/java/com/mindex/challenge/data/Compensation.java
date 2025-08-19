package com.mindex.challenge.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;

public class Compensation {
    @Id private String id; 
    private Employee employee;
    private BigDecimal salary;
    private LocalDate effectiveDate;

    public Compensation() {
    }

    public String getId() { 
        return id; 
    }

    public void setId(String id) { 
        this.id = id; 
    }

    public Employee getEmployee() { 
        return employee; 
    }

    public void setEmployee(Employee employee) { 
        this.employee = employee; 
    }

    public BigDecimal getSalary() { 
        return salary; 
    }

    public void setSalary(BigDecimal salary) { 
        this.salary = salary; 
    }

    public LocalDate getEffectiveDate() { 
        return effectiveDate; 
    }

    public void setEffectiveDate(LocalDate effectiveDate) { 
        this.effectiveDate = effectiveDate; 
    }
}