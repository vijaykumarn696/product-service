package vijay.dev.productservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Price extends BaseModel {
    private String currency;
    private double price;
}
