package readingtips.database;

import java.io.File;
import org.junit.Before;
import org.junit.Test;

import readingtips.Main;

public class DaoTest {

    // test x
    public static void main(String[] args) {

    }

    private String myTestDatabase = "readingtips_DaoTest";

    @Before
    public void setUp() {
        System.setProperty(Main.environment_database_name, myTestDatabase);
    }

    @Test
    public void alustaTietokantaTest() {
        // delete database
        File file = new File(myTestDatabase + ".mv.db");
        file.delete();
        // no database exists
        BookDao dao = new BookDao();
        // database exists
        BookDao dao2 = new BookDao();
    }

}
