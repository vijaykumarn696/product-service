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
import vijay.dev.productservice.thirdpartyclient.FakeStoreClient;

import java.util.ArrayList;
import java.util.List;
@Service
@Primary
public class FakeStoreProductService implements ProductService{

//    @Value("${fakestore.product.url}")
//    private String fakeStoreWebUrl;
//    @Value("${fakestore.productpath}")
//    private String fakeStoreProductUrl;
//
////    private  String productUrl="http://fakestoreapi.com/products/{id}";
//    private RestTemplateBuilder restTemplateBuilder;
//    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder)
//    {
//        this.restTemplateBuilder=restTemplateBuilder;
//    }

    private FakeStoreClient fakeStoreClient;
    public FakeStoreProductService(FakeStoreClient fakeStoreClient)
    {
        this.fakeStoreClient=fakeStoreClient;
    }
    private  GenericProductDto convertFakeStoreProductDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto)
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

    private  FakeStoreProductDto convertGenericProductDtoToFakeStoreProductDto(GenericProductDto genericProductDto)
    {
        FakeStoreProductDto fakeStoreProductDto=new FakeStoreProductDto();
        fakeStoreProductDto.setId(genericProductDto.getId());
        fakeStoreProductDto.setDescription(genericProductDto.getDescription());
        fakeStoreProductDto.setCategory(genericProductDto.getCategory());
        fakeStoreProductDto.setImage(genericProductDto.getImage());
        fakeStoreProductDto.setPrice(genericProductDto.getPrice());
        fakeStoreProductDto.setTitle(genericProductDto.getTitle());
        return fakeStoreProductDto;
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto=fakeStoreClient.getProductById(id);
        if(fakeStoreProductDto==null)
        {
         throw new NotFoundException("Product id "+id+" is not found");
        }
        GenericProductDto genericProductDto=convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto);
        return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        FakeStoreProductDto fakeStoreProductDto=convertGenericProductDtoToFakeStoreProductDto(product);
        FakeStoreProductDto fakeStoreProductDtoRes= fakeStoreClient.createProduct(fakeStoreProductDto);
        GenericProductDto genericProductDto = convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDtoRes);
        return genericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtoList= fakeStoreClient.getAllProducts();
        List<GenericProductDto> genericProductDtoList=new ArrayList<>();
        for(FakeStoreProductDto dto:fakeStoreProductDtoList) {
            GenericProductDto genericProductDto = convertFakeStoreProductDtoToGenericProductDto(dto);
            genericProductDtoList.add(genericProductDto);
        }
        return genericProductDtoList;
    }

    @Override
    public GenericProductDto updateProductUsingPUT(Long id, GenericProductDto product) {
        FakeStoreProductDto fakeStoreProductDto=convertGenericProductDtoToFakeStoreProductDto(product);
        FakeStoreProductDto fakeStoreProductDtoRes=fakeStoreClient.updateProductUsingPUT(id,fakeStoreProductDto);
        GenericProductDto genericProductDto = convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDtoRes);
        return genericProductDto;
    }

    @Override
    public GenericProductDto updateProductUsingPATCH(Long id, GenericProductDto product) {
        FakeStoreProductDto fakeStoreProductDto=convertGenericProductDtoToFakeStoreProductDto(product);
        FakeStoreProductDto fakeStoreProductDtoRes=fakeStoreClient.updateProductUsingPATCH(id,fakeStoreProductDto);
        GenericProductDto genericProductDto = convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDtoRes);
        return genericProductDto;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        FakeStoreProductDto fakeStoreProductDto=fakeStoreClient.deleteProduct(id);
        GenericProductDto genericProductDto = convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto);
        return genericProductDto;
    }
}
