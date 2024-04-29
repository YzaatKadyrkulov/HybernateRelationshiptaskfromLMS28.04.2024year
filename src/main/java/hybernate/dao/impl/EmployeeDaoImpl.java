package hybernate.dao.impl;

import hybernate.config.DatabaseConfig;
import hybernate.dao.EmployeeDao;
import hybernate.entity.Customer;
import hybernate.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    EntityManagerFactory entityManagerFactory = DatabaseConfig.getEntityManagerFactory();
    @Override
    public Employee saveEmployee(Employee employee) {
        try {
            assert entityManagerFactory != null;
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(employee);
            entityManager.getTransaction().commit();
            entityManager.close();
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
        return employee;
    }

    @Override
    public Employee findEmployeeById(Long employeeId) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("select e from Employee e where id =: employeeId");

                Employee employee = entityManager.createQuery(hql, Employee.class)
                        .setParameter("employeeId", employeeId)
                        .getSingleResult();
                entityManager.getTransaction().commit();
                return employee;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("select e from Employee e");

                List<Employee> employees = entityManager.createQuery(hql, Employee.class).getResultList();
                entityManager.getTransaction().commit();
                return employees;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String updateEmployee(Long employeeId, Employee newEmployeeName) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();

                Employee employeeToUpdate = entityManager.find(Employee.class, employeeId);

                if (employeeToUpdate != null) {
                    employeeToUpdate.setFirst_name(newEmployeeName.getFirst_name());
                    employeeToUpdate.setLast_name(newEmployeeName.getLast_name());
                    employeeToUpdate.setAge(newEmployeeName.getAge());
                    entityManager.getTransaction().commit();

                    return "Employee with ID " + employeeId + " updated successfully";
                } else {
                    return "Employee with ID " + employeeId + " not found";
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String deleteEmployeeById(Long employeeId) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("delete from Employee where id = :employeeId");

                int deletedCount = entityManager.createQuery(hql)
                        .setParameter("employeeId", employeeId)
                        .executeUpdate();
                entityManager.getTransaction().commit();

                if (deletedCount > 0) {
                    return "Employee with ID " + employeeId + " deleted successfully";
                } else {
                    return "Employee with ID " + employeeId + " not found";
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public String cleanEmployee() {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = "delete from Employee ";
                entityManager.createQuery(hql).executeUpdate();
                entityManager.getTransaction().commit();
                return "Successfully cleaned";
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
