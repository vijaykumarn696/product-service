package vijay.dev.productservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vijay.dev.productservice.models.Category;
import vijay.dev.productservice.services.SelfProductServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private SelfProductServiceImpl selfProductService;

    public CategoryController(SelfProductServiceImpl selfProductService)
    {
        this.selfProductService=selfProductService;
    }

    @PostMapping()
    public Category createCategory(@RequestBody Category category)
    {
       return selfProductService.createCategory(category);
    }


    @GetMapping("{id}")
    public Optional<Category> getCategoryById(@PathVariable("id") String id)
    {
        return selfProductService.getCategoryById(id);
    }
    @GetMapping()
    public List<Category> getAllCategories()
    {
        return selfProductService.getAllCategories();
    }

    @PutMapping()
    public Category updateCategoryUsingPut(@RequestBody Category category)
    {
        return selfProductService.updateCategoryUsingPut(category);
    }

    @PatchMapping()
    public Category updateCategoryUsingPatch(@RequestBody  Category category)
    {
        return selfProductService.updateCategoryUsingPatch(category);
    }
    @DeleteMapping("{id}")
    public Category deleteCategory(@PathVariable("id") String id)
    {
        return selfProductService.deleteCategory(id);
    }

}
