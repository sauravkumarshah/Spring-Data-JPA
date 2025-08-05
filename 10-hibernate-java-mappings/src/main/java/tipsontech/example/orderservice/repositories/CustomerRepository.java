package tipsontech.example.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.orderservice.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}