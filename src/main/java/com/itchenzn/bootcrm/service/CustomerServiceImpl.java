package com.itchenzn.bootcrm.service;

import com.itchenzn.bootcrm.dao.CustomerDao;
import com.itchenzn.bootcrm.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public int createCustomer(Customer customer) {

        return customerDao.createCustomer(customer);
    }

    @Override
    public List<Customer> selectCustomerList(Customer customer) {
        return customerDao.selectCustomerList(customer);
    }

    @Override
    public int selectCustomerListCount(Customer customer) {
        return customerDao.selectCustomerListCount(customer);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    @Override
    public int deleteCustomerById(Integer id) {
        return customerDao.deleteCustomerById(id);
    }
}
