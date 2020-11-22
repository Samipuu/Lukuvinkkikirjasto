package readingtips.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {
    
    public Connection conn;

    Dao() {

        try {
            conn = DriverManager.getConnection("jdbc:h2:./readingtips", "sa", "");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
