package guru.springframework.spring5webapp.repository;

import guru.springframework.spring5webapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> { // CrudRepo takes 2 Generics: POJO Type and ID Type
    // Just by extending this class we get operations like save(), saveAll(), findById(), deleteById() etc
}
