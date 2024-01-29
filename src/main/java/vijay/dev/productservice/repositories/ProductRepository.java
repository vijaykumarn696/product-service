package vijay.dev.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vijay.dev.productservice.models.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
}
