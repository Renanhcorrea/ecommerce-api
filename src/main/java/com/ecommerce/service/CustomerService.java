package com.ecommerce.service;

import com.ecommerce.model.Customer;
import com.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Create
    @Transactional
    public Customer createCustomer(Customer customer){
        if(customer.getName() == null || customer.getName().isEmpty()){
            throw new IllegalArgumentException("Name can NOT be empty.");
        }
        if (customer.getEmail() == null || customer.getEmail().isEmpty()){
            throw new IllegalArgumentException("Email can NOT be empty.");
        }
        if (customer.getPhone() == null || customer.getPhone().isEmpty()){
            throw new IllegalArgumentException("Phone can NOT be empty.");
        }

        return customerRepository.save(customer);
    }

    // Find by ID
    public Customer getCustomerById(Long id){
        return customerRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Customer not found with ID: "+ id));
    }

    // Find All
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    // Find by Email
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    // Update
    public Customer updateCustomer(Long id, Customer customer){
        if(!customerRepository.existsById(id)){
            throw new RuntimeException("Customer not found with ID " + id);
        }
        customer.setId(id);
        return customerRepository.save(customer);
    }

    // Delete
    public void deleteCustomerById(Long id){
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with ID "+ id);
        }
        customerRepository.deleteById(id);
    }
}
