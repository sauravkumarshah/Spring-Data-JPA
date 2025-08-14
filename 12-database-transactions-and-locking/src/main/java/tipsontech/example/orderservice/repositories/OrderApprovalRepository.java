package tipsontech.example.orderservice.repositories;

import tipsontech.example.orderservice.domain.OrderApproval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderApprovalRepository extends JpaRepository<OrderApproval, Long> {
}
