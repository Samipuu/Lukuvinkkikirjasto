package readingtips;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TipTest {

    //     @Before
    // public static void setUp() {
        
    // }

    @Test
    public void getTitleEmpty() {
        //Tip tip = new Tip("Type", "", "Author", "Description", null, null);
        Book book = new Book("", "Author", "Description", null, null, "isbn");
        assertEquals("", book.getTitle());
    }

    @Test
    public void getTitleRight() {
        //Tip tip = new Tip("Type", "", "Author", "Description", null, null);
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        assertEquals("Title", book.getTitle());
    }

    @Test
    public void getAuthorEmpty() {
        //Tip tip = new Tip("Type", "", "Author", "Description", null, null);
        Book book = new Book("Title", "", "Description", null, null, "isbn");
        assertEquals("", book.getAuthor());
    }

    @Test
    public void getAuthorRight() {
        //Tip tip = new Tip("Type", "", "Author", "Description", null, null);
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        assertEquals("Author", book.getAuthor());
    }    
}
