package tipsontech.example.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpaintro.domain.AuthorUuid;

public interface AuthorUuidRepository extends JpaRepository<AuthorUuid, String> {
}
