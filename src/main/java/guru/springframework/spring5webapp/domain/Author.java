package guru.springframework.spring5webapp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity // Tells hibernate that this class is an entity

public class Author {
    @Id // Tell hibernate which property we're using as an ID
    @GeneratedValue(strategy = GenerationType.AUTO) // Tell hibernate how this ID is being generated (in this case, the underlying DB will be providing the generation of the ID (i.e. the property will be managed by the DB, like auto-increment in SQL))
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "authors") // value needs to match the mapped field's name
    private Set<Book> books = new HashSet<>();

    public Author() {
    }

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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return id != null ? id.equals(author.id) : author.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        // We removed books property from here to avoid a Circular reference infinite loop
        sb.append('}');
        return sb.toString();
    }
}
