/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingtips.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import readingtips.*;
import readingtips.database.TipDao;

/**
 *
 * @author aatukallio
 */
public class Tui implements UI {
    Scanner scanner;
    TipDao tipDao;
    
    public Tui (Scanner scanner) {
        this.scanner = scanner;
        this.tipDao = new TipDao();
    }
    
    @Override
    public void launch() {
        System.out.println("Commands: \n"
                + "Add : Add new reading tip\n"
                + "Delete : Delete specific readig tip\n"
                + "Edit : Edit specific reading tip\n"
                + "Print All : Print title of all reading tips\n"
                + "Print : Print specific reading tip by title\n" 
                + "Exit : Close the program");
        
        while(true) {
            System.out.println("Command:");
            switch(scanner.nextLine().toLowerCase()) {
                case "add":
                    add();   
                    continue;
                case "delete":
                    delete();
                    continue;
                case "exit":
                    return;
                case "print all":
                    printAll();
                    continue;
                case "print":
                    print();
                    continue;
                case "edit":
                    edit();
                    continue;
                default:
                    System.out.println("Invalid command.");
            }
            
        }
    }

    private void add() {
        String[] types = new String[] {"Book", "Podcast", "Video", "Blog Post"};
        
        String type;
        String title;
        String author;
        String description;
        List<String> tags;
        List<String> courses;
        
        System.out.println("Title: ");
        title = scanner.nextLine();
        System.out.println("Description: ");
        description = scanner.nextLine();
        System.out.println("Author: ");
        author = scanner.nextLine();
        System.out.println("Tags (comma seperated): ");
        String tagsString = scanner.nextLine();
        tags = Arrays.asList(tagsString.split("\\s*, \\s*"));
        System.out.println("Courses (comma seperated): ");
        String courseString = scanner.nextLine();
        courses = Arrays.asList(courseString.split("\\s*,"));
        
        System.out.println("Available types: " + Arrays.toString(types));
        System.out.println("Type: ");
        switch(scanner.nextLine().toLowerCase()) {
            case "book":
                System.out.println("ISBN :");
                String isbn = scanner.nextLine();
                Tip book = new Book(title, description, author, tags, courses, isbn);
                tipDao.createTip(book);
                break;
            case "podcast":
                System.out.println("Podcast name: ");
                String podcastName = scanner.nextLine();
                Tip podcast = new Podcast(title, author, description, tags, courses, podcastName);
                tipDao.createTip(podcast);
                break;
            case "video":
                System.out.println("URL :");
                String url = scanner.nextLine();
                Tip video = new Video(title, author, description, tags, courses, url);
                tipDao.createTip(video);
                break;
            case "blog post":
                System.out.println("URL :");
                String urlBlog = scanner.nextLine();
                Tip blogpost = new BlogPost(title, author, description, tags, courses, urlBlog);
                break;
            default:
                System.out.println("Invalid type. Valid types " + Arrays.toString(types));
        }



    }

    private void delete() {
        System.out.println("Title: ");
        String title = scanner.nextLine();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void printAll() {
        List<Tip> tipList = tipDao.getAllTips();
        for(Tip tip : tipList) {
            System.out.println(tip);
            System.out.println("");
        }
    }

    private void print() {
        System.out.println("Title: ");
        String title = scanner.nextLine();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void edit() {
        System.out.println("Title: ");
        String title = scanner.nextLine();
        
        Tip tip = new Book("Book",null,null,null,null,null);
        //System.out.println(tip);
        Boolean run = true;
        while(run) {
            System.out.println("Choose attribute to change. Type cancel to cancel.");
            switch(scanner.nextLine().toLowerCase()) {
                case "title":
                    System.out.println("Title: ");
                    String newTitle = scanner.nextLine();
                    continue;
                case "author":
                    System.out.println("Author: ");
                    String newAuthor = scanner.nextLine();
                    continue;
                case "description":
                    System.out.println("Description: ");
                    String newDescription = scanner.nextLine();
                    continue;
                case "tags":
                    System.out.println("Tags (comma seperated): ");
                    String tagsString = scanner.nextLine();
                    continue;
                case "courses":
                    System.out.println("Courses (comma seperated): ");
                    String coursesString = scanner.nextLine();
                    continue;
                case "url": 
                    if(tip.getType() != "Video" || tip.getType() != "BlogPost") {
                        System.out.println("Invalid attribute");
                        continue;
                    }
                    System.out.println("Url: ");
                    String url = scanner.nextLine();
                    continue;
                case "isbn":
                    if(tip.getType() != "Book") {
                        System.out.println("Invalid attribute");
                        continue;
                    }
                    System.out.println("ISBN: ");
                    String newIsbn = scanner.nextLine();
                    continue;
                case "podcast name":
                    if(tip.getType() != "Podcast") {
                        System.out.println("Invalid attribute");
                        continue;
                    }
                    System.out.println("Podcast name: ");
                    String newPodCastName = scanner.nextLine();
                    continue;
                case "cancel":
                    run = false;
                    continue;
                default:
                    System.out.println("Invalid attribute.");
            }
            
            
        }
        
    }
    
}
