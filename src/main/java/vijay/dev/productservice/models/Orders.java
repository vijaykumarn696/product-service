package vijay.dev.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name="orders")
public class Orders extends BaseModel{

    @ManyToMany
    @JoinTable(name = "product_orders")
    List<Product> products;
}
