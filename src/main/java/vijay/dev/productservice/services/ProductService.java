package vijay.dev.productservice.services;

import vijay.dev.productservice.dtos.GenericProductDto;
import vijay.dev.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    public GenericProductDto getProductById(Long id) throws NotFoundException;
    public GenericProductDto createProduct(GenericProductDto product);
    public List<GenericProductDto> getAllProducts();
    public GenericProductDto updateProductUsingPUT(Long id,GenericProductDto product);
    public GenericProductDto updateProductUsingPATCH(Long id,GenericProductDto product);
    public GenericProductDto deleteProduct(Long id);

}
