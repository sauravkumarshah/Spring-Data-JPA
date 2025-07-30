package tipsontech.example.sdjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpa.domain.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);

    Book findByIsbn(String isbn);
}
