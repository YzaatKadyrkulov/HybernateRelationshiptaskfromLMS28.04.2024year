package hybernate.service.impl;

import hybernate.dao.CustomerDao;
import hybernate.dao.impl.CustomerDaoImpl;
import hybernate.entity.Customer;
import hybernate.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerDao.saveCustomer(customer);
    }

    @Override
    public Customer findCustomerById(Long customerId) {
        return customerDao.findCustomerById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public String updateCustomer(Long customerId, Customer newCustomerName) {
        return customerDao.updateCustomer(customerId,newCustomerName);
    }

    @Override
    public String deleteCustomerById(Long customerId) {
        return customerDao.deleteCustomerById(customerId);
    }

    @Override
    public String cleanCustomer() {
        return customerDao.cleanCustomer();
    }
}
