package readingtips.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import readingtips.entity.BlogPost;
import readingtips.entity.Book;
import readingtips.entity.Podcast;
import readingtips.entity.Tip;
import readingtips.entity.Video;
import readingtips.database.TipDao;
import readingtips.system.SystemAccess;

public class Tui implements UI {

    IO scanner;
    TipDao tipDao;
    SystemAccess systemAccess;

    public Tui(IO scanner, TipDao tipDao, SystemAccess systemAccess) {
        this.scanner = scanner;
        this.tipDao = tipDao;
        this.systemAccess = systemAccess;
    }
    
    @Override
    public void launch() {
        scanner.print("Commands: \n"
                + "Add : Add a new reading tip\n"
                + "Delete : Delete a specific reading tip\n"
                + "Edit : Edit a specific reading tip\n"
                + "Open : Open a specific reading tip with an external program\n"                
                + "Print All : Print titles of all reading tips\n"
                + "Print : Print a specific reading tip. You can search by title, tags and courses\n"
                + "Help : Print all commands\n"
                + "Exit : Close the program\n");

        while (true) {
            scanner.print("Command:");
            switch (scanner.nextLine().toLowerCase()) {
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
                    printTip();
                    continue;
                case "edit":
                    edit();
                    continue;
                case "open":
                    open();
                    continue;
                case "help":
                    help();
                    continue;
                default:
                    scanner.print("Invalid command.\n");
            }

        }
    }

    private void add() {
        scanner.print("\nYou are adding a new reading tip. Please give the inputs.");        
        String[] types = new String[]{"book", "podcast", "video", "blog post"};
        
        String type;
        String positionComment;
        Long position;
        String title;
        String author;
        String description;
        List<String> tags;
        List<String> courses;
        
        scanner.print("Available types: " + Arrays.toString(types));
        while(true) {
            scanner.print("Type: ");
            type = scanner.nextLine().toLowerCase();
            if(!Arrays.asList(types).contains(type)) {
                System.out.println("Invalid type. Valid types " + Arrays.toString(types));
            } else {
                break;
            }    
        }
        
        scanner.print("Title: ");
        title = scanner.nextLine();
        scanner.print("Author: ");
        author = scanner.nextLine();
        scanner.print("Description: ");
        description = scanner.nextLine();
        printList("tags");
        scanner.print("Tags (comma seperated): ");
        String tagsString = scanner.nextLine();
        tags = Arrays.asList(tagsString.split("\\s*,\\s*"));
        printList("courses");
        scanner.print("Courses (comma seperated): ");
        String courseString = scanner.nextLine();
        courses = Arrays.asList(courseString.split("\\s*,\\s*"));

        switch (type.toLowerCase()) {
            case "book":
                scanner.print("ISBN: ");
                String isbn = scanner.nextLine();
                Book book = new Book(title, author, description, tags, courses, isbn);
                tipDao.createTip(book);
                break;
            case "podcast":
                scanner.print("Podcast name: ");
                String podcastName = scanner.nextLine();
                Podcast podcast = new Podcast(title, author, description, tags, courses, podcastName);
                position = this.getTime();
                if (position != -1) {
                    podcast.setPosition(position);
                    scanner.print("Timestamp information: ");
                    positionComment = scanner.nextLine();
                    podcast.setPositionComment(positionComment);
                }
                tipDao.createTip(podcast);
                break;
            case "video":
                scanner.print("URL: ");
                String url = scanner.nextLine();
                Video video = new Video(title, author, description, tags, courses, url);
                position = this.getTime();
                if (position != -1) {
                    video.setPosition(position);
                    scanner.print("Timestamp information: ");
                    positionComment = scanner.nextLine();
                    video.setPositionComment(positionComment);
                }
                tipDao.createTip(video);
                break;
            case "blog post":
                scanner.print("URL: ");
                String urlBlog = scanner.nextLine();
                Tip blogpost = new BlogPost(title, author, description, tags, courses, urlBlog);
                tipDao.createTip(blogpost);
                break;
            default:
                scanner.print("Invalid type. Valid types " + Arrays.toString(types));
        }

    }

