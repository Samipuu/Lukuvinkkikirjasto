
package readingtips.database;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class DatabaseTestSetup {

    // test
    public static void main(String[] args) {
        DatabaseTestSetup databaseTestSetup = new DatabaseTestSetup();
        databaseTestSetup.alustaTietokanta();
        databaseTestSetup.testaaJotain();
    }

    public void alustaTietokanta() {

        try {
            String luontilauseet;
            {
                Path path = new File("src/test/java/readingtips/database/luontilauseet.sql").toPath();
                List<String> fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
                luontilauseet = fileLines.stream().reduce((t, u) -> t + u).get();
            }

            // System.out.println("luontilauseet: \n" + luontilauseet);

            Connection conn = DriverManager.getConnection("jdbc:h2:./readingtips", "sa", "");
            for (String lause : luontilauseet.split(";")) {
                // System.out.println("ajetaan komento: \n " + lause);
                conn.prepareStatement(lause).executeUpdate();
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    public void testaaJotain() {

        try {

            Connection conn = DriverManager.getConnection("jdbc:h2:./readingtips", "sa", "");

            // test insert something
            conn.prepareStatement(
                    "insert into BOOK(title, author, description, isbn) values('muumix', 'tove', 'hejee', '1234-5') ")
                    .executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
