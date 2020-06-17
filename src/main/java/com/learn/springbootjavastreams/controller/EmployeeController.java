package com.learn.springbootjavastreams.controller;

import com.learn.springbootjavastreams.ResourceNotFoundException;
import com.learn.springbootjavastreams.model.Employee;
import com.learn.springbootjavastreams.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    //List all Employees
    @GetMapping("/employees")
    public List<Employee> listAllEmployees(){
        return employeeRepository.findAll();
    }

    //Create an Employee
    @PostMapping("/employees")
    public Employee createEmployee(@Validated @RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    //Find an Employee
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Unable to find the Employee ID ::" + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    //Update an Employee
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable(value = "id") long employeeId, @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow( () -> new ResourceNotFoundException("Unable to find the employee Id to update :: " + employeeId));
        employee.setEmployeeName(employeeDetails.getEmployeeName());
        employeeDetails.setEmployeeSalary(employeeDetails.getEmployeeSalary());
        employeeRepository.save(employee);
        return ResponseEntity.ok().body(employeeDetails);
    }

    //Delete Employee by Id
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable long id) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Unable to find the employee Id to update :: " + id));
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
