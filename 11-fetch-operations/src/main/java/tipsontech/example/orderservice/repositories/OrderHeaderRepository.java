package tipsontech.example.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.orderservice.domain.Customer;
import tipsontech.example.orderservice.domain.OrderHeader;

import java.util.Collection;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
    Collection<OrderHeader> findAllByCustomer(Customer customer);
}
