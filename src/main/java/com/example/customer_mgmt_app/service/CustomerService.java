package com.example.customer_mgmt_app.service;

import com.example.customer_mgmt_app.exception.CustomerInvalidException;
import com.example.customer_mgmt_app.exception.CustomerNotFoundException;
import com.example.customer_mgmt_app.model.Customer;
import com.example.customer_mgmt_app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) throws CustomerInvalidException {
        if (customer.getName() == null || customer.getEmail() == null || customer.getName().isEmpty() || customer.getEmail().isEmpty()) {
            throw new CustomerInvalidException("Customer is invalid.");
        }
        return customerRepository.save(customer);
    }

    public Iterable<Customer> readAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer readCustomer(Integer id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new CustomerNotFoundException("Customer not found: " + id);
    }

    public Customer updateCustomer(Customer customer) throws CustomerInvalidException, CustomerNotFoundException {
        if (customer.getName() == null || customer.getEmail() == null || customer.getName().isEmpty() || customer.getEmail().isEmpty() || customer.getId() == null) {
            throw new CustomerInvalidException("Customer is invalid.");
        }
        Optional<Customer> customerInDB = customerRepository.findById(customer.getId());
        if (customerInDB.isPresent()) {
            return customerRepository.save(customer);
        } else {
            throw new CustomerNotFoundException("Customer not found: " + customer.getId());
        }

    }

    public Customer deleteCustomer(Integer id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.deleteById(id);
            return customer.get();
        } else {
            throw new CustomerNotFoundException("Customer not found: " + id);
        }
    }
}
