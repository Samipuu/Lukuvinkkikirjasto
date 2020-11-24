package readingtips;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    @Before
    public void setUp() {
        Book bookTesti = new Book("Title", "Author", "Description", null, null, "isbn");
    }

    @Test
    public void isbnOikein() {
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        assertEquals("isbn", book.getIsbn());
    }

    @Test
    public void toStringOikeinPelkkaNimiKuvausJaISBN() {
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        String returnString = "";
        returnString += "Type: " + "Book";
        returnString += "\nTitle: " + "Title";
        returnString += "\nAuthor: " + "Author";        
        returnString += "\nDescription: " + "Description";
        returnString += "\nISBN: " + "isbn";
        assertEquals(returnString, book.toString());
    }

    @Test
    public void superToStringOikein() {
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        String returnString = "";
        returnString += "Type: " + "Book";
        returnString += "\nTitle: " + "Title";
        returnString += "\nAuthor: " + "Author";        
        returnString += "\nDescription: " + "Description";
        returnString += "\nISBN: " + "isbn";
        assertEquals(returnString, book.toString());
    }

    @Test
    public void equalsFalse() {
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        Book bookTesti = new Book(null, null, null, null, null, null);
        assertEquals(false, book.equals(bookTesti));
    }
    @Test
    public void equalsTrue() {
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        Book book1 = new Book("Title", "Author", "Description", null, null, "isbn");
        assertEquals(true, book.equals(book1));
    }    
}
