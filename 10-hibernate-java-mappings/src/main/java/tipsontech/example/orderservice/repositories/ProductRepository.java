package tipsontech.example.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.orderservice.domain.Product;
public interface ProductRepository extends JpaRepository<Product, Long> {
}
