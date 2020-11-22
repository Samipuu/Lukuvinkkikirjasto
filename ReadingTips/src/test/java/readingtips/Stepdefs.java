package readingtips;

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


    @Given("a book with a title and an isbn is added")
    public void searchForISBN() {
        Book book1 = new Book("BookTitle", null, null, null, null, "isbn");
        //driver.get(baseUrl);
        //WebElement element = driver.findElement(By.linkText("login"));       
        //element.click();   
    }

    @When("command getIsbn() is given")
    public void searchISBNforBook() {
        Book book1 = new Book("BookTitle", null, null, null, null, "isbn");
        book1.getIsbn();
    }

    @Then("{isbn} is printed")
    public void systemWillRespond(String isbn) throws Throwable {
        assertTrue(driver.getPageSource().contains(isbn));
        //assertTrue(pageContent("isbn"));
    }
}