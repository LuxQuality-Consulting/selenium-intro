package com.luxqualityconsulting.seleniumintro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTest
{
    @Test
    public void runSelenium() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        assertEquals("Web form", driver.getTitle());

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");

        Thread.sleep(1500);

        submitButton.click();

        Thread.sleep(1500);

        WebElement message = driver.findElement(By.id("message"));
        assertEquals("Received!", message.getText());

        driver.quit();
    }
}
