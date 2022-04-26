package tqs.HW1.frontend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class FrontendSteps {
    
    private WebDriver wd;

    @Given("the following page {string}")
    public void setUp(String page){
        wd = new ChromeDriver();
        wd.get(page);
        wd.manage().window().setSize(new Dimension(1840, 1053));
    }

    @When("I select {string} in the country select")
    public void selectCountry(String country) throws InterruptedException{
        Thread.sleep(4000);
        Select dropdown = new Select(wd.findElement(By.id("selectCountry")));
        dropdown.selectByVisibleText(country);
    }

    @Then("I should see the recent and 6 months before covid details about {string}")
    public void checkDetails(String country) throws InterruptedException{
        String title = "Last 6 Months Info about " +country;
        Thread.sleep(7000);
        assertEquals(wd.findElement(By.id("sixMonthsTitle")).getText(), title);
        wd.quit();
    }

    @Then("the title should be {string}")
    public void cacheDetails(String title){
        assertEquals(wd.getTitle(), title);
        wd.quit();
    }
}
