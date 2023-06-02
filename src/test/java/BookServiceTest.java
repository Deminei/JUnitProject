import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookService bookService;
    @InjectMocks
    private BookService bookServiceUnderTest;
    private User user;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() {
        bookService = new BookService();

        user = new User("John", "johnnyboy123", "john@example.com");

        book1 = new Book("Title 1", "Author 1", "Genre 1", 9.99);
        book2 = new Book("Title 2", "Author 2", "Genre 2", 14.99);
        book3 = new Book("Title 3", "Author 3", "Genre 1", 19.99);

        bookServiceUnderTest = new BookService();
        bookServiceUnderTest.addBook(book1);
        bookServiceUnderTest.addBook(book2);
        bookServiceUnderTest.addBook(book3);
    }

    @AfterEach
    public void tearDown() {
        bookServiceUnderTest = null;
        user = null;
    }

    @Test
    public void testSearchBook_KeywordInTitle() {
        String keyword = "Title";

        List<Book> result = bookServiceUnderTest.searchBook(keyword);

        assertEquals(Arrays.asList(book1, book2, book3), result);
    }

    @Test
    public void testSearchBook_KeywordInAuthor() {
        String keyword = "Author";

        List<Book> result = bookServiceUnderTest.searchBook(keyword);

        assertEquals(Arrays.asList(book1, book2, book3), result);
    }

    @Test
    public void testSearchBook_KeywordInGenre() {
        String keyword = "Genre";

        List<Book> result = bookServiceUnderTest.searchBook(keyword);

        assertEquals(Arrays.asList(book1, book2, book3), result);
    }

    @Test
    public void testSearchBook_NoMatch() {
        String keyword = "Unknown";

        List<Book> result = bookServiceUnderTest.searchBook(keyword);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testPurchaseBook_BookExists() {
        boolean success = bookServiceUnderTest.purchaseBook(user, book1);

        assertTrue(success);
    }

    @Test
    public void testPurchaseBook_BookDoesNotExist() {
        Book unknownBook = new Book("Unknown", "Author", "Genre", 9.99);

        boolean success = bookServiceUnderTest.purchaseBook(user, unknownBook);

        assertFalse(success);
    }
}
