package vijay.dev.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vijay.dev.productservice.models.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {

    @Query(value = "SELECT c FROM Category c WHERE c.id IN :uuids",nativeQuery = false)
    List<Category> findAllByUuids(@Param("uuids")List<String> uuids);
}
