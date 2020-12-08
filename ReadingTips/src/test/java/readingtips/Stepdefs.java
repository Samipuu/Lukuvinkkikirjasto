package readingtips;
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
import readingtips.ui.IO;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    //WebDriver driver = new HtmlUnitDriver();
    //String baseUrl = "http://localhost:4567";
    Tui tui;
    TipDao testDao;
    UIStub ui;
    
    @Before
    public void setUp() {
        
    }

    @Given("the system is launched")
    public void launched() {
        this.testDao = new TipDao();
    }
    
    
    @When("command exit is given")
    public void commandExit() {
        UIStub ui = new UIStub("exit");
        new Tui(ui, testDao).launch();
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
        Tui tui = new Tui(ui, testDao);
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
        Tui tui = new Tui(ui, testDao);
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

    @When("podcast is created with title {string} author {string} description {string}")
    public void createPodcast(String title, String description, String author) {
        UIStub ui2 = new UIStub("add","podcast", title, "Author", "description", "1", "2",  "Podcast name", "y", "0", "10", "10", "kommentti", "print all", "exit");
        Tui tui2 = new Tui(ui2, testDao);
        tui2.launch();
    }    

    @When("command print all is given")
    public void commandPrintAll() {
        ui = new UIStub("print all", "exit");
        Tui tui2 = new Tui(ui, testDao);
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
        Tui tui2 = new Tui(ui2, testDao);
        tui2.launch(); 
        
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
        Tui tui2 = new Tui(ui, testDao);
        tui2.launch(); 
    }
    
    @When("tip with title {string} is edit with attributes title {string} author {string} description {string} tags {string} courses {string}")
    public void editTip(String oldTitle, String title, String author, String description, String tags, String courses) {
        String id = String.valueOf(getTipByTitle(oldTitle).getId());
        UIStub ui2 = new UIStub("edit", id, "title", title, "author", author, "description", description, "tags", tags, "courses", courses, "done", "exit");
        Tui tui = new Tui(ui2, testDao);
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
    
    @When("tip with title {string} is commanded to print")
    public void printByTitle(String title) {
        ui = new UIStub("print all", "title", title, "exit");
        tui = new Tui(ui, testDao);
        tui.launch();
    }
    

    @Then("tip with title {string} is printed")
    public void tipHasBeenPrinted(String title) {
        Boolean contain = outputContainsString(title);
        assertEquals(true, contain);
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

    // Scenario: User can read a book's isbn
    //     Given a book with a title and an isbn is added
    //     When command getIsbn is given
    //     Then isbn is printed

    // @Given("a book with a title and an isbn is added")
    // public void searchForISBN() {
    //     Book book1 = new Book("BookTitle", null, null, null, null, "isbn");
    //     //driver.get(baseUrl);
    //     //WebElement element = driver.findElement(By.linkText("login"));       
    //     //element.click();   
    // }

    // @When("command getIsbn is given")
    // public void commandGetIsbnIsGiven() {
    //     // Write code here that turns the phrase above into concrete actions
    //     Book book2 = new Book("BookTitle", null, null, null, null, "isbn");
    //     book2.getIsbn();        
    //     throw new io.cucumber.java.PendingException();
    // }

    // @Then("isbn is printed")
    // public void systemWillRespond(String isbn) throws Throwable {
    //     assertTrue(driver.getPageSource().contains("isbn"));
    //     //assertTrue(pageContent("isbn"));
    // }

}