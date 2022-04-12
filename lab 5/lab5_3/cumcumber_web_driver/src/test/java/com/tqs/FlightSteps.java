package com.tqs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.openqa.selenium.support.ui.Select;


public class FlightSteps {
    
    private final WebDriver driver = new ChromeDriver();


    @Given("I am on the BlazeDemo page")
    public void openWeb(){
        driver.get("https://www.blazedemo.com");
    }

    @When("I search for a flight from {string} to {string} and click the button")
    public void searchFlight(String from, String to){
        
        Select frmCountry = new Select(driver.findElement(By.name("fromPort")));
        frmCountry.selectByVisibleText(from);

        Select toCountry = new Select(driver.findElement(By.name("toPort")));
        toCountry.selectByVisibleText(to);

        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    }

    @Then("I choose the first flight and click {string}")
    public void pageProperties(String buttonString){
        driver.findElement(By.xpath("//input[@value='"+buttonString+"']")).click();
    }

    @Then("I fill the name input with {string}")
    public void fillName(String name){
        driver.findElement(By.id("inputName")).sendKeys(name);
    }

    @Then("I fill the address input with {string}")
    public void fillAddress(String address){
        driver.findElement(By.id("address")).sendKeys(address);
    }

    @Then("I fill the city input with {string}")
    public void fillCity(String city){
        driver.findElement(By.id("city")).sendKeys(city);
    }

    @Then("I fill the zipcode input with {string}")
    public void fillZipCode(String zipCode){
        driver.findElement(By.id("zipCode")).sendKeys(zipCode);
    }

    @Then("I select the {string} card")
    public void selectCard(String card){
        Select cardObject = new Select(driver.findElement(By.name("cardType")));
        cardObject.selectByVisibleText(card);
    }

    @Then("I fill the card with {int}")
    public void cardNumber(int cardNumber){
        String cardNumberS = Integer.toString(cardNumber);
        driver.findElement(By.id("creditCardNumber")).sendKeys(cardNumberS);
    }

    @Then("card's month is {int}")
    public void cardMonth(int month){
        String monthString = Integer.toString(month);
        driver.findElement(By.id("creditCardMonth")).sendKeys(monthString);
    }

    @Then("card's year is {int}")
    public void cardYear(int year){
        String yearString = Integer.toString(year);
        driver.findElement(By.id("creditCardYear")).sendKeys(yearString);
    }

    @Then("the name on card is {string}")
    public void cardName(String name){
        driver.findElement(By.id("nameOnCard")).sendKeys(name);
    }

    @Then("finnaly I click on the confirm button")
    public void clickButton(){
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    }

    @Then("I can see the confirmation order")
        public void confirmation(){
            assertTrue(driver.getTitle().contains("Confirmation"));
            driver.quit();
        }
}
