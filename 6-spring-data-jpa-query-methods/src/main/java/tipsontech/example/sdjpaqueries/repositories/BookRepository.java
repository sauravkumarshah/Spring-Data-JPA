package tipsontech.example.sdjpaqueries.repositories;

import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpaqueries.domain.Book;

import java.util.Optional;
import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);

    Optional<Book> findByTitle(String title);

    Book readByTitle(String title);

    @Nullable
    Book getByTitle(@Nullable String title);

    Stream<Book> findAllByTitleNotNull();
}
