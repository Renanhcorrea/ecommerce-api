package com.ecommerce.service;

import com.ecommerce.model.Customer;
import com.ecommerce.model.identifier.CNPJ;
import com.ecommerce.model.identifier.CPF;
import com.ecommerce.model.validation.CNPJValidation;
import com.ecommerce.model.validation.CPFValidation;
import com.ecommerce.repository.identifier.CNPJRepository;
import com.ecommerce.repository.identifier.CPFRepository;
import com.ecommerce.model.identifier.TaxIdentifier;
import com.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    private final CPFValidation cpfValidation;
    private final CNPJValidation cnpjValidation;
    private final CustomerRepository customerRepository;
    private final CPFRepository cpfRepository;
    private final CNPJRepository cnpjRepository;

    @Autowired
    public CustomerService(CPFValidation cpfValidation,CNPJValidation cnpjValidation, CustomerRepository customerRepository, CPFRepository cpfRepository, CNPJRepository cnpjRepository ){
        this.cpfValidation = cpfValidation;
        this.cnpjValidation = cnpjValidation;
        this.customerRepository = customerRepository;
        this.cpfRepository = cpfRepository;
        this.cnpjRepository = cnpjRepository;
    }

    // Create
    @Transactional
    public Customer createCustomer(Customer customer, String taxIndentifier){
        if(customer.getName() == null || customer.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Name can NOT be empty.");
        }
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()){
            throw new IllegalArgumentException("Email can NOT be empty.");
        }
        if (customer.getPhone() == null || customer.getPhone().trim().isEmpty()){
            throw new IllegalArgumentException("Phone can NOT be empty.");
        }
        if (taxIndentifier == null || taxIndentifier.trim().isEmpty()){
            throw new IllegalArgumentException("Must not be empty.");
        }

        TaxIdentifier identifier = null;
        String onlyDigits = taxIndentifier.replaceAll("[\\d]", "");

        if (onlyDigits.length() == 11 && cpfValidation.isValidCPF(onlyDigits)){
            CPF cpf = new CPF(onlyDigits);
            identifier = cpfRepository.save(cpf);
        } else if (onlyDigits.length() == 14 && cnpjValidation.isValidCNPJ(onlyDigits)){
            CNPJ cnpj = new CNPJ(onlyDigits);
            identifier = cnpjRepository.save(cnpj);
        } else {
            throw new IllegalArgumentException("Invalid numbers");
        }

        customer.setTaxIdentifier(identifier);
        return customerRepository.save(customer);
    }

    // Find by ID
    public Customer getCustomerById(Long id){
        return customerRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Customer not found with ID: "+ id));
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
    @Transactional
    public Customer updateCustomer(Long id, Customer customer){
        if(!customerRepository.existsById(id)){
            throw new NoSuchElementException("Customer not found with ID " + id);
        }
        customer.setId(id);
        return customerRepository.save(customer);
    }

    // Delete
    @Transactional
    public void deleteCustomerById(Long id){
        if (!customerRepository.existsById(id)) {
            throw new NoSuchElementException("Customer not found with ID "+ id);
        }
        customerRepository.deleteById(id);
    }
}
