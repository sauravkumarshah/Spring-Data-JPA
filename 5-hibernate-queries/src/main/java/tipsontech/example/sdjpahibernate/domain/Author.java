package tipsontech.example.sdjpahibernate.domain;

import jakarta.persistence.*;
import jdk.jfr.Name;

import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "author_find_all", query = "FROM Author"),
        @NamedQuery(name = "find_by_name", query = "FROM Author a WHERE a.firstName = :firstName and a.lastName = :lastName")
})
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    public Author() { }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return Objects.equals(getId(), author.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
