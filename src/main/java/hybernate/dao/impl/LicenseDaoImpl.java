package hybernate.dao.impl;

import hybernate.config.DatabaseConfig;
import hybernate.dao.LicenseDao;
import hybernate.entity.Customer;
import hybernate.entity.Employee;
import hybernate.entity.License;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class LicenseDaoImpl implements LicenseDao {
    EntityManagerFactory entityManagerFactory = DatabaseConfig.getEntityManagerFactory();
    @Override
    public License saveLicense(License license) {
        try {
            assert entityManagerFactory != null;
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(license);
            entityManager.getTransaction().commit();
            entityManager.close();
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
        return license;
    }


    @Override
    public License findLicenseById(Long licenseId) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("select l from License l where id =: licenseId");

                License license = entityManager.createQuery(hql, License.class)
                        .setParameter("licenseId", licenseId)
                        .getSingleResult();
                entityManager.getTransaction().commit();
                return license;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<License> getAllLicenses() {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("select l from License l");

                List<License> licenses = entityManager.createQuery(hql, License.class).getResultList();
                entityManager.getTransaction().commit();
                return licenses;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String updateLicense(Long licenseId, License newLicenseName) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();

                License licenseToUpdate = entityManager.find(License.class, licenseId);

                if (licenseToUpdate != null) {
                    licenseToUpdate.setLicenseNumber(newLicenseName.getLicenseNumber());
                    licenseToUpdate.setIssueDate(newLicenseName.getIssueDate());
                    licenseToUpdate.setExpirationDate(newLicenseName.getExpirationDate());
                    entityManager.getTransaction().commit();

                    return "Employee with ID " + licenseId + " updated successfully";
                } else {
                    return "Employee with ID " + licenseId + " not found";
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String deleteLicenseById(Long licenseId) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("delete from License where id = :licenseId");

                int deletedCount = entityManager.createQuery(hql)
                        .setParameter("licenseId", licenseId)
                        .executeUpdate();
                entityManager.getTransaction().commit();

                if (deletedCount > 0) {
                    return "License with ID " + licenseId + " deleted successfully";
                } else {
                    return "License with ID " + licenseId + " not found";
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String cleanLicense() {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = "delete from License ";
                entityManager.createQuery(hql).executeUpdate();
                entityManager.getTransaction().commit();
                return "Successfully cleaned";
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
