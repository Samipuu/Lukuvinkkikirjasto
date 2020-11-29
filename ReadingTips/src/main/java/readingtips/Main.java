package readingtips;
import readingtips.ui.Konsoli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import readingtips.database.TipDao;
import readingtips.ui.Tui;

public class Main {

    public static void main(String[] args) {
          
        Scanner scanner = new Scanner(System.in);
        //Tui ui = new Tui(scanner);
        TipDao tipDao = new TipDao();
        Tui ui = new Tui(new Konsoli(), tipDao);
        ui.launch();
    }
    
}
