package readingtips.database;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import readingtips.Book;

public class BookDaoTest {

    // test x
    public static void main(String[] args) {
        BookDaoTest test = new BookDaoTest();
        test.setUp();
        test.listBooks();
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
        Book book = new Book("Muumi2020", "sairas tarina", "Toove", null, null, "2020-2020");
        Integer id = dao.create(book);        
        assertTrue(id > 0);
    }

    @Test
    public void listBooks() {
        Book book = new Book("Muumi2020", "sairas tarina", "Toove", null, null, "2020-2020");
        Integer id = dao.create(book);        
        Book book2 = new Book("Muumi2021", "sairas tarina2", "Toove", null, null, "2020-2021");
        Integer id2 = dao.create(book2);        

        List<Book> list = dao.list();

        assertTrue(list.size() > 1);
    }    
}

