package vijay.dev.productservice.services;

import org.springframework.stereotype.Service;
import vijay.dev.productservice.dtos.GenericProductDto;
import vijay.dev.productservice.models.Category;
import vijay.dev.productservice.models.Price;
import vijay.dev.productservice.models.Product;
import vijay.dev.productservice.repositories.CategoryRepository;
import vijay.dev.productservice.repositories.PriceRepository;
import vijay.dev.productservice.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService,CategoryService{


    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private PriceRepository priceRepository;
    public SelfProductServiceImpl(ProductRepository productRepository,CategoryRepository categoryRepository,PriceRepository priceRepository)
    {
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
        this.priceRepository=priceRepository;

    }

    private Product convertGenericProductDtoToProduct(GenericProductDto productDto)
    {
        Product p=new Product();
        p.setTitle(productDto.getTitle());
        p.setDescription(productDto.getDescription());
        p.setPrice(productDto.getPrice());
        p.setImage(productDto.getImage());
        p.setCategory(productDto.getCategory());
        return p;

    }
    private GenericProductDto convertProductToGenericProductDto(Product product)
    {
        GenericProductDto p=new GenericProductDto();
        p.setTitle(product.getTitle());
        p.setDescription(product.getDescription());
        p.setPrice(product.getPrice());
        p.setImage(product.getImage());
        p.setCategory(product.getCategory());
        p.setId(product.getId());
        return p;

    }

    @Override
    public GenericProductDto getProductById(String id) {
        Optional<Product> p=productRepository.findById(id);
        return convertProductToGenericProductDto(p.get());
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        Optional<Category> category;
        if(product.getCategory().getId()!=null)
        {
            category=categoryRepository.findById(product.getCategory().getId());
            Category cat=category.get();
            cat.setName(product.getCategory().getName());
            product.setCategory(categoryRepository.save(cat));
        }
        Optional<Price> price;

        if(product.getPrice().getId()!=null)
        {
            price=priceRepository.findById(product.getPrice().getId());
            Price p=price.get();
            p.setCurrency(product.getPrice().getCurrency());
            p.setPrice(product.getPrice().getPrice());
            product.setPrice(priceRepository.save(p));
        }
        Product p=convertGenericProductDtoToProduct(product);
        GenericProductDto res=convertProductToGenericProductDto(productRepository.save(p));
        return res;
    }

    @Override
    public GenericProductDto createProductUsingHibernate(Product product) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
       List<Product> productList= productRepository.findAll();
       List<GenericProductDto> list=new ArrayList<>();
        for (Product p: productList) {
            GenericProductDto genericProductDto=new GenericProductDto();
            genericProductDto=convertProductToGenericProductDto(p);
            list.add(genericProductDto);
        }
        return list;
    }

    @Override
    public GenericProductDto updateProductUsingPUT(String id, GenericProductDto product) {
        Optional<Category> category;
        if(product.getCategory().getId()!=null)
        {
            category=categoryRepository.findById(product.getCategory().getId());
            Category cat=category.get();
            cat.setName(product.getCategory().getName());
            product.setCategory(categoryRepository.save(cat));
        }
        Optional<Price> price;

        if(product.getPrice().getId()!=null)
        {
            price=priceRepository.findById(product.getPrice().getId());
            Price p=price.get();
            p.setCurrency(product.getPrice().getCurrency());
            p.setPrice(product.getPrice().getPrice());
            product.setPrice(priceRepository.save(p));
        }
        Product existingProduct=productRepository.findById(id).orElseThrow();
        existingProduct.setCategory(product.getCategory());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setTitle(product.getTitle());
        existingProduct.setImage(product.getImage());
        existingProduct.setPrice(product.getPrice());
        productRepository.save(existingProduct);
        return  convertProductToGenericProductDto(existingProduct);
    }

    @Override
    public GenericProductDto updateProductUsingPATCH(String id, GenericProductDto product) {
        return null;
    }

    @Override
    public GenericProductDto deleteProduct(String id) {
       Optional<Product> product=productRepository.findById(id);
       productRepository.deleteById(id);
       return convertProductToGenericProductDto(product.get());
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategoryById(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategoryUsingPut(Category category) {
        Category existingCategory=categoryRepository.findById(category.getId()).orElseThrow();
        existingCategory.setName(category.getName());
        return categoryRepository.save(category);
    }

    @Override
    public Category deleteCategory(String id) {
        Optional<Category> c=categoryRepository.findById(id);
        categoryRepository.deleteById(id);
        return  c.get();
    }

    @Override
    public Category updateCategoryUsingPatch(Category category) {
        return categoryRepository.save(category);
    }
}
