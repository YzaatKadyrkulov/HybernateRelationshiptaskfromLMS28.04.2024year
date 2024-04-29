package hybernate.dao.impl;

import hybernate.config.DatabaseConfig;
import hybernate.dao.CustomerDao;
import hybernate.entity.Address;
import hybernate.entity.Category;
import hybernate.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    EntityManagerFactory entityManagerFactory = DatabaseConfig.getEntityManagerFactory();
    @Override
    public Customer saveCustomer(Customer customer) {
        try {
            assert entityManagerFactory != null;
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            entityManager.close();
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
        return customer;
    }

    @Override
    public Customer findCustomerById(Long customerId) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("select c from Customer c where id =: customerId");

                Customer customer = (Customer) entityManager.createQuery(hql)
                        .setParameter("customerId", customerId)
                        .getSingleResult();
                entityManager.getTransaction().commit();
                return customer;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("select c from Customer c");

                List<Customer> customers = entityManager.createQuery(hql, Customer.class).getResultList();
                entityManager.getTransaction().commit();
                return customers;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public String updateCustomer(Long customerId, Customer newCustomerName) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();

                Customer customerToUpdate = entityManager.find(Customer.class, customerId);

                if (customerToUpdate != null) {
                    customerToUpdate.setFirst_name(newCustomerName.getFirst_name());
                    customerToUpdate.setLast_name(customerToUpdate.getLast_name());
                    customerToUpdate.setAge(customerToUpdate.getAge());
                    entityManager.getTransaction().commit();

                    return "Customer with ID " + customerId + " updated successfully";
                } else {
                    return "Customer with ID " + customerId + " not found";
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public String deleteCustomerById(Long customerId) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("delete from Customer where id = :customerId");

                int deletedCount = entityManager.createQuery(hql, Customer.class)
                        .setParameter("customerId", customerId)
                        .executeUpdate();
                entityManager.getTransaction().commit();

                if (deletedCount > 0) {
                    return "Customer with ID " + customerId + " deleted successfully";
                } else {
                    return "Customer with ID " + customerId + " not found";
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public String cleanCustomer() {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = "delete from Customer ";
                entityManager.createQuery(hql).executeUpdate();
                entityManager.getTransaction().commit();
                return "Successfully cleaned";
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}