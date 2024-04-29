package hybernate.dao;

import hybernate.entity.Customer;

import java.util.List;

public interface CustomerDao{
    Customer saveCustomer(Customer customer);

    Customer findCustomerById(Long customerId);

    List<Customer> getAllCustomers();

    String updateCustomer(Long customerId, Customer newCustomerName);

    String deleteCustomerById(Long customerId);

    String cleanCustomer();
}
