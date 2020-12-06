package readingtips;

import java.util.Scanner;

import readingtips.database.TipDao;
import readingtips.ui.Konsoli;
import readingtips.ui.Tui;

public class Main {

    // public static String this_is_environment = "this.is.environment";  // not used for now. { test, development, production }
    public static String environment_database_name = "environment.database.name";  // used if set to something.

    public static String getDatabaseName() {
        String databaseName;
        {
            databaseName = System.getProperty(Main.environment_database_name);
            if (null == databaseName) {
                databaseName="readingtips_test";
            }
        }
        return databaseName;
    }

    public static void main(String[] args) {

        System.setProperty(Main.environment_database_name, "readingtips");
          
        Scanner scanner = new Scanner(System.in);
        //Tui ui = new Tui(scanner);
        TipDao tipDao = new TipDao();
        Tui ui = new Tui(new Konsoli(), tipDao);
        ui.launch();
    }
    
}
