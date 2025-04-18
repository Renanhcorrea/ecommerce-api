package com.ecommerce.controller;

import com.ecommerce.dto.customer.CustomerRequestDTO;
import com.ecommerce.entity.Customer;
import com.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
/* PRECISO ARRUMAR !
    @PostMapping
    public ResponseEntity<CustomerRequestDTO> createCustomer (@Valid @RequestBody CustomerRequestDTO customerRequestDTO){
        Customer customer = new Customer();
        customer.setName(customerRequestDTO.getName());
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setAddress(customerRequestDTO.getAddress());
        customer.setPhone(customerRequestDTO.getPhone());

        Customer createdCustomer = customerService.createCustomer(
                customer,
                customerRequestDTO.getTaxIdentifier(),
                customerRequestDTO.getCep()
        );
        return new ResponseEntity<>(mapToCustomerResponseDTO(createdCustomer), HttpStatus.CREATED);
    }
 */
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
        try {
            return ResponseEntity.ok(customerService.getCustomerByEmail(email));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
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
