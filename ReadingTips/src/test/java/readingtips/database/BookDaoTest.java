package readingtips.database;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.junit.Rule;
import org.junit.Before;
import org.junit.Test;

import readingtips.Book;

public class BookDaoTest {

    // test x
    public static void main(String[] args) {
        BookDaoTest test = new BookDaoTest();
        test.setUp();
        test.listBooks();
        test.insertBook();
    }

    private BookDao dao;

    @Before
    public void setUp() {
        DatabaseTestSetup dataBaseTestSetup = new DatabaseTestSetup();
        dataBaseTestSetup.alustaTietokanta();
        dao = new BookDao();
    }

    @Test
    public void insertBook() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        Book book = new Book("Muumi2020", "sairas tarina", "Toove", tags, courses, "2020-2020");
        dao.create(book);
        assertTrue(book.getId() > 0);
    }

    @Test
    public void exceptionTesting() {
        RuntimeException exception = assertThrows(
            RuntimeException.class, () -> {
                throw new RuntimeException("a message"); }
        );
    
    //     assertEquals("a message", exception.getMessage());
    // }

    // @Test (expected = RuntimeException.class)
    // public void insertBookWithException() {
    //     try {
    //         List<String> tags = Arrays.asList("tag1", "tag2");
    //         List<String> courses = Arrays.asList("course1", "course2");
    //         Book book = new Book("Muumi2020", "sairas tarina", "Toove", tags, courses, "2020-2020");
    //         dao.create(book);
    //     }
    //     catch(RuntimeException re) {
    //         fail("tuliko ex?");
    //     }
    // }

    @Test
    public void listBooks() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        Book book = new Book("Muumi2020", "sairas tarina", "Toove", tags, courses, "2020-2020");
        // Integer id = dao.create(book);
        dao.create(book);
        Book book2 = new Book("Muumi2021", "sairas tarina2", "Toove", tags, courses, "2020-2021");
        // Integer id2 = dao.create(book2);
        dao.create(book2);

        List<Book> list = dao.list();

        assertTrue(list.size() > 1);
    }

    @Test
    public void listBooksContainsAddedBook() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        Book book = new Book("Muumi2020", "sairas tarina", "Toove", tags, courses, "2020-2020");
        dao.create(book);
        Book book2 = new Book("Muumi2021", "sairas tarina2", "Toove", tags, courses, "2020-2021");
        dao.create(book2);

        List<Book> list = dao.list();

        assertTrue(book.equals(list.get((0))));
    }

    @Test
    public void updateBookUpdatesBook() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        Book book = new Book("Muumi2020", "sairas tarina", "Toove", tags, courses, "2020-2020");
        dao.create(book);
        Book book2 = new Book("Muumi2020", "sairas tarina2", "Toove", tags, courses, "2020-2021");
        dao.update(book2);

        List<Book> list = dao.list();
        
        assertTrue(book2.equals(list.get(0)));
    }
}
