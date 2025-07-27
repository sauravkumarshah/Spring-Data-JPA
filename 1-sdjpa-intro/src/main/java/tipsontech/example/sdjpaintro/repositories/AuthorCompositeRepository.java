package tipsontech.example.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpaintro.domain.composite.AuthorComposite;
import tipsontech.example.sdjpaintro.domain.composite.NameId;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {
}
