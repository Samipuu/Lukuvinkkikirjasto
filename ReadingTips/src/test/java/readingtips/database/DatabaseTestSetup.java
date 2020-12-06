package readingtips.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Properties;

public class DatabaseTestSetup {

    // test
    public static void main(String[] args) {

        // tietoja();
        
        DatabaseTestSetup databaseTestSetup = new DatabaseTestSetup();
        Dao.alustaTietokanta(Dao.getConnection());
        databaseTestSetup.testaaJotain();
    }

    public static void tietoja() {
        Properties p = System.getProperties();
        Enumeration keys = p.keys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = (String) p.get(key);
            System.out.println(key + ": " + value);
        }
    }

    public void alustaTietokanta() {
        Dao.alustaTietokanta(Dao.getConnection());
    }

    public void testaaJotain() {

        try {
            Connection conn = Dao.getConnection();

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
    }

}
