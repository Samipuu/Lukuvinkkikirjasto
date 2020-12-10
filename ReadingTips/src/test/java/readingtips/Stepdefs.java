package readingtips;
import readingtips.entity.Tip;
import java.util.Scanner;
import readingtips.ui.UIStub;
import readingtips.ui.Tui;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.assertj.core.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.stubbing.OngoingStubbing;
import readingtips.database.TipDao;
import readingtips.database.TipDaoTest;
import readingtips.system.SystemAccess;
import readingtips.ui.IO;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    //WebDriver driver = new HtmlUnitDriver();
    //String baseUrl = "http://localhost:4567";
    Tui tui;
    TipDao testDao;
    UIStub ui;
    SystemAccess systemAccess;
    
    @Before
    public void setUp() {
        
    }

    @Given("the system is launched")
    public void launched() {
        systemAccess = mock(SystemAccess.class);
        this.testDao = new TipDao();
    }
    
    
    @When("command exit is given")
    public void commandExit() {
        UIStub ui = new UIStub("exit");
        new Tui(ui, testDao, systemAccess).launch();
    }
    
    
    @When("command add is given with type {string}")
    public void commandAdd(String type) {
        UIStub ui = new UIStub("exit");
        switch(type) {
            case("book"):
                ui = new UIStub("add", type, "title", "author", "description", "1", "2", "isbn", "exit");
                break;
            case("video"):
                ui = new UIStub("add", type, "title", "Author", "description", "1", "2",  "url", "y", "0", "10", "10", "kommentti", "exit");
                break;
            case("blog post"):
                ui = new UIStub("add", type, "title", "author", "description", "1", "2", "url", "exit");
                break;
            case("podcast"):
                ui = new UIStub("add", type, "title", "Author", "description", "1", "2",  "Podcast name", "y", "0", "10", "10", "kommentti", "exit");
                break;
        }
        Tui tui = new Tui(ui, testDao, systemAccess);
        tui.launch();
    }
    
    @Then("tip with type {string} is created")
    public void checkType(String type) {
        assertEquals(type, testDao.getAllTips().get(testDao.getAllTips().size() - 1).getType().toLowerCase());
    }
    
    @When("tip is created with type {string} title {string}")
    public void createTip(String type, String title) {
        createTip(type, title, "author", "description");
    }
    
    @When("tip is created with type {string} title {string} author {string} description {string}")
    public void createTip(String type, String title, String author, String description) {
        UIStub ui = new UIStub("exit");
        switch(type) {
            case("book"):
                ui = new UIStub("add", type, title, author, description, "1", "2", "isbn", "exit");
                break;
            case("video"):
                ui = new UIStub("add", type, title, author, description, "1", "2",  "url", "y", "0", "10", "10", "kommentti", "exit");
                break;
            case("blog post"):
                ui = new UIStub("add", type, title, author, description, "1", "2", "url", "exit");
                break;
            case("podcast"):
                ui = new UIStub("add", type, title, author, description, "1", "2",  "Podcast name", "y", "0", "10", "10", "kommentti", "exit");
                break;
        }
        Tui tui = new Tui(ui, testDao, systemAccess);
        tui.launch();
    }
    
    @Then("tip with type {string} title {string} author {string} description {string} is created")
    public void checkTip(String type, String title, String author, String description) {
        Tip tip = getTipByTitle(title);
        assertEquals(type, tip.getType().toLowerCase());
        assertEquals(author, tip.getAuthor());
        assertEquals(description, tip.getDescription());
        assertEquals(title, tip.getTitle());        
    }

    @When("tip is created with type {string} title {string} tag {string}")
    public void tipWithTagsIsCreated(String type, String title, String tag) {
        UIStub ui2 = new UIStub("add", type, title, "Author", "description", tag, "2",  "isbn", "print all", "exit");
        Tui tui2 = new Tui(ui2, testDao, systemAccess);
        tui2.launch();                
    }

    @When("tip is created with type {string} title {string} course {string}")
    public void tipWithCourseIsCreated(String type, String title, String course) {
        UIStub ui2 = new UIStub("add", type, title, "Author", "description", "1", course,  "isbn", "print all", "exit");
        Tui tui2 = new Tui(ui2, testDao, systemAccess);
        tui2.launch();                
    }        

    @When("podcast is created with title {string} author {string} description {string}")
    public void createPodcast(String title, String description, String author) {
        UIStub ui2 = new UIStub("add","podcast", title, "Author", "description", "1", "2",  "Podcast name", "y", "0", "10", "10", "kommentti", "print all", "exit");
        Tui tui2 = new Tui(ui2, testDao, systemAccess);
        tui2.launch();
    }    
    @When("blog post is created with title {string} author {string} description {string}")
    public void createBlog(String title, String description, String author) {
        UIStub ui2 = new UIStub("add","blog post", title, "Author", "description", "1", "2",  "www.url.fi", "print all", "exit");
        Tui tui2 = new Tui(ui2, testDao, systemAccess);
        tui2.launch();
    }    




    @When("command print all is given")
    public void commandPrintAll() {
        ui = new UIStub("print all", "exit");
        Tui tui2 = new Tui(ui, testDao, systemAccess);
        tui2.launch();          
    }

    @When("commands print and tag and {string} are given")
    public void commandPrintByTag(String tag) {
        ui = new UIStub("print", "tag", tag, "end", "exit");
        Tui tui2 = new Tui(ui, testDao, systemAccess);
        tui2.launch();          
    }

    @When("commands print and course and {string} are given")
    public void commandPrintByCourse(String course) {
        ui = new UIStub("print", "course", course, "end", "exit");
        tui = new Tui(ui, testDao, systemAccess);
        tui.launch();
    }
    
    @When("commands print and title and {string} are given")
    public void commandPrintByTitle(String title) {
        ui = new UIStub("print", "title", title, "end", "exit");
        Tui tui2 = new Tui(ui, testDao, systemAccess);
        tui2.launch();          
    }

    @Then("tip with type {string} is returned")
    public void printAllShowsCreatedTip(String type) {
        Boolean contain = outputContainsString(type);
        assertEquals(true, contain);
    }
        
    @Then("book title {string} is returned")
    public void printAllShowBookPrints(String title) {
        Boolean contain = outputContainsString(title);
        assertEquals(true, contain);
        
    }
    @Then("podcast title {string} is returned")
    public void printAllShowPodcastPrints(String title) {
        UIStub ui2 = new UIStub("add","podcast", title, "description", "Author", "1", "2",  "Podcast name", "y", "0", "10", "10", "kommentti", "print all", "exit");
        Tui tui2 = new Tui(ui2, testDao, systemAccess);
        tui2.launch(); 
        
    }
    @Then("blog post title {string} is returned")
    public void printAllShowBlogPrints(String title) {
        Boolean contain = outputContainsString(title);
        assertEquals(true, contain);
    }
    @Then("video title {string} is returned")
    public void printAllShowVideoPrints(String title) {
        Boolean contain = outputContainsString(title);
        assertEquals(true, contain);
    }    
    
    
    @Then("program is quit")
    public void questionTitleIsPrinted() {
        //assertTrue(io.getPrints().contains("Title"));
        //assertTrue(driver.getPageSource().contains("Title"));
        
        //driver.close();
    
    } 
    
    
    @When("book with id 1 is edit with attributes title {string} author {string} description {string}")
    public void bookCanBeEdit(String title, String author, String description) {
        ui = new UIStub("edit", "1", "title", title, "done", "print all", "exit");
        Tui tui2 = new Tui(ui, testDao, systemAccess);
        tui2.launch(); 
    }
    
    @When("book with id 1 is edit with attributes title {string} author {string} description {string} isbn {string}")
    public void bookCanBeEditWithISBN(String title, String author, String description, String isbn) {
        ui = new UIStub("edit", "1", "title", title, "isbn", isbn, "done", "print all", "exit");
        Tui tui2 = new Tui(ui, testDao, systemAccess);
        tui2.launch(); 
    }

    @When("tip with title {string} is edit with attributes title {string} author {string} description {string} tags {string} courses {string}")
    public void editTip(String oldTitle, String title, String author, String description, String tags, String courses) {
        String id = String.valueOf(getTipByTitle(oldTitle).getId());
        UIStub ui2 = new UIStub("edit", id, "title", title, "author", author, "description", description, "tags", tags, "courses", courses, "done", "exit");
        Tui tui = new Tui(ui2, testDao, systemAccess);
        tui.launch();
    }

    @When("tip with title {string} is edit with attributes title {string} author {string} description {string} url {string} podcast name {string}")
    public void editTipPodcast(String oldTitle, String title, String author, String description, String url, String podcastName) {
        String id = String.valueOf(getTipByTitle(oldTitle).getId());
        UIStub ui2 = new UIStub("edit", id, "title", title, "author", author, "description", description, "url", url, "podcast name", podcastName, "done", "exit");
        Tui tui = new Tui(ui2, testDao, systemAccess);
        tui.launch();
    }
    
    @When("video with title {string} is edit with attributes title {string} author {string} url {string}")
    public void editVideo(String oldTitle, String title, String author, String url) {
        String id = String.valueOf(getTipByTitle(oldTitle).getId());
        UIStub ui2 = new UIStub("edit", id, "title", title, "author", author, "url", url, "done", "exit");
        Tui tui = new Tui(ui2, testDao, systemAccess);
        tui.launch();
    }    

    @Then("tip has been changed with attributes {string} author {string} description {string} tags {string} courses {string}")
    public void checkTipChange(String title, String author, String description, String tags, String courses) {
        Tip tip = getTipByTitle(title);
        List<String> tagList = new ArrayList<>();
        Collections.addAll(tagList, tags.split("\\s*,\\s"));
        List<String> courseList = new ArrayList<>();
        Collections.addAll(courseList, courses.split("\\s*,\\s"));
        assertEquals(title, tip.getTitle());
        assertEquals(author, tip.getAuthor());
        assertEquals(description, tip.getDescription());
        assertEquals(tagList, tip.getTags());
        assertEquals(courseList, tip.getCourses());
    }


    @Then("tip has been changed with attributes {string} author {string} url {string}")
    public void checkTipChangeUrl(String title, String author, String url) {
        Tip tip = getTipByTitle(title);
        assertEquals(title, tip.getTitle());
        assertEquals(author, tip.getAuthor());
        //assertEquals(url, tip.getUrl()); SAISIKO TÄN TOIMIMAAN?
        
    }

    @Then("tip has been changed with attributes {string} author {string} description {string} url {string} podcast name {string}")
    public void checkTipChangePodcast(String title, String author, String description, String tags, String courses) {
        Tip tip = getTipByTitle(title);
        assertEquals(title, tip.getTitle());
        assertEquals(author, tip.getAuthor());
        assertEquals(description, tip.getDescription());
        //assertEquals(url, tip.getUrl());
        //assertEquals(podcastName, tip.getPodcastName());
    }    
    
    @When("tip with title {string} is commanded to print")
    public void printByTitle(String title) {
        ui = new UIStub("print all", "title", title, "exit");
        tui = new Tui(ui, testDao, systemAccess);
        tui.launch();
    }
    

    @Then("tip with title {string} is printed")
    public void tipHasBeenPrinted(String title) {
        Boolean contain = outputContainsString(title);
        assertEquals(true, contain);
    }

    @When("book with title {string} is deleted")
    public void deleteBook(String title) {
        UIStub ui = new UIStub("exit");  
        String id = String.valueOf(getTipByTitle(title).getId());   
        ui = new UIStub("delete", id, "exit");           
        Tui tui = new Tui(ui, testDao, systemAccess);
        tui.launch();        
    }

    @Then("book title {string} is not printed")
    public void tipHasNotBeenPrinted(String title) {
        Boolean contain = outputContainsString(title);
        assertEquals(false, contain);
    }    


    @When("command {string} is given")
    public void printHelp(String command) {
        UIStub ui = new UIStub("exit");  
        ui = new UIStub(command, "exit");           
        Tui tui = new Tui(ui, testDao, systemAccess);
        tui.launch();        
    }

    @Then("text {string} is printed")
    public void printHelpCommands(String command) {
        ui = new UIStub(command, "exit");           
        Tui tui = new Tui(ui, testDao, systemAccess);
        tui.launch();        
        Boolean contain = outputContainsString(command);
        assertEquals(true, contain);
    }
    
    @When("video is created with title {string} author {string} description {string} url {string}")
    public void createVideo(String title, String author, String description, String url) {
        UIStub ui2 = new UIStub("add","video", title, author, description, "1", "2",  url, "y", "0", "10", "10", "kommentti", "print all", "exit");
        tui = new Tui(ui2, testDao, systemAccess);
        tui.launch();
    } 
    
    @When("command open is given with ID of video with title {string}")
    public void openVideo(String title) {
        String id = String.valueOf(getTipByTitle(title).getId());
        ui = new UIStub("open", id, "exit");
        tui = new Tui(ui, testDao, systemAccess);
        tui.launch();
    }
    
    @When("command open is given with ID of podcast with title {string}")
    public void openPodcast(String title) {
        openVideo(title);
    }

    @Then("video will open with title {string}")
    public void checkVideoTitle(String title) {
        Tip tip = getTipByTitle(title);
        verify(systemAccess).open(tip);
    }
    
    @Then("podcast will open with title {string}")
    public void checkPodCastOpen(String title) {
        checkVideoTitle(title);
    }




    
    private boolean outputContainsString(String value) {
        Boolean contain = false;
        for(String output : ui.getOutputs()) {
            if(output.contains(value)) {              
                contain = true;
            }          
        }
        return contain;
    }
    
    private Tip getTipByTitle(String title) {
        List<Tip> tipList = testDao.getAllTips();
        for (Tip tip : tipList) {
            if (tip.getTitle().toLowerCase().equals(title) || tip.getTitle().equals(title)) {
                return tip;
            }
        }
        return null;
    }

}