package com.itchenzn.bootcrm.dao;

import com.itchenzn.bootcrm.pojo.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao {
    //创建客户
    public int createCustomer(Customer customer);

    //查询客户信息列表
    List<Customer> selectCustomerList(Customer customer);

    //查询客户总条目数
    int selectCustomerListCount(Customer customer);

    //通过ID查询客户信息
    Customer getCustomerById(Integer id);

    //给更新客户信息
    int updateCustomer(Customer customer);


    //删除客户信息
    int deleteCustomerById(Integer id);


}
