package hybernate.dao.impl;

import hybernate.config.DatabaseConfig;
import hybernate.dao.CategoryDao;
import hybernate.entity.Account;
import hybernate.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    EntityManagerFactory entityManagerFactory = DatabaseConfig.getEntityManagerFactory();
    @Override
    public Category saveCategory(Category category) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                entityManager.persist(category);
                entityManager.getTransaction().commit();
                System.out.println("Success");
            }
            return category;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }
    @Override
    public Category findCategoryById(Long categoryId) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("select a from Category a where id =: categoryId");

                Category category = entityManager.createQuery(hql, Category.class)
                        .setParameter("categoryId", categoryId)
                        .getSingleResult();
                entityManager.getTransaction().commit();
                return category;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public List<Category> getAllCategories() {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("select c from Category c");

                List<Category> categories = entityManager.createQuery(hql, Category.class).getResultList();
                entityManager.getTransaction().commit();
                return categories;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String updateCategory(Long categoryId, Category newCategoryName) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();

                Category categoryToUpdate = entityManager.find(Category.class, categoryId);

                if (categoryToUpdate != null) {
                    categoryToUpdate.setCategoryName(newCategoryName.getCategoryName());
                    entityManager.getTransaction().commit();
                    return "Category with ID " + categoryId + " updated successfully";
                } else {
                    return "Category with ID " + categoryId + " not found";
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public String deleteCategoryById(Long categoryId) {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = ("delete from Category where id = :categoryId");

                int deletedCount = entityManager.createQuery(hql)
                        .setParameter("categoryId", categoryId)
                        .executeUpdate();
                entityManager.getTransaction().commit();

                if (deletedCount > 0) {
                    return "Category with ID " + categoryId + " deleted successfully";
                } else {
                    return "Category with ID " + categoryId + " not found";
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public String cleanCategory() {
        try {
            assert entityManagerFactory != null;
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();
                String hql = "delete from Category";
                entityManager.createQuery(hql).executeUpdate();
                entityManager.getTransaction().commit();
                return "Successfully cleaned";
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}