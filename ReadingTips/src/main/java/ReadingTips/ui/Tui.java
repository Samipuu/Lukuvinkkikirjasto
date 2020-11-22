/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingtips.ui;

import java.util.Scanner;

/**
 *
 * @author aatukallio
 */
public class Tui implements UI {
    Scanner scanner;
    
    public Tui () {
        
    }
    
    @Override
    public void launch(Scanner scanner) {
        this.scanner = scanner;
        System.out.println("Commands: \n"
                + "Add : Add new readingtip\n"
                + "Delete : Delete specific readigtip\n"
                + "Print All : Print title of all readingtips\n"
                + "Print : Print specific readingtip by title\n" 
                + "Exit : Close the program");
        
        while(true) {
            switch(scanner.nextLine().toLowerCase()) {
                case "add":
                    add();   
                case "delete":
                    delete();
                case "exit":
                    return;
                case "print all":
                    printAll();
                case "print":
                    print();                    
                default:
                    System.out.println("Invalid command.");
            }
            
        }
    }

    private void add() {
        System.out.println("Available types: book, podcast, video");
        System.out.println("Type: ");
        switch(scanner.nextLine().toLowerCase()) {
            case "book":
                
            case "podcast":

            case "video":

            default:
                System.out.println("Invalid type. Valid types book, podcast and video.");
        }



    }

    private void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void printAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void print() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
