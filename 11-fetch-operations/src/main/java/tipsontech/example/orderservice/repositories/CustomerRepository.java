package tipsontech.example.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.orderservice.domain.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByCustomerNameIgnoreCase(String customerName);
}