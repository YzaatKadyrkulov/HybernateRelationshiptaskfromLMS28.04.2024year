package hybernate.dao.impl;

import hybernate.config.DatabaseConfig;
import hybernate.dao.AddressDao;
import hybernate.entity.Account;
import hybernate.entity.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class AddressDaoImpl implements AddressDao {
    EntityManagerFactory entityManagerFactory = DatabaseConfig.getEntityManagerFactory();
    @Override
    public Address saveAddress(Address address) {
        assert entityManagerFactory != null;
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            entityManager.persist(address);
            entityManager.getTransaction().commit();
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
        return address;
    }

    @Override
    public Address findAddressById(Long addressId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.find(Address.class,addressId);
            Address address = (Address) entityManager.createQuery("select a from Address a where id = :addressId")
                    .setParameter("addressId",addressId)
                    .getSingleResult();
            entityManager.getTransaction().commit();
            return address;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public List<Address> getAllAddresses() {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("select a from Address a");

                List<Address> addresses = entityManager.createQuery(hql, Address.class).getResultList();
                entityManager.getTransaction().commit();
                return addresses;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String updateAddress(Long addressId, Address newAddressName) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();

                Address addressToUpdate = entityManager.find(Address.class, addressId);

                if (addressToUpdate != null) {
                    addressToUpdate.setEmployeeAddress(newAddressName.getEmployeeAddress());
                    entityManager.getTransaction().commit();
                    return "Address with ID " + addressId + " updated successfully";
                } else {
                    return "Address with ID " + addressId + " not found";
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String deleteAddressById(Long addressId) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("delete from Address where id = :addressId");

                int deletedCount = entityManager.createQuery(hql)
                        .setParameter("addressId", addressId)
                        .executeUpdate();
                entityManager.getTransaction().commit();

                if (deletedCount > 0) {
                    return "Address with ID " + addressId + " deleted successfully";
                } else {
                    return "Address with ID " + addressId + " not found";
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String cleanAddress() {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = "delete from Address";
                entityManager.createQuery(hql).executeUpdate();
                entityManager.getTransaction().commit();
                return "Successfully cleaned";
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}