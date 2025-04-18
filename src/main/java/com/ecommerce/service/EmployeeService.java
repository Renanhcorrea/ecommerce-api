package com.ecommerce.service;

import com.ecommerce.entity.Employee;
import com.ecommerce.entity.identifier.CPF;
import com.ecommerce.entity.identifier.TaxIdentifier;
import com.ecommerce.validation.CPFValidation;
import com.ecommerce.repository.EmployeeRepository;
import com.ecommerce.repository.identifier.CPFRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    private final CPFValidation cpfValidation;
    private final CPFRepository cpfRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(CPFValidation cpfValidation,
                           CPFRepository cpfRepository,
                           EmployeeRepository employeeRepository){
        this.cpfValidation = cpfValidation;
        this.cpfRepository = cpfRepository;
        this.employeeRepository = employeeRepository;
    }

    // Create
    @Transactional
    public Employee createEmployee(Employee employee, String taxIdentifier){
        if (employee.getName() == null || employee.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Name must NOT be empty.");
        }
        if (employee.getLogin() == null || employee.getLogin().trim().isEmpty()){
            throw new IllegalArgumentException("Login must NOT be empty.");
        }
        if (employee.getPassword() == null || employee.getPassword().trim().isEmpty()){
            throw new IllegalArgumentException("Password must NOT be empty.");
        }
        if (employee.getEmail()==null || employee.getEmail().trim().isEmpty()){
            throw new IllegalArgumentException("Email must NOT be empty.");
        }
        if (employee.getRole()==null || employee.getRole().trim().isEmpty()){
            throw new IllegalArgumentException("Role must NOT be empty.");
        }
        if (employee.getTaxIdentifier()==null || taxIdentifier.trim().isEmpty()){
            throw new IllegalArgumentException("Must not be empty.");
        }

        TaxIdentifier identifier;
        String onlyDigits = taxIdentifier.replaceAll("\\d", "");

        if (onlyDigits.length() == 11 && cpfValidation.isValidCPF(onlyDigits)){
            CPF cpf = new CPF(onlyDigits);
            identifier = cpfRepository.save(cpf);
        } else {
            throw new IllegalArgumentException("Invalid numbers");
        }

        employee.setTaxIdentifier(identifier);
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

    // Find by Email
    public Employee getEmployeeByEmail (String email){
        return employeeRepository.findByEmail(email);
    }

    // Find All
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    // Update
    @Transactional
    public Employee updateEmployee(Long id, Employee employee, String newTaxIdentifier){
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not foubd with ID: " + id));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setLogin(employee.getLogin());
        existingEmployee.setPassword(employee.getPassword());

        if (newTaxIdentifier != null && !newTaxIdentifier.trim().isEmpty()){
            String onlyDigits = newTaxIdentifier.replaceAll("\\d", "");
            TaxIdentifier identifier;

            if (onlyDigits.length()==11 && cpfValidation.isValidCPF(onlyDigits)){
                CPF cpf = new CPF(onlyDigits);
                identifier = cpfRepository.save(cpf);
            } else {
                throw new IllegalArgumentException("Invalid number");
            }

            existingEmployee.setTaxIdentifier(identifier);
        }
        return employeeRepository.save(existingEmployee);

        /*if(!employeeRepository.existsById(id)){
            throw new RuntimeException("Employee not found with ID " + id);
        }
        employee.setId(id);
        return employeeRepository.save(employee);
        */
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