    private Long getTime() {
        scanner.print("Insert timestamp? Type (y) if yes :");
        String answer = scanner.nextLine().toLowerCase();
        if (!answer.equals("y")) {
            return (long) -1;
        }
        while (true) {
            try {
                scanner.print("Hours: ");
                long hours = Long.parseLong(scanner.nextLine());            
                scanner.print("Minutes: ");
                long minutes = Long.parseLong(scanner.nextLine()); 
                scanner.print("Seconds: ");
                long seconds = Long.parseLong(scanner.nextLine());         
                if (hours < 0 | minutes < 0 | minutes > 60 | seconds < 0 | seconds > 60) {
                    throw new IllegalArgumentException();
                }
                return (long) hours * 3600 + minutes * 60 + seconds;
            } catch (Exception e) {
                scanner.print("All values must be positive integers. Minutes and seconds must be in the range of 0-60\n");
            }
        }
    }
    
    private void printTagFiltered() {
        printList("tags");
        scanner.print("You can filter by one or more tags.\nTags (comma seperated): ");
        String tagsString = scanner.nextLine();
        List<String> tags = Arrays.asList(tagsString.split("\\s*,\\s*"));
        List<Tip> tipList = tipDao.getTipsTagFiltered(tags);
        printTips(tipList);
    }
    
    private void printCoursesFiltered() {
        printList("courses");
        scanner.print("You can filter by one or more courses.\nCourses (comma seperated): ");
        String coursesString = scanner.nextLine();
        List<String> courses = Arrays.asList(coursesString.split("\\s*,\\s*"));
        List<Tip> courseList = tipDao.getTipsCourseFiltered(courses);
        printTips(courseList);        
    }
    
    private void printList(String type) {
        List<String> list = new ArrayList<>();
        if(type.equals("courses")) {
            list = tipDao.getAllCourses();
        } else if (type.equals("tags")) {
            list = tipDao.getAllTags();
        }
        
        scanner.print("\nExisting " + type + ": ");
        scanner.print("");
        for (String item : list) {
            System.out.println(item);
        }
        scanner.print("");
    }

    private void delete() {
        scanner.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Tip tip = getTipById(id);
        if(tip == null) {
            System.out.println("ID not found");
            return;
        }        
        String header = tip.getTitle();
        String text= "Delete successfull. You deleted " + header + "\n";
        tipDao.deleteTip(id);
        scanner.print(text);
    }
    
    private void printTips(List<Tip> tipList) {
        scanner.print("");
        for (Tip tip : tipList) {
            scanner.print(tip.toString());
            scanner.print("");
        }
        
    }

    private void printAll() {
        scanner.print("\nAll existing reading tips: \n");
        List<Tip> tipList = tipDao.getAllTips();
        printTips(tipList);
    }

    private void printTip() {

        
        OUTER:
        while (true) {
            scanner.print("For title search, type (title)\n"
                + "For tag search, type (tag)\n"
                + "For course search, type (course)\n"
                + "Type (end) to end search\n");
            scanner.print("Search type:");

            String answer = scanner.nextLine();
            switch (answer.toLowerCase()) {
                case "title":
                    this.printTitleFiltered();
                    break;
                case "tag":
                    this.printTagFiltered();
                    break;
                case "course":
                    this.printCoursesFiltered();
                    break;
                case "end":
                    break OUTER;
                    
                default:
                    System.out.println("Invalid search type");
            }
        }
    }
    
    private void printTitleFiltered() {
        scanner.print("\nSearching by title: \n");
        scanner.print("Title: ");
        String title = scanner.nextLine();
        List<Tip> tipList = tipDao.getAllTips();
        for (Tip tip : tipList) {
            if (tip.getTitle().toLowerCase().startsWith(title) || tip.getTitle().startsWith(title)) {
                scanner.print(tip.toString());
            }
        }
    }

