package com.luxqualityconsulting.seleniumintro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// page_url = https://www.selenium.dev/selenium/web/web-form.html
public class WebFormPage {
    private WebDriver driver;
    //input[@type="radio"]/parent::label

    public WebFormPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void fillInText(String text) {
        WebElement textBox = driver.findElement(By.name("my-text"));
        textBox.sendKeys(text);
    }

    public void fillInPassword(String password) {
        WebElement passwordBox = driver.findElement(By.name("my-password"));
        passwordBox.sendKeys(password);

    }

    public void fillInTextArea(String longText) {
        WebElement textArea = driver.findElement(By.name("my-textarea"));
        textArea.sendKeys(longText);
    }

    private Select getSelectList() {
        WebElement dropdownList = driver.findElement(By.name("my-select"));
        return new Select(dropdownList);
    }

    public List<String> getValuesFromList() {
        Select select = getSelectList();
        List<WebElement> options = select.getOptions();

        List<String> result = new ArrayList<>();

        for (WebElement option : options) {
            result.add(option.getText());
        }

        return result;
    }

    public void selectInList(String choice) {
        Select select = getSelectList();
        select.selectByVisibleText(choice);
    }

    public void typeInListAndSelectFirstOption(String input) {
        WebElement dataList = driver.findElement(By.name("my-datalist"));
        dataList.sendKeys(input);

        String optionValue = driver.findElement(By.xpath("//datalist/option[contains(@value,'" + input + "')]")).getAttribute("value");
        dataList.clear();
        dataList.sendKeys(optionValue);
    }

    private WebElement getFirstCheckbox() {
        return driver.findElement(By.id("my-check-1"));
    }

    public boolean getFirstBox() {
        return getFirstCheckbox().isSelected();
    }

    public void checkFirstBox(boolean selected) {
        boolean current = getFirstBox();

        if ((selected && !current) || (!selected && current)) {
            getFirstCheckbox().click();
        }
    }

    private WebElement getSecondCheckbox() {
        return driver.findElement(By.id("my-check-2"));
    }

    public boolean getSecondBox() {
        return getSecondCheckbox().isSelected();
    }

    public void checkSecondBox(boolean selected) {
        boolean current = getSecondBox();

        if ((selected && !current) || (!selected && current)) {
            getSecondCheckbox().click();
        }
    }

    private WebElement getRadio1() {
        return driver.findElement(By.id("my-radio-1"));
    }

    private WebElement getRadio2() {
        return driver.findElement(By.id("my-radio-2"));
    }

    public String getSelectedRadioOption() {
        WebElement radio1 = getRadio1();

        if (radio1.isSelected()) {
            WebElement label = radio1.findElement(By.xpath(".."));
            return label.getText().trim();
        }

        WebElement label = getRadio2().findElement(By.xpath(".."));
        return label.getText().trim();
    }

    public void setRadioOption(String option) {
        driver.findElement(By.xpath("//input[@type='radio']/parent::label[text()[contains(.,'" + option + "')]]")).click();
    }

    public void selectColor(Color color) {
        driver.findElement(By.name("my-colors")).sendKeys(color.toString());
    }

    public void selectDate(LocalDate date) {
        driver.findElement(By.name("my-date")).sendKeys(date.toString());
    }

    private WebElement getRange() {
        return driver.findElement(By.name("my-range"));
    }

    public void setValueInRange(int value) {
        int currentValue = Integer.parseInt(getRange().getAttribute("value"));
        int steps = value - currentValue;

        for (int i = 0; i < steps; i++) {
            getRange().sendKeys(Keys.ARROW_RIGHT);
        }
    }

    public ResultPage submitForm() {
        WebElement submitButton = driver.findElement(By.cssSelector("button"));
        submitButton.click();

        return new ResultPage(driver);
    }
}