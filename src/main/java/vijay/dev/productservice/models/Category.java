package vijay.dev.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String name;
    @OneToMany(mappedBy="category",fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT) // to solve the N+1 problem
    List<Product> products;
}
