package tipsontech.example.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.orderservice.domain.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByDescription(String description);
}
