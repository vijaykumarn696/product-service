package vijay.dev.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@Entity
public class Product extends BaseModel{
    private String title;
    @ManyToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JsonIgnore
    private Category category;
    private String description;
    private String image;
    @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private Price price;

}
