package guru.springframework.spring5webapp.repository;

import guru.springframework.spring5webapp.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> { // CrudRepo takes 2 Generics: POJO Type and ID Type
    // Just by extending this class we get operations like save(), saveAll(), findById(), deleteById() etc
}
