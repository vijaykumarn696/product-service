package vijay.dev.productservice.thirdpartyclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vijay.dev.productservice.dtos.FakeStoreProductDto;
import vijay.dev.productservice.dtos.GenericProductDto;
import vijay.dev.productservice.exceptions.NotFoundException;
import vijay.dev.productservice.services.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class FakeStoreClient  {

    @Value("${fakestore.product.url}")
    private String fakeStoreWebUrl;
    @Value("${fakestore.productpath}")
    private String fakeStoreProductUrl;

    //    private  String productUrl="http://fakestoreapi.com/products/{id}";
    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplateBuilder=restTemplateBuilder;
    }


    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response= restTemplate.getForEntity(fakeStoreWebUrl+fakeStoreProductUrl+"/{id}",FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto=response.getBody();
        if(fakeStoreProductDto==null)
        {
            throw new NotFoundException("Product id "+id+" is not found");
        }
        return fakeStoreProductDto;
    }


    public FakeStoreProductDto createProduct(FakeStoreProductDto product) {

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response= restTemplate.postForEntity(fakeStoreWebUrl+fakeStoreProductUrl,product,FakeStoreProductDto.class,product);
        FakeStoreProductDto fakeStoreProductDto=response.getBody();
        return fakeStoreProductDto;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response= restTemplate.getForEntity(fakeStoreWebUrl+fakeStoreProductUrl,FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtos=response.getBody();

        return Arrays.asList(fakeStoreProductDtos);
    }


    public FakeStoreProductDto updateProductUsingPUT(Long id, FakeStoreProductDto product) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        HttpEntity<FakeStoreProductDto> httpEntity=new HttpEntity<>(product);
        ResponseEntity<FakeStoreProductDto> response= restTemplate.exchange(fakeStoreWebUrl+fakeStoreProductUrl+"/{id}", HttpMethod.PUT,httpEntity,FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto=response.getBody();
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto updateProductUsingPATCH(Long id, FakeStoreProductDto product) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        HttpEntity<FakeStoreProductDto> httpEntity=new HttpEntity<>(product);
        ResponseEntity<FakeStoreProductDto> response= restTemplate.exchange(fakeStoreWebUrl+fakeStoreProductUrl+"/{id}",
                HttpMethod.PATCH,httpEntity,FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto=response.getBody();
        return fakeStoreProductDto;
    }


    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response= restTemplate.exchange(fakeStoreWebUrl+fakeStoreProductUrl+"/{id}", HttpMethod.DELETE,null,FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto=response.getBody();
        return fakeStoreProductDto;
    }
}
