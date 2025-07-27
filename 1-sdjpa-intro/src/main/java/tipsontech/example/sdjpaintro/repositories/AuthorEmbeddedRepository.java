package tipsontech.example.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpaintro.domain.composite.AuthorEmbedded;
import tipsontech.example.sdjpaintro.domain.composite.NameId;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId> {
}
