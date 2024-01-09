package vijay.dev.productservice.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vijay.dev.productservice.dtos.FakeStoreProductDto;
import vijay.dev.productservice.dtos.GenericProductDto;
import vijay.dev.productservice.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
@Service
@Primary
public class FakeStoreProductService implements ProductService{

    @Value("${fakestore.product.url}")
    private String fakeStoreWebUrl;
    @Value("${fakestore.productpath}")
    private String fakeStoreProductUrl;

//    private  String productUrl="http://fakestoreapi.com/products/{id}";
    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplateBuilder=restTemplateBuilder;
    }
    private  GenericProductDto convertFakeStoreDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto)
    {
        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        return genericProductDto;
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response= restTemplate.getForEntity(fakeStoreWebUrl+fakeStoreProductUrl+"/{id}",FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto=response.getBody();
        if(fakeStoreProductDto==null)
        {
         throw new NotFoundException("Product id "+id+" is not found");
        }
        GenericProductDto genericProductDto=convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
        return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response= restTemplate.postForEntity(fakeStoreWebUrl+fakeStoreProductUrl,product,FakeStoreProductDto.class,product);
        FakeStoreProductDto fakeStoreProductDto=response.getBody();
        GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
        return genericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response= restTemplate.getForEntity(fakeStoreWebUrl+fakeStoreProductUrl,FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtos=response.getBody();
        List<GenericProductDto> fakeStoreProductDtoList= new ArrayList<>();
        for(FakeStoreProductDto dto:fakeStoreProductDtos) {
            GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProductDto(dto);
            fakeStoreProductDtoList.add(genericProductDto);
        }
        return fakeStoreProductDtoList;
    }

    @Override
    public GenericProductDto updateProductUsingPUT(Long id, GenericProductDto product) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        HttpEntity<GenericProductDto> httpEntity=new HttpEntity<>(product);
        ResponseEntity<FakeStoreProductDto> response= restTemplate.exchange(fakeStoreWebUrl+fakeStoreProductUrl+"/{id}", HttpMethod.PUT,httpEntity,FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto=response.getBody();
        GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
        return genericProductDto;
    }

    @Override
    public GenericProductDto updateProductUsingPATCH(Long id, GenericProductDto product) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        HttpEntity<GenericProductDto> httpEntity=new HttpEntity<>(product);
        ResponseEntity<FakeStoreProductDto> response= restTemplate.exchange(fakeStoreWebUrl+fakeStoreProductUrl+"/{id}",
                HttpMethod.PATCH,httpEntity,FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto=response.getBody();
        GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
        return genericProductDto;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response= restTemplate.exchange(fakeStoreWebUrl+fakeStoreProductUrl+"/{id}", HttpMethod.DELETE,null,FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto=response.getBody();
        GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
        return genericProductDto;
    }
}
