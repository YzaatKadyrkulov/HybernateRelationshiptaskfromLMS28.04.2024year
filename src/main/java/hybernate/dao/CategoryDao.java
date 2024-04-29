package hybernate.dao;

import hybernate.entity.Category;

import java.util.List;

public interface CategoryDao {
    Category saveCategory(Category category);

    Category findCategoryById(Long categoryId);

    List<Category> getAllCategories();

    String updateCategory(Long categoryId, Category newCategoryName);

    String deleteCategoryById(Long categoryId);

    String cleanCategory();
}