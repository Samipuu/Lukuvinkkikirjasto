package readingtips;
import java.util.Scanner;
import readingtips.ui.UIStub;
import readingtips.ui.Tui;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

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
        new Tui(ui).launch();

        //assertEquals("summa: 4\n", io.outputs.get(2));
        //ui.add();
    }
    
    @When("command add is given")
    public void commandAdd() {
        //Scanner scanner = new Scanner(System.in);
        UIStub ui2 = new UIStub("add", "title", "description", "author", "1", "2", "book", "isbn", "exit");
        new Tui(ui2).launch();
        
        assertEquals("Title: ", ui2.getOutputs().get(2));
        //assertEquals("summa: 4\n", io.outputs.get(2));
        //ui.add();
    }
    
    @Then("program is quit")
    public void questionTitleIsPrinted() {
        //assertTrue(io.getPrints().contains("Title"));
        //assertTrue(driver.getPageSource().contains("Title"));
        
        driver.close();
    
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