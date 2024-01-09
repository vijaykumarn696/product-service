package vijay.dev.productservice.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vijay.dev.productservice.dtos.ExceptionDto;
import vijay.dev.productservice.dtos.GenericProductDto;
import vijay.dev.productservice.exceptions.NotFoundException;
import vijay.dev.productservice.services.ProductService;

import java.io.NotActiveException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    public  ProductController(ProductService productService)
    {
        this.productService=productService;
    }
    @GetMapping("{id}")
    public ResponseEntity<GenericProductDto> getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }
    @GetMapping()
    public List<GenericProductDto> getAllProducts()
    {
        return  productService.getAllProducts();
    }
    @PostMapping()
    public GenericProductDto createProduct(@RequestBody GenericProductDto product)
    {
        return  productService.createProduct(product);
    }
    @DeleteMapping("{id}")
    public GenericProductDto deleteProduct(@PathVariable("id") Long id)
    {
        return productService.deleteProduct(id);
    }
    @PutMapping("{id}")
    public GenericProductDto updateProductUsingPUT(@PathVariable("id") Long id,@RequestBody GenericProductDto product)
    {
        return productService.updateProductUsingPUT(id,product);
    }
    @PatchMapping("{id}")
    public GenericProductDto updateProductUsingPATCH(@PathVariable("id") Long id,@RequestBody GenericProductDto product)
    {
        return productService.updateProductUsingPATCH(id,product);
    }

    @ExceptionHandler(NotActiveException.class)
    public ResponseEntity<ExceptionDto> NotFoundExceptionHandlier(NotFoundException notFoundException)
    {
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(HttpStatus.NOT_FOUND,notFoundException.getMessage()),HttpStatus.NOT_FOUND);
    }
}
