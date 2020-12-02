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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.mockito.Mockito.*;
import org.mockito.stubbing.OngoingStubbing;
import readingtips.database.TipDao;
import readingtips.ui.IO;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    Tui tui;
    TipDao mockDao;
    
    @Before
    public void setUp() {
        mockDao = mock(TipDao.class);
    }

    @Given("the system is launched")
    public void launched() {
        // Scanner scanner = new Scanner(System.in);
        // Tui ui = new Tui(scanner);
        // ui.launch();
    }
    
    
    @When("command exit is given")
    public void commandExit() {
        //Scanner scanner = new Scanner(System.in);
        UIStub ui = new UIStub("exit");
        new Tui(ui, mockDao).launch();

        
        //assertEquals("summa: 4\n", io.outputs.get(2));
        //ui.add();
    }
    
    
    @When("command add is given")
    public void commandAdd() {
        UIStub ui2 = new UIStub("add", "title", "author", "description", "1", "2", "book", "isbn", "exit");
        mockDao = mock(TipDao.class);
        Tui tui2 = new Tui(ui2, mockDao);
        tui2.launch();
        
        //ArrayList<String> tags = new ArrayList<>() {{ //TÄMÄ TOIMII MUILLA
        ArrayList<String> tags = new ArrayList<String>() {{ //TÄMÄ TOIMII LAURALLA     
                add("1");
            }};
        //ArrayList<String> courses = new ArrayList<>() {{ //TÄMÄ TOIMII MUILLA
        ArrayList<String> courses = new ArrayList<String>() {{ //TÄMÄ TOIMII LAURALLA
                add("2");
            }};
        
        verify(mockDao).createTip(new Book("title", "author", "description", tags, courses, "isbn"));
        verify(mockDao, times(1)).createTip(new Book("title", "author", "description", tags, courses, "isbn"));
        assertEquals("Title: ", ui2.getOutputs().get(2));
    }
    
    @When("book is created with title {string} description {string} author {string}")
    public void createBook(String title, String description, String author) {
        UIStub ui2 = new UIStub("add", title, author, description, "1", "2", "book", "isbn", "exit");
        mockDao = mock(TipDao.class);
        Tui tui2 = new Tui(ui2, mockDao);
        tui2.launch();
        
        //ArrayList<String> tags = new ArrayList<>() {{ //TÄMÄ TOIMII MUILLA
        ArrayList<String> tags = new ArrayList<String>() {{ //TÄMÄ TOIMII LAURALLA
                add("1");
            }};
        //ArrayList<String> courses = new ArrayList<>() {{ //TÄMÄ TOIMII MUILLA
        ArrayList<String> courses = new ArrayList<String>() {{ //TÄMÄ TOIMII LAURALLA
                add("2");
            }};
        
        verify(mockDao).createTip(new Book(title, author, description, tags, courses, "isbn"));
        assertEquals("Title: ", ui2.getOutputs().get(2));
    }

    @When("podcast is created with title {string} description {string} author {string}")
    public void createPodcast(String title, String description, String author) {
        UIStub ui2 = new UIStub("add", title, "Author", "description", "1", "2", "podcast", "Podcast name", "y", "0", "10", "10", "kommentti", "print all", "exit");
        mockDao = mock(TipDao.class);
        Tui tui2 = new Tui(ui2, mockDao);
        tui2.launch();
        
        //ArrayList<String> tags = new ArrayList<>() {{ //TÄMÄ TOIMII MUILLA
        ArrayList<String> tags = new ArrayList<String>() {{ //TÄMÄ TOIMII LAURALLA
                add("1");
            }};
        //ArrayList<String> courses = new ArrayList<>() {{ //TÄMÄ TOIMII MUILLA
        ArrayList<String> courses = new ArrayList<String>() {{ //TÄMÄ TOIMII LAURALLA
                add("2");
            }};
        
        //verify(mockDao).createTip(new Podcast(title, author, description, tags, courses, "Podcast Name", "10 min 10 s", "kommentti"));
    //     verify(mockDao).createTip(new Podcast(title, author, description, tags, courses, "Podcast Name"));

    //     assertEquals("Title: ", ui2.getOutputs().get(2));
    }    

    @When("command print all is given")
    public void commandPrintAll() {
        UIStub ui2 = new UIStub("add", "Test Book", "description", "Author", "1", "2", "book", "isbn", "print all", "exit");
        mockDao = mock(TipDao.class);
        Tui tui2 = new Tui(ui2, mockDao);
        tui2.launch();
        //UIStub ui3 = new UIStub("print all");
        //new Tui(ui3, mockDao).launch();              
    }

        
    @Then("book title {string} is returned")
    public void printAllShowBookPrints(String title) {
        UIStub ui2 = new UIStub("add", "Test Book", "description", "Author", "1", "2", "book", "isbn", "print all", "exit");
        mockDao = mock(TipDao.class);
        Tui tui2 = new Tui(ui2, mockDao);
        tui2.launch(); 
        System.out.print(title);
        //assertEquals(title, ui2.getOutputs().get(3));
        
    }
    @Then("podcast title {string} is returned")
    public void printAllShowPodcastPrints(String title) {
        UIStub ui2 = new UIStub("add", title, "description", "Author", "1", "2", "podcast", "Podcast name", "y", "0", "10", "10", "kommentti", "print all", "exit");
        mockDao = mock(TipDao.class);
        Tui tui2 = new Tui(ui2, mockDao);
        tui2.launch(); 
        System.out.print(title);
        //assertEquals(title, ui2.getOutputs().get(3));
        
    }
    
    @Then("Book is created")
    public void checkBook() {
        
    }
    
    
    @Then("program is quit")
    public void questionTitleIsPrinted() {
        //assertTrue(io.getPrints().contains("Title"));
        //assertTrue(driver.getPageSource().contains("Title"));
        
        driver.close();
    
    } 
    
    @When("book is edit with id 1 with attributes title {string} author {string} description {string}")
    public void bookCanBeEdit(String title, String author, String description) {
        UIStub ui2 = new UIStub("edit", "1", "title", title, "done", "print all", "exit");
        mockDao = mock(TipDao.class);
        
        ArrayList<String> tags = new ArrayList<String>() {{ //TÄMÄ TOIMII LAURALLA
                add("1");
            }};
        //ArrayList<String> courses = new ArrayList<>() {{ //TÄMÄ TOIMII MUILLA
        ArrayList<String> courses = new ArrayList<String>() {{ //TÄMÄ TOIMII LAURALLA
                add("2");
            }};
        
        Book book = new Book("Title", "Author", "Description", tags, courses, "12345");
        mockDao.createTip(book);
        Tui tui2 = new Tui(ui2, mockDao);
        tui2.launch(); 
        
        //verify(mockDao).editTip(1);
        //assertTrue(ui2.getOutputs().contains(title));
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