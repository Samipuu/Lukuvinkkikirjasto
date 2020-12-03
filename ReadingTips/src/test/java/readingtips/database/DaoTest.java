package readingtips.database;

import java.io.File;
import org.junit.Before;
import org.junit.Test;

public class DaoTest {

    // test x
    public static void main(String[] args) {

    }

    @Before
    public void setUp() {
    }

    @Test
    public void alustaTietokantaTest() {
        // delete database
        File file = new File("readingtips.mv.db");
        file.delete();
        // no database exists
        BookDao dao = new BookDao();
        // database exists
        BookDao dao2 = new BookDao();
    }

}
