package tipsontech.example.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.orderservice.domain.OrderHeader;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
}
