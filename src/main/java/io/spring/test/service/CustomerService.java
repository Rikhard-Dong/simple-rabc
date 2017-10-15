package io.spring.test.service;

import io.spring.test.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-12
 * Time: 下午12:26
 */
@Component
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}