    private Tip getTipByTitle(String title) {
        List<Tip> tipList = tipDao.getAllTips();
        for (Tip tip : tipList) {
            if (tip.getTitle().toLowerCase().equals(title) || tip.getTitle().equals(title)) {
                return tip;
            }
        }
        return null;
    }

    private Tip getTipById(int id) {
        return tipDao.getAllTips().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    private void edit() {
        scanner.print("\nYou are editing a reading tip. Please give the inputs: \n");
        scanner.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Tip tip = getTipById(id);
        
        if(tip == null) {
            System.out.println("ID not found");
            return;
        }
        
        System.out.println(tip);
        Boolean run = true;
        while (run) {
            scanner.print("Choose an attribute to change. Type 'done' when ready.\n");
            switch (scanner.nextLine().toLowerCase()) {
                case "title":
                    scanner.print("Title: ");
                    String newTitle = scanner.nextLine();
                    tip.setTitle(newTitle);
                    continue;
                case "author":
                    scanner.print("Author: ");
                    String newAuthor = scanner.nextLine();
                    tip.setAuthor(newAuthor);
                    continue;
                case "description":
                    scanner.print("Description: ");
                    String newDescription = scanner.nextLine();
                    tip.setDescription(newDescription);
                    continue;
                case "tags":
                    scanner.print("Tags (comma seperated): ");
                    String tagsString = scanner.nextLine();
                    List<String> tags = Arrays.asList(tagsString.split("\\s*,\\s*"));
                    tip.setTags(tags);
                    continue;
                case "courses":
                    scanner.print("Courses (comma seperated): ");
                    String coursesString = scanner.nextLine();
                    List<String> courses = Arrays.asList(coursesString.split("\\s*,\\s*"));
                    tip.setCourses(courses);
                    continue;
                case "url":
                    if (!tip.getType().equals("Video") || !tip.getType().equals("BlogPost")) {
                        scanner.print("Invalid attribute");
                        continue;
                    }
                    scanner.print("Url: ");
                    String url = scanner.nextLine();
                    if (tip.getType().equals("Video")) {
                        Video video = (Video) tip;
                        video.setUrl(url);
                    } else {
                        BlogPost blog = (BlogPost) tip;
                        blog.setUrl(url);
                    }
                    continue;
                case "isbn":
                    if (!tip.getType().equals("Book")) {
                        scanner.print("Invalid attribute");
                        continue;
                    }
                    scanner.print("ISBN: ");
                    String newIsbn = scanner.nextLine();
                    Book book = (Book) tip;
                    book.setIsbn(newIsbn);
                    continue;
                case "podcast name":
                    if (!tip.getType().equals("Podcast")) {
                        scanner.print("Invalid attribute");
                        continue;
                    }
                    scanner.print("Podcast name: ");
                    String newPodCastName = scanner.nextLine();
                    Podcast podcast = (Podcast) tip;
                    podcast.setPodcastName(newPodCastName);
                    continue;
                case "done":
                    run = false;
                    continue;
                default:
                    scanner.print("Invalid attribute.");

            }

        }
        
        tipDao.editTip(id);

    }

    private void open() {
        scanner.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Tip tip = getTipById(id);
        
        if(tip == null) {
            System.out.println("ID not found");
            return;
        }

        systemAccess.open(tip);
    }

    private void help() {
        scanner.print("Commands: \n"
            + "Add : Add a new reading tip\n"
            + "Delete : Delete a specific reading tip\n"
            + "Edit : Edit a specific reading tip\n"
            + "Open : Open a specific reading tip with an external program\n"             
            + "Print All : Print titles of all reading tips\n"
            + "Print : Print a specific reading tip. You can search by title, tags or courses\n"
            + "Help : Print all commands\n"
            + "Exit : Close the program\n");
    }

}
