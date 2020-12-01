package readingtips.database;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static com.google.common.collect.Lists.newArrayList;

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
                // File x = new File("");
                // System.out.println("polulla: " + x.getAbsolutePath());

                // Path path = new File("ReadingTips/docs/luontilauseet.sql").toPath();
                Path path = new File("docs/luontilauseet.sql").toPath();
                // lines with sql comments still existing
                List<String> rawFileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
                // remove sql comments as PreparedStatement can't handle them.
                List<String> fileLines = new ArrayList<String>();
                for (String rivi : rawFileLines) {
                    String parsittuRivi = rivi.replaceAll("--.*$", "");
                    // if(!parsittuRivi.equals(rivi)) {
                    // System.out.println("rivi kommenteilla:\n " + rivi);
                    // System.out.println("rivis ilman kommentteja:\n " + parsittuRivi);
                    // }
                    fileLines.add(parsittuRivi);
                }
                // tulosta koko scripti ilman kommentteja
                // System.out.println("KOMMENTEISTA PARSITTU KANNANLUONTI:");
                // fileLines.stream().forEach(System.out::println);
                // luodaan yksi stringi koko scriptistä
                luontilauseet = fileLines.stream().reduce((t, u) -> t + u).get();
            }
            Connection conn = DriverManager.getConnection("jdbc:h2:./readingtips", "sa", "");
            for (String lause : luontilauseet.split(";")) {
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
            conn.prepareStatement("insert into BOOK(created, modified, title, author, description, isbn) "
                    + " values('2020-11-28 01:23:45.67', '2020-11-28 02:23:45.67', 'muumix', 'tove', 'hejee', '1234-5') ")
                    .executeUpdate();

            // test the dao way
            {
                String sql = "INSERT INTO BOOK(created, modified, title, author, description, isbn)"
                        + " VALUES(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?, ?) ";

                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, "Rahastus");
                ps.setString(2, "Sysimetsä");
                ps.setString(3, "has been");
                ps.setString(4, "000-000");
                ps.executeUpdate();
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        // test different init lists
        {
            List<String> list = List.of("s1", "s2", "s3"); // JDK9 immutable
            System.out.println("tyyppi: " + list.getClass());   // tyyppi: class java.util.ImmutableCollections$ListN
        }
        {
            List<String> list = new ArrayList<String>(List.of("s1", "s2", "s3")); // JDK9 mutable
            System.out.println("tyyppi: " + list.getClass()); // tyyppi: class java.util.ArrayList 
        }
        {
            // import static com.google.common.collect.Lists.newArrayList;
            List<String> list = new ArrayList("s1", "s2", "s3"); // Guava
            System.out.println("tyyppi2: " + list.getClass()); // tyyppi2: class java.util.ArrayList
        }
    }

}
