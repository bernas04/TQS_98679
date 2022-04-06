// Generated by Selenium IDE
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


import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class BookAFlightTest {

  private WebDriver driver;
  private Map<String, Object> vars;
  
  JavascriptExecutor js;

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
  public void bookAFlight() {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(1920, 1053));
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'San Diego']")).click();
    }
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'Cairo']")).click();
    }
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(4) .btn")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("João");
    driver.findElement(By.id("address")).sendKeys("Farias");
    driver.findElement(By.id("city")).sendKeys("Aveiro");
    driver.findElement(By.id("state")).sendKeys("Aveiro");
    driver.findElement(By.id("zipCode")).sendKeys("3800");
    driver.findElement(By.id("creditCardNumber")).sendKeys("123456789");
    driver.findElement(By.id("creditCardMonth")).sendKeys("10");
    driver.findElement(By.id("creditCardYear")).sendKeys("2031");
    driver.findElement(By.id("nameOnCard")).sendKeys("João Farias");
    driver.findElement(By.id("rememberMe")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    assertEquals(driver.getTitle(), "BlazeDemo Confirmation");
    driver.findElement(By.cssSelector(".hero-unit")).click();
    driver.findElement(By.cssSelector("h1")).click();
    driver.findElement(By.cssSelector("h1")).click();
  }
}
