package com.ecommerce.controller;

import com.ecommerce.entity.Employee;
import com.ecommerce.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, @RequestParam String taxIndentifier){
        return new ResponseEntity<>(employeeService.createEmployee(employee, taxIndentifier), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(employeeService.getEmployeeById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{login}")
    public ResponseEntity<Employee> loginEmployee(@RequestBody Employee employee){
        try {
            return ResponseEntity.ok(employeeService.loginEmployee(employee.getLogin(), employee.getPassword()));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable String email){
        try {
            return ResponseEntity.ok(employeeService.getEmployeeByEmail(email));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee, @RequestParam(required = false) String newTaxIdentifier){
        try{
            return ResponseEntity.ok(employeeService.updateEmployee(id, employee, newTaxIdentifier));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id){
        try {
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}
