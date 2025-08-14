package tipsontech.example.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.orderservice.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
