package repository;
import entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
        // You can add custom query methods here if needed
    }





