package tipsontech.example.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpaintro.domain.BookNatural;

import java.util.UUID;

public interface BookNaturalRepository extends JpaRepository<BookNatural, String> {
}
