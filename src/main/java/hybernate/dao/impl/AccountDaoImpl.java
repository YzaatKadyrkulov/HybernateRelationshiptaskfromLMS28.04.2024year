package hybernate.dao.impl;

import hybernate.config.DatabaseConfig;
import hybernate.dao.AccountDao;
import hybernate.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class AccountDaoImpl implements AccountDao {
    EntityManagerFactory entityManagerFactory = DatabaseConfig.getEntityManagerFactory();

    @Override
    public Account saveAccount(Account account) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                entityManager.persist(account);
                entityManager.getTransaction().commit();
                System.out.println("Success");
            }
            return account;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public Account findAccountById(Long accountId) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("select a from Account a where id =: accountId");

                Account account = entityManager.createQuery(hql, Account.class)
                        .setParameter("accountId", accountId)
                        .getSingleResult();
                entityManager.getTransaction().commit();
                return account;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("select a from Account a");

                List<Account> accounts = entityManager.createQuery(hql, Account.class).getResultList();
                entityManager.getTransaction().commit();
                return accounts;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String updateAccount(Long accountId, Account newAccountName) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();

                Account accountToUpdate = entityManager.find(Account.class, accountId);

                if (accountToUpdate != null) {
                    accountToUpdate.setAccountEmail(newAccountName.getAccountEmail());
                    entityManager.getTransaction().commit();
                    return "Account with ID " + accountId + " updated successfully";
                } else {
                    return "Account with ID " + accountId + " not found";
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String deleteAccountById(Long accountId) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("delete from Account where id = :accountId");

                int deletedCount = entityManager.createQuery(hql)
                        .setParameter("accountId", accountId)
                        .executeUpdate();
                entityManager.getTransaction().commit();

                if (deletedCount > 0) {
                    return "Account with ID " + accountId + " deleted successfully";
                } else {
                    return "Account with ID " + accountId + " not found";
                }
            }
        } catch (Exception e) {
            System.out.println((e.getMessage()));
        }
        return "";
    }

    @Override
    public String cleanAccount() {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = "delete from Account";
                entityManager.createQuery(hql).executeUpdate();
                entityManager.getTransaction().commit();
                return "Successfully cleaned";
            }
        } catch (Exception e) {
            System.out.println((e.getMessage()));
        }
        return "";
    }
}