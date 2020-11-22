package readingtips;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BookTest {

    //     @Before
    // public static void setUp() {
        
    // }

    @Test
    public void isbnOikein() {
        Book book = new Book("Title", "Description", null, null, null, "isbn");
        assertEquals("isbn", book.getIsbn());
    }
}
