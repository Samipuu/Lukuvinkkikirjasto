package readingtips;

import java.util.List;
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
    @Test
    public void bookGetTitleNull() {
        Book book = new Book(null, "Author", "Description", null, null, "isbn");
        assertEquals("", book.getTitle());
    }
    @Test
    public void bookGetAuthorNull() {
        Book book = new Book("Title", null, "Description", null, null, "isbn");
        assertEquals("", book.getAuthor());
    }    
    @Test
    public void bookGetAuthor() {
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        assertEquals("Author", book.getAuthor());
    } 

    @Test
    public void bookGetDescriptionNull() {
        Book book = new Book("Title", "Author", null, null, null, "isbn");
        assertEquals("", book.getDescription());
    }    
    @Test
    public void bookGetDescription() {
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        assertEquals("Description", book.getDescription());
    }

    @Test
    public void bookToString() {
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
    public void toStringIlmanAutor() {
        Book book = new Book("Title", null, "Description", null, null, "isbn");
        String returnString = "";
        returnString += "Type: " + "Book";
        returnString += "\nTitle: " + "Title";
      
        returnString += "\nDescription: " + "Description";
        returnString += "\nISBN: " + "isbn";
        assertEquals(returnString, book.toString());
    }

    @Test
    public void bookToStringOikein() {
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
    public void bookToStringOikeinEiTitle() {
        Book book = new Book(null, "Author", "Description", null, null, "isbn");
        String returnString = "";
        returnString += "Type: " + "Book";

        returnString += "\nAuthor: " + "Author";        
        returnString += "\nDescription: " + "Description";
        returnString += "\nISBN: " + "isbn";
        assertEquals(returnString, book.toString());
    }

    public void bookToStringOikeinVainAuthor() {
        Book book = new Book(null, "Author", null, null, null, "isbn");
        String returnString = "";
        returnString += "Type: " + "Book";

        returnString += "\nAuthor: " + "Author";        

        returnString += "\nISBN: " + "isbn";
        assertEquals(returnString, book.toString());
    }

    @Test
    public void bookToStringOikeinListatMyos() {
        List<String> tags = new ArrayList();
        List<String> courses = new ArrayList();
        tags.add("eka");
        courses.add("tira");
        Book book = new Book("Title", "Author", "Description", tags, courses, "isbn");        
        String tagsString = book.getTags().toString();
        String coursesString = book.getCourses().toString();        
        String returnString = "";
        returnString += "Type: " + "Book";
        returnString += "\nTitle: " + "Title";
        returnString += "\nAuthor: " + "Author";        
        returnString += "\nDescription: " + "Description";
        returnString += "\nTags: " + tagsString.substring(1, tagsString.length()-1);
        returnString += "\nCourses: " + coursesString.substring(1, coursesString.length()-1);
                
        returnString += "\nISBN: " + "isbn";
        assertEquals(returnString, book.toString());
    }

    @Test
    public void bookToStringOikeinListatMyosEiTitle() {
        List<String> tags = new ArrayList();
        List<String> courses = new ArrayList();
        tags.add("eka");
        courses.add("tira");
        Book book = new Book("Title", "Author", null, tags, courses, "isbn");        
        String tagsString = book.getTags().toString();
        String coursesString = book.getCourses().toString();        
        String returnString = "";
        returnString += "Type: " + "Book";
        returnString += "\nTitle: " + "Title";
        returnString += "\nAuthor: " + "Author";        

        returnString += "\nTags: " + tagsString.substring(1, tagsString.length()-1);
        returnString += "\nCourses: " + coursesString.substring(1, coursesString.length()-1);
                
        returnString += "\nISBN: " + "isbn";
        assertEquals(returnString, book.toString());
    }

    @Test
    public void equalsTunnistaaSamaksiJosParametritSamat() {
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        Book book2 = new Book("Title", "Author", "Description", null, null, "isbn");

        assertTrue(book.equals(book2));
    }
}
