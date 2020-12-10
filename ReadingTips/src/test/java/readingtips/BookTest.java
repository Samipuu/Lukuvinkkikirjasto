package readingtips;

import readingtips.entity.Podcast;
import readingtips.entity.Book;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

//import jdk.jfr.Timestamp; //EI TOIMINUT LAURALLA. ONKO TÄMÄ KÄYTÖSSÄ?

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

    @Test
    public void updateWorks() {
        List<String> tags = new ArrayList();
        List<String> courses = new ArrayList();        
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        tags.add("eka");
        courses.add("tira"); 
        book.update(1, LocalDateTime.now(), LocalDateTime.now(), "isbn2", "Title2", "Author", "Description", tags, courses);
        assertTrue(book.getIsbn().equals("isbn2"));
    }

    @Test
    public void equalsHuomaaNullin() {
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        Book book2 = new Book("Title", "Author", "Description", null, null, "isbn");

        assertEquals(false, book.equals(null));
    }
    @Test
    public void equalsHuomaaVaaranLuokan() {
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        Podcast podcast = new Podcast(null, "Author", "Description", null, null, null);
        
        assertEquals(false, book.equals(podcast));
    }
    @Test
    public void equalsTarkistaaJaFeilaa() {
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        Book book2 = new Book("Title", "Author2", "Description", null, null, "isbn");

        assertEquals(false, book.equals(book2));
    }   
    @Test
    public void equalsTarkistaaJaFeilaa2() {
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        Book book2 = new Book("Title", "Author", "Description2", null, null, "isbn");

        assertEquals(false, book.equals(book2));
    }     
    @Test
    public void equalsTarkistaaJaFeilaa23() {
        Book book = new Book("Title", "Author", "Description", null, null, "isbn");
        Book book2 = new Book("Title2", "Author", "Description", null, null, "isbn");

        assertEquals(false, book.equals(book2));
    }
   
    @Test
    public void toStringIlmanISBN2() {
        Book book = new Book("Title", null, "Description", null, null, "");
        String returnString = "";
        returnString += "Type: " + "Book";
        returnString += "\nTitle: " + "Title";
      
        returnString += "\nDescription: " + "Description";
        //returnString += "\nISBN: " + "isbn";
        assertEquals(returnString, book.toString());
    }     

// EN ONNISTUNUT TÄTÄ SUPER-TESTIÄ SAAMAAN MITÄÄN LISÄARVOA
    public void superToimii() {
        List<String> tags = new ArrayList();
        List<String> courses = new ArrayList();        
        Book book = new Book("Title", "Author", "Description", tags, courses, "isbn");

        assertEquals(true, book.getDescription().equals("Description"));
    }    
}
