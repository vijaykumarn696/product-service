package vijay.dev.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vijay.dev.productservice.models.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
}
