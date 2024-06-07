import static org.junit.jupiter.api.Assertions.*;

import com.library.controller.BookController;
import com.library.exceptions.BookNotFoundException;
import com.library.model.entities.Book;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;


public class BookTests {
    private final BookController controller = new BookController();
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    void addBook() throws ParseException {
        String title = "Código Limpo";
        String author = "Robert Cecil Martin";
        String publishingCompany = null;
        String category = "Informatica";
        int amount = 3;
        LocalDate publicationDate = sdf.parse("01/08/2008").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        assertDoesNotThrow(() -> controller.addBook(title, author, publishingCompany, category, amount, publicationDate));
        Integer idBook = controller.getBooks().stream().filter(book -> Objects.equals(book.getTitle(), title)).findFirst().get().getId();
        Book book = assertDoesNotThrow(() -> controller.getBook(idBook));

        assertNotNull(book);
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(publishingCompany, book.getPublishingCompany());
        assertEquals(category, book.getCategory());
        assertEquals(amount, book.getAmount());
        assertEquals(publicationDate, book.getPublicationDate());
    }

    @Test
    void updateBook() throws ParseException {
        addBook();

        String oldBookTitle = "Código Limpo";
        Integer idBook = controller.getBooks().stream().filter(book -> Objects.equals(book.getTitle(), oldBookTitle)).findFirst().get().getId();
        Book book = assertDoesNotThrow(() -> controller.getBook(idBook));

        String newTitle = "O Pequeno Príncipe";
        String newAuthor = "Antoine de Saint-Exupéry";
        String newPublishingCompany = "HarperCollins Brasil.";
        String newCategory = "Literatura infantil, Fábula, Novela, Ficção especulativa";
        int amount = 10;
        LocalDate newPublicationDate = sdf.parse("6/04/1943").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        assertDoesNotThrow(() -> controller.updateBook(book, newTitle, newAuthor, newPublishingCompany, newCategory, amount, newPublicationDate));
        assertNotNull(book);
        assertEquals(idBook, book.getId());
        assertEquals(newTitle, book.getTitle());
        assertEquals(newAuthor, book.getAuthor());
        assertEquals(newPublishingCompany, book.getPublishingCompany());
        assertEquals(newCategory, book.getCategory());
        assertEquals(amount, book.getAmount());
        assertEquals(newPublicationDate, book.getPublicationDate());
    }

    @Test
    void deleteBook() throws ParseException {
        addBook();

        String title = "Código Limpo";
        Integer idBook = controller.getBooks().stream().filter(book -> Objects.equals(book.getTitle(), title)).findFirst().get().getId();

        controller.deleteBook(idBook);
        assertThrows(BookNotFoundException.class, () -> controller.getBook(idBook));
    }
}
