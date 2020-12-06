package readingtips.ui;

import java.util.Arrays;
import java.util.List;
import readingtips.BlogPost;
import readingtips.Book;
import readingtips.Podcast;
import readingtips.Tip;
import readingtips.Video;
import readingtips.database.Dao;
import readingtips.database.TipDao;

public class Tui implements UI {

    IO scanner;
    TipDao tipDao;

    public Tui(IO scanner, TipDao tipDao) {
        this.scanner = scanner;
        this.tipDao = tipDao;
    }
    
    @Override
    public void launch() {
        scanner.print("Commands: \n"
                + "Add : Add new reading tip\n"
                + "Delete : Delete specific readig tip\n"
                + "Edit : Edit specific reading tip\n"
                + "Print All : Print title of all reading tips\n"
                + "Print : Print specific reading. You can search by title and tags\n"
                + "Help : Prints commands"
                + "Exit : Close the program");

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
                case "help":
                    help();
                    continue;
                default:
                    scanner.print("Invalid command.\n");
            }

        }
    }

    private void add() {
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
        printTags();
        scanner.print("Tags (comma seperated): ");
        String tagsString = scanner.nextLine();
        tags = Arrays.asList(tagsString.split("\\s*,\\s*"));
        scanner.print("Courses (comma seperated): ");
        String courseString = scanner.nextLine();
        courses = Arrays.asList(courseString.split("\\s*,\\s*"));

        switch (type.toLowerCase()) {
            case "book":
                scanner.print("ISBN :");
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
                    scanner.print("Timestamp information :");
                    positionComment = scanner.nextLine();
                    podcast.setPositionComment(positionComment);
                }
                tipDao.createTip(podcast);
                break;
            case "video":
                scanner.print("URL :");
                String url = scanner.nextLine();
                Video video = new Video(title, author, description, tags, courses, url);
                position = this.getTime();
                if (position != -1) {
                    video.setPosition(position);
                    scanner.print("Timestamp information :");
                    positionComment = scanner.nextLine();
                    video.setPositionComment(positionComment);
                }
                tipDao.createTip(video);
                break;
            case "blog post":
                scanner.print("URL :");
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
        String answer = scanner.nextLine().toLowerCase(); //TÄMÄ TOIMII LAURALLA
        //String answer = scanner.nextLine().strip().toLowerCase(); //TÄMÄ TOIMII MUILLA  
        if (!answer.equals("y")) {
            return (long) -1;
        }
        while (true) {
            try {
                scanner.print("Hours: ");
                long hours = Long.parseLong(scanner.nextLine()); //TÄMÄ TOIMII LAURALLA
                //long hours = Long.parseLong(scanner.nextLine().strip()); //TÄMÄ TOIMII MUILLA              
                scanner.print("Minutes: ");
                long minutes = Long.parseLong(scanner.nextLine()); //TÄMÄ TOIMII LAURALLA
                //long minutes = Long.parseLong(scanner.nextLine().strip()); //TÄMÄ TOIMII MUILLA 
                scanner.print("Seconds: ");
                //long seconds = Long.parseLong(scanner.nextLine().strip()); //TÄMÄ TOIMII MUILLA 
                long seconds = Long.parseLong(scanner.nextLine()); //TÄMÄ TOIMII LAURALLA             
                if (hours < 0 | minutes < 0 | minutes > 60 | seconds < 0 | seconds > 60) {
                    throw new IllegalArgumentException();
                }
                return (long) hours * 3600 + minutes * 60 + seconds;
            } catch (Exception e) {
                scanner.print("All values must be positive integers. Minutes and seconds must be in a range of 0-60");
            }
        }
    }
    
    private void printTagFiltered() {
        printTags();
        scanner.print("You can filter by one or more tags.\nTags (comma seperated): ");
        String tagsString = scanner.nextLine();
        List<String> tags = Arrays.asList(tagsString.split("\\s*,\\s*"));
        List<Tip> tipList = tipDao.getTipsTagFiltered(tags);
        printTips(tipList);
    }
    
    private void printTags() {
        List<String> tags = this.tipDao.getAllTags();
        scanner.print("\nExisting tags: ");
        scanner.print("");
        for (String tag:tags) {
            System.out.println(tag);
        }
        scanner.print("");
    }

    private void delete() {
        scanner.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        tipDao.deleteTip(id);
    }
    
    private void printTips(List<Tip> tipList) {
        scanner.print("");
        for (Tip tip : tipList) {
            scanner.print(tip.toString());
            scanner.print("");
        }
        
    }

    private void printAll() {
        List<Tip> tipList = tipDao.getAllTips();
        printTips(tipList);
    }

    private void printTip() {
        while (true) {
            scanner.print("For title search, type (title)\nFor tag search, type (tag)");
            String answer = scanner.nextLine();
            if (answer.toLowerCase().equals("title")) {
                this.printTitleFiltered();
                break;
            }
            if (answer.toLowerCase().equals("tag")) {
                this.printTagFiltered();
                break;
            }
        }
    }
    
    private void printTitleFiltered() {
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
            scanner.print("Choose attribute to change. Type 'done' when ready.");
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

    private void help() {
        scanner.print("Commands: \n"
            + "Add : Add new reading tip\n"
            + "Delete : Delete specific readig tip\n"
            + "Edit : Edit specific reading tip\n"
            + "Print All : Print title of all reading tips\n"
            + "Print : Print specific reading. You can search by title and tags\n"
            + "Help : Prints commands"
            + "Exit : Close the program");
    }

}
