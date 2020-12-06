package readingtips;

import java.util.Scanner;

import readingtips.database.TipDao;
import readingtips.ui.Konsoli;
import readingtips.ui.Tui;

public class Main {

    public static String this_is_environment = "this.is.environment";  // if set to development will use development database. otherwise test database.

    public static String getDatabaseName() {
        if ("development".equals(System.getProperty(Main.this_is_environment))) {
            return "readingtips";
        } else {
            return "readingtips_test";
        }
    }

    public static void main(String[] args) {

        System.setProperty(Main.this_is_environment, "development");
          
        Scanner scanner = new Scanner(System.in);
        //Tui ui = new Tui(scanner);
        TipDao tipDao = new TipDao();
        Tui ui = new Tui(new Konsoli(), tipDao);
        ui.launch();
    }
    
}
