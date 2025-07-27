package tipsontech.example.sdjpaintro;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import tipsontech.example.sdjpaintro.domain.AuthorUuid;
import tipsontech.example.sdjpaintro.domain.BookNatural;
import tipsontech.example.sdjpaintro.domain.BookUuid;
import tipsontech.example.sdjpaintro.domain.composite.AuthorComposite;
import tipsontech.example.sdjpaintro.domain.composite.NameId;
import tipsontech.example.sdjpaintro.repositories.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = "tipsontech.example.sdjpaintro.bootstrap")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    AuthorUuidRepository authorUuidRepository;

    @Autowired
    BookUuidRepository bookUuidRepository;

    @Autowired
    BookNaturalRepository bookNaturalRepository;

    @Autowired
    AuthorCompositeRepository authorCompositeRepository;

    @Test
    public void authorCompositeTest() {
        NameId nameId = new NameId("John", "Doe");
        AuthorComposite authorComposite = new AuthorComposite();
        authorComposite.setFirstName(nameId.getFirstName());
        authorComposite.setLastName(nameId.getLastName());
        authorComposite.setCountry("USA");

        AuthorComposite saved = authorCompositeRepository.save(authorComposite);
        assertThat(saved).isNotNull();

        AuthorComposite fetched = authorCompositeRepository.findById(nameId).orElse(null);
        assertThat(fetched).isNotNull();
    }

    @Test
    public void bookNaturalTest() {
        BookNatural bookNatural = new BookNatural();
        bookNatural.setTitle("Book One");
        bookNatural.setIsbn("111-1111111111");
        bookNatural.setPublisher("Publisher One");
        BookNatural saved = bookNaturalRepository.save(bookNatural);

        BookNatural fetched = bookNaturalRepository.getById(saved.getTitle());
        assertThat(fetched).isNotNull();
    }
    @Test
    public void testBookRepository() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
    }


    @Test
    void testSaveAndGetAuthorUuid() {
        // Given
        AuthorUuid author = new AuthorUuid();
        author.setFirstName("John");
        author.setLastName("Doe");

        // When
        AuthorUuid savedAuthor = authorUuidRepository.save(author);
        AuthorUuid fetchedAuthor = authorUuidRepository.findById(savedAuthor.getId())
                .orElse(null);

        // Then
        Assertions.assertThat(fetchedAuthor).isNotNull();
        Assertions.assertThat(fetchedAuthor.getId()).isNotNull();
        Assertions.assertThat(fetchedAuthor.getFirstName()).isEqualTo("John");
        Assertions.assertThat(fetchedAuthor.getLastName()).isEqualTo("Doe");
    }

    @Test
    void testSaveAndGetBookUuid() {
        // Given
        BookUuid book = new BookUuid();
        book.setTitle("Test Book");
        book.setIsbn("123-4567890123");
        book.setPublisher("Test Publisher");

        // When
        BookUuid savedBook = bookUuidRepository.save(book);
        BookUuid fetchedBook = bookUuidRepository.findById(savedBook.getId())
                .orElse(null);

        // Then
        Assertions.assertThat(fetchedBook).isNotNull();
        Assertions.assertThat(fetchedBook.getId()).isNotNull();
        Assertions.assertThat(fetchedBook.getTitle()).isEqualTo("Test Book");
        Assertions.assertThat(fetchedBook.getIsbn()).isEqualTo("123-4567890123");
        Assertions.assertThat(fetchedBook.getPublisher()).isEqualTo("Test Publisher");
    }

    @Test
    void testAuthorUuidVersion() {
        // Given
        AuthorUuid author = new AuthorUuid();
        author.setFirstName("Test");
        author.setLastName("Author");
        AuthorUuid savedAuthor = authorUuidRepository.save(author);

        // When
        savedAuthor.setLastName("Updated Author");
        AuthorUuid updatedAuthor = authorUuidRepository.save(savedAuthor);

        // Then
        Assertions.assertThat(updatedAuthor).isNotNull();
        Assertions.assertThat(updatedAuthor.getId()).isEqualTo(savedAuthor.getId());
        Assertions.assertThat(updatedAuthor.getLastName()).isEqualTo("Updated Author");
    }

    @Test
    void testBookUuidVersion() {
        // Given
        BookUuid book = new BookUuid();
        book.setTitle("Original Title");
        book.setIsbn("123-4567890123");
        book.setPublisher("Original Publisher");
        BookUuid savedBook = bookUuidRepository.save(book);

        // When
        savedBook.setTitle("Updated Title");
        BookUuid updatedBook = bookUuidRepository.save(savedBook);

        // Then
        Assertions.assertThat(updatedBook).isNotNull();
        Assertions.assertThat(updatedBook.getId()).isEqualTo(savedBook.getId());
        Assertions.assertThat(updatedBook.getTitle()).isEqualTo("Updated Title");
    }

    @Test
    void testFindAllAuthors() {
        // Given
        AuthorUuid author1 = new AuthorUuid();
        author1.setFirstName("Author");
        author1.setLastName("One");
        authorUuidRepository.save(author1);

        AuthorUuid author2 = new AuthorUuid();
        author2.setFirstName("Author");
        author2.setLastName("Two");
        authorUuidRepository.save(author2);

        // When
        long count = authorUuidRepository.count();

        // Then
        Assertions.assertThat(count).isGreaterThanOrEqualTo(2);
    }

    @Test
    void testFindAllBooks() {
        // Given
        BookUuid book1 = new BookUuid();
        book1.setTitle("Book One");
        book1.setIsbn("111-1111111111");
        book1.setPublisher("Publisher One");
        bookUuidRepository.save(book1);

        BookUuid book2 = new BookUuid();
        book2.setTitle("Book Two");
        book2.setIsbn("222-2222222222");
        book2.setPublisher("Publisher Two");
        bookUuidRepository.save(book2);

        // When
        long count = bookUuidRepository.count();

        // Then
        Assertions.assertThat(count).isGreaterThanOrEqualTo(2);
    }
}
