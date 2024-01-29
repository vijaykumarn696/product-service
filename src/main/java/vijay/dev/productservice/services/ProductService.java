package vijay.dev.productservice.services;

import vijay.dev.productservice.dtos.GenericProductDto;
import vijay.dev.productservice.models.Product;
import vijay.dev.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    public GenericProductDto getProductById(String id) throws NotFoundException;
    public GenericProductDto createProduct(GenericProductDto product);
    public GenericProductDto createProductUsingHibernate(Product product);

    public List<GenericProductDto> getAllProducts();
    public GenericProductDto updateProductUsingPUT(String id,GenericProductDto product);
    public GenericProductDto updateProductUsingPATCH(String id,GenericProductDto product);
    public GenericProductDto deleteProduct(String id);

}
