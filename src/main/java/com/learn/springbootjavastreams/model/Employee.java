package com.learn.springbootjavastreams.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "employeeDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId;
    @Column(name = "employeeName")
    private String employeeName;
    @Column(name = "employeeSalary")
    private BigDecimal employeeSalary;
}
