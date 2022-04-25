package tqs.HW1.frontend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class FrontendSteps {
    
    private WebDriver wd;

    @When("I open thw browser and access the page {string}")
    public void setUp(String page){
        wd = new ChromeDriver();
        wd.get(page);
    }

    @And("I select {string} in the country select")
    public void selectCountry(String country){
        Select dropdown = new Select(wd.findElement(By.id("selectCountry")));
        dropdown.selectByVisibleText(country);
    }

    @Then("I should see the recent and 6 months before covid details about {string}")
    public void checkDetails(String country){
        String title = "Last 6 Months Info about " +country;
        assertEquals(wd.findElement(By.id("sixMonthsTitle")), title);
        wd.quit();
    }
}
