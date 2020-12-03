package readingtips.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                alustaTietokanta();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void alustaTietokanta() {

        try {
            String luontilauseet;
            {
                // File x = new File("");                
                // System.out.println("polulla: " + x.getAbsolutePath());

                // Path path = new File("ReadingTips/docs/luontilauseet.sql").toPath();
                List<String> rawFileLines;
                InputStream luontiLauseetStream = Dao.class.getResourceAsStream("luontilauseet.sql");
   
                // lines with sql comments still existing
                rawFileLines = new BufferedReader(new InputStreamReader(luontiLauseetStream, StandardCharsets.UTF_8)).lines().collect(Collectors.toList());    
                // remove sql comments as PreparedStatement can't handle them.
                List<String> fileLines = new ArrayList<String>();
                for (String rivi : rawFileLines) {
                    String parsittuRivi = rivi.replaceAll("--.*$","");
                    // if(!parsittuRivi.equals(rivi)) {
                    //     System.out.println("rivi kommenteilla:\n " + rivi);
                    //     System.out.println("rivis ilman kommentteja:\n " + parsittuRivi);
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


}
