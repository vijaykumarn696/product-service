package vijay.dev.productservice.repositories;

import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vijay.dev.productservice.models.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price,String> {
}
