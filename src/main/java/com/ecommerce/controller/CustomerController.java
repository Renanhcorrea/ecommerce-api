package com.ecommerce.controller;

import com.ecommerce.model.Customer;
import com.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer (@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(String email){
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Long id, Customer customer){
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id){
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
