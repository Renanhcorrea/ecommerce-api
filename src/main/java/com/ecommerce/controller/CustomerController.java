package com.ecommerce.controller;

import com.ecommerce.model.Customer;
import com.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer (@RequestBody Customer customer, @RequestParam String taxIndentifier){
        return new ResponseEntity<>(customerService.createCustomer(customer, taxIndentifier), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(customerService.getCustomerById(id));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email){
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,@RequestBody Customer customer,@RequestParam(required = false) String newTaxIdentifier){
        try {
            return ResponseEntity.ok(customerService.updateCustomer(id, customer, newTaxIdentifier));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id){
        try {
            customerService.deleteCustomerById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}
