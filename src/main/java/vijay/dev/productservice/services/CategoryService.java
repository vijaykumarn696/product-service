package vijay.dev.productservice.services;

import vijay.dev.productservice.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category category);
    Optional<Category> getCategoryById(String id);
    List<Category> getAllCategories();

    Category updateCategoryUsingPut(Category category);

    Category deleteCategory(String id);

    Category updateCategoryUsingPatch(Category category);

}
