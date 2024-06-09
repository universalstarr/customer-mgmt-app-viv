package com.example.customer_mgmt_app.controller;

import com.example.customer_mgmt_app.exception.CustomerInvalidException;
import com.example.customer_mgmt_app.exception.CustomerNotFoundException;
import com.example.customer_mgmt_app.model.Customer;
import com.example.customer_mgmt_app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customer", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws CustomerInvalidException {
        Customer customerCreated = customerService.createCustomer(customer);
        return new ResponseEntity<>(customerCreated, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Customer>> readAllCustomer() {
        Iterable<Customer> customers = customerService.readAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Customer> readCustomer(@PathVariable Integer id) throws CustomerNotFoundException {
        Customer customer = customerService.readCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws CustomerInvalidException, CustomerNotFoundException {
        Customer updatedCustomer = customerService.updateCustomer(customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer id) throws CustomerNotFoundException {
        Customer deletedCustomer = customerService.deleteCustomer(id);
        return new ResponseEntity<>(deletedCustomer, HttpStatus.OK);
    }


}
