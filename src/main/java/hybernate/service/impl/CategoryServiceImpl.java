package hybernate.service.impl;

import hybernate.dao.CategoryDao;
import hybernate.dao.impl.CategoryDaoImpl;
import hybernate.entity.Category;
import hybernate.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public Category saveCategory(Category category) {
        return categoryDao.saveCategory(category);
    }

    @Override
    public Category findCategoryById(Long categoryId) {
        return categoryDao.findCategoryById(categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Override
    public String updateCategory(Long categoryId, Category newCategoryName) {
        return categoryDao.updateCategory(categoryId,newCategoryName);
    }

    @Override
    public String deleteCategoryById(Long categoryId) {
        return categoryDao.deleteCategoryById(categoryId);
    }

    @Override
    public String cleanCategory() {
        return categoryDao.cleanCategory();
    }
}
