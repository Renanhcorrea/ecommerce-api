package com.ecommerce.service;

import com.ecommerce.model.Employee;
import com.ecommerce.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Create
    @Transactional
    public Employee createEmployee(Employee employee){
        if(employee.getName() == null || employee.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Name must NOT be empty.");
        }
        if(employee.getLogin() == null || employee.getLogin().trim().isEmpty()){
            throw new IllegalArgumentException("Login must NOT be empty.");
        }
        if(employee.getPassword() == null || employee.getPassword().trim().isEmpty()){
            throw new IllegalArgumentException("Password must NOT be empty.");
        }
        /*if(employee.getEmail()==null || employee.getEmail().isEmpty()){
            throw new IllegalArgumentException("Email must NOT be empty.");
        }*/
        if(employee.getRole()==null || employee.getRole().trim().isEmpty()){
            throw new IllegalArgumentException("Role must NOT be empty.");
        }

        return employeeRepository.save(employee);
    }

    // Find by ID
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Employee not found with ID: " + id));
    }

    // Login
    public Employee loginEmployee(String login, String password){
        Employee employee = employeeRepository.findByLogin(login);
        if(employee != null && employee.getPassword().equals(password)){
            return employee;
        } else {
            throw new RuntimeException("Invalid Login or Password");
        }
    }

    //

    // Find All
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    // Update
    @Transactional
    public Employee updateEmployee(Long id, Employee employee){
        if(!employeeRepository.existsById(id)){
            throw new RuntimeException("Employee not found with ID " + id);
        }
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    // Delete
    @Transactional
    public void deleteEmployeeById(Long id){
        if(!employeeRepository.existsById(id)){
            throw new RuntimeException("Employee not found with ID " + id);
        }
        employeeRepository.deleteById(id);
    }

}
