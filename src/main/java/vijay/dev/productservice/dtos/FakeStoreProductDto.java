package vijay.dev.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import vijay.dev.productservice.models.Category;
import vijay.dev.productservice.models.Price;

@Setter
@Getter
public class FakeStoreProductDto {
    private String id;
    private String title;
    private Price price;
    private Category category;
    private String description;
    private String image;
}
