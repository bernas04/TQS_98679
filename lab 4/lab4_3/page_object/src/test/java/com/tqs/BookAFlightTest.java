package com.tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.HashMap;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class BookAFlightTest {

  private WebDriver driver;
  private Map<String, Object> vars;
  private static String PAGE_URL="https://blazedemo.com/";
  JavascriptExecutor js;



  @FindBy(name="fromPort")
  private WebElement fromSelect;

  @FindBy(xpath="//option[. = 'San Diego']")
  private WebElement fromCity;

  @FindBy(name="toPort")
  private WebElement toSelect;

  @FindBy(xpath="//option[. = 'Cairo']")
  private WebElement toCity;

  @FindBy(css=".btn-primary")
  private WebElement searchButton;

  @FindBy(css="tr:nth-child(4) .btn")
  private WebElement button;

  @FindBy(id="inputName")
  private WebElement inputName;

  @FindBy(id="address")
  private WebElement address;

  @FindBy(id="city")
  private WebElement city;

  @FindBy(id="state")
  private WebElement state;

  @FindBy(id="zipCode")
  private WebElement zipCode;

  @FindBy(id="creditCardNumber")
  private WebElement creditCardNumber;
  
  @FindBy(id="creditCardMonth")
  private WebElement creditCardMonth;
  
  @FindBy(id="creditCardYear")
  private WebElement creditCardYear;
  
  @FindBy(id="nameOnCard")
  private WebElement nameOnCard;
  
  @FindBy(id="rememberMe")
  private WebElement rememberMe;

  @FindBy(css=".btn-primary")
  private WebElement confirmButton;

  @FindBy(css=".hero-unit")
  private WebElement hero_unit;

  @FindBy(css="h1")
  private WebElement header;



  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }


  @AfterEach
  public void tearDown() {
    driver.quit();
  }


  @Test
  public void bookAFlight(WebDriver driver) {

    driver.get(PAGE_URL);
    driver.manage().window().setSize(new Dimension(1920, 1053));
    PageFactory.initElements(driver,this);
    
    //WebElement dropdown = driver.findElement(By.name("fromPort"));
    //dropdown.findElement(By.xpath("//option[. = 'San Diego']")).click();
    fromCity.click();
    
    //WebElement dropdown = driver.findElement(By.name("toPort"));
    //dropdown.findElement(By.xpath("//option[. = 'Cairo']")).click();
    toCity.click();

    //driver.findElement(By.css(".btn-primary")).click();
    searchButton.click();

    //driver.findElement(By.css("tr:nth-child(4) .btn")).click();
    button.click();
    
    //driver.findElement(By.id("inputName")).click();
    inputName.click();
    
    //driver.findElement(By.id("inputName")).sendKeys("João");
    inputName.sendKeys("João");

    //driver.findElement(By.id("address")).sendKeys("Farias");
    address.sendKeys("Farias");

    //driver.findElement(By.id("city")).sendKeys("Aveiro");
    city.sendKeys("Aveiro");

    //driver.findElement(By.id("state")).sendKeys("Aveiro");
    state.sendKeys("Aveiro");

    //driver.findElement(By.id("zipCode")).sendKeys("3800");
    zipCode.sendKeys("3800");

    //driver.findElement(By.id("creditCardNumber")).sendKeys("123456789");
    creditCardNumber.sendKeys("123456789");
    
    //driver.findElement(By.id("creditCardMonth")).sendKeys("10");
    creditCardMonth.sendKeys("10");

    //driver.findElement(By.id("creditCardYear")).sendKeys("2031");
    creditCardYear.sendKeys("2031");

    //driver.findElement(By.id("nameOnCard")).sendKeys("João Farias");
    nameOnCard.sendKeys("João Farias");

    //driver.findElement(By.id("rememberMe")).click();
    rememberMe.click();

    //driver.findElement(By.css(".btn-primary")).click();
    confirmButton.click();

    //assertEquals(driver.getTitle(), "BlazeDemo Confirmation");
    driver.getTitle().contains("BlazeDemo Confirmation");

    //driver.findElement(By.css(".hero-unit")).click();
    hero_unit.click();

    //driver.findElement(By.css("h1")).click();
    header.click();
    
    //driver.findElement(By.css("h1")).click();
    header.click();

  }
}
