package readingtips.database;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public abstract class Dao {
    
    public Connection conn;

    Dao() {
        
        checkDB();
        
        try {
            conn = DriverManager.getConnection("jdbc:h2:./readingtips", "sa", ""); // TODO: test/operational..
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private void checkDB() {
        try {
            File file = new File ("readingtips.mv.db");
            if(!file.exists()) {
                String luontilauseet;
                {
                    Path path = new File("src/test/java/readingtips/database/luontilauseet.sql").toPath();
                    List<String> fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
                    luontilauseet = fileLines.stream().reduce((t, u) -> t + u).get();
                }
            
                Connection conn = DriverManager.getConnection("jdbc:h2:./readingtips", "sa", "");
                for (String lause : luontilauseet.split(";")) {
                    conn.prepareStatement(lause).executeUpdate();
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
