package tipsontech.example.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpaintro.domain.BookUuid;

import java.util.UUID;

public interface BookUuidRepository extends JpaRepository<BookUuid, UUID> {
}
