package guru.springframework.spring5webapp.controller;

import guru.springframework.spring5webapp.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Telling Spring that we want this class to be a MVC Controller
public class BookController {

    private final BookRepository bookRepository; // We want to inject a book repo

    // With the Constructor underneath, because this is a Spring-managed component (coz of @Controller), when
    // Spring instantiates this class, it'll inject an instance of the book repo into our Controller.
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books") // Map this method to a specific URL "/books", so when we do an action against this URL,
                              // the mapped method gets called by the Spring MVC Framework
    public String getBooks(Model model) { // Model obj is what is going to get returned to the View (eventually)

        // At runtime, when Spring gets a request to the URL /books, it is going to execute getBooks() and provide the
        // method a model obj, and our code is saying for that model we'll add the attribute called "books" and we're
        // going to get it to execute a method to get all the books in the repo
        model.addAttribute("books", bookRepository.findAll());

        // This model is going to get returned back to our View layer with the attribute "books" and a list of books,
        // and we'll be able to use this attribute inside our View layer to apply the view we're going to be returning
        // back to the client

        // The view page in our resources/templates directory we want to apply.
        return "books/list"; // Look for the list template inside our templates directory
    }
}
