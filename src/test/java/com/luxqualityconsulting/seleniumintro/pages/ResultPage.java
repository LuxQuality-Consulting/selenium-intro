package com.luxqualityconsulting.seleniumintro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

// page_url = https://www.selenium.dev/selenium/web/submitted-form.html
public class ResultPage {
    private WebDriver driver;

    public ResultPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
        driver.get("https://www.selenium.dev/selenium/web/submitted-form.html");
    }

    public String getPageContent() {
        WebElement message = driver.findElement(By.id("message"));
        return message.getText();
    }
}