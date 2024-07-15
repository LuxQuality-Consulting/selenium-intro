package com.luxqualityconsulting.seleniumintro;

import com.luxqualityconsulting.seleniumintro.pages.ResultPage;
import com.luxqualityconsulting.seleniumintro.pages.WebFormPage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
public class PageObjectTest
{
    @Test
    public void runSelenium() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        WebFormPage page = new WebFormPage(driver);
        assertEquals("Web form", page.getPageTitle());

        page.fillInText("Selenium");
        page.fillInPassword("secret");
        page.fillInTextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla elementum ipsum in ante feugiat, sit amet fermentum quam ultrices. In malesuada tellus a eros commodo finibus. Nulla ante nulla, accumsan vel maximus pellentesque, vehicula nec enim. Vestibulum egestas risus eget feugiat posuere. Aenean in imperdiet neque. Proin eleifend odio leo, sit amet consectetur nulla vehicula at. Nunc non congue diam.\n" +
                "\n" +
                "Sed eget sagittis mi. Aenean lobortis nisl quis eleifend ornare. Quisque tristique tortor eu dolor semper, id eleifend sem congue. Aliquam fringilla tellus vitae libero vehicula volutpat. Maecenas quis nunc nibh. Phasellus in congue nibh, at posuere ante. Etiam sed tellus purus. In gravida commodo aliquet. Proin ac libero consequat, scelerisque urna quis, blandit nisi. Etiam vel lacinia turpis. Etiam malesuada imperdiet laoreet. Aenean iaculis malesuada arcu, vitae luctus erat volutpat vel. Proin dapibus ipsum lacus, vitae aliquet nisi molestie ac.\n" +
                "\n" +
                "Duis aliquet, tortor vel fringilla molestie, odio mauris laoreet erat, eu laoreet quam lacus quis nisl. In commodo, lacus eget eleifend dapibus, elit ante faucibus lorem, vel fringilla elit purus quis diam. In faucibus eros id velit ornare, at interdum purus sodales. Duis vel risus molestie, ultricies nunc at, sagittis elit. Duis vel tempus arcu. Donec non sollicitudin urna, sed fringilla orci. Praesent quis lectus aliquet, fringilla massa eget, consequat metus. Vestibulum ornare, elit ac tristique consectetur, felis risus sodales nibh, in consequat lacus urna eget odio. Cras non velit tempus, varius mauris quis, consequat tortor. Maecenas nec scelerisque augue, ac commodo metus. In ornare lacinia condimentum.");
        List<String> possibleValues = page.getValuesFromList();
        page.selectInList(possibleValues.get(new Random().nextInt(possibleValues.size())));
        page.typeInListAndSelectFirstOption("New");
        page.checkFirstBox(!page.getFirstBox());
        page.checkSecondBox(!page.getSecondBox());
        assertEquals("Checked radio", page.getSelectedRadioOption());
        page.setRadioOption("Default radio");
        page.selectColor(Color.fromString("red"));
        page.selectDate(LocalDate.now());
        page.setValueInRange(9);

        Thread.sleep(1500);

        ResultPage result = page.submitForm();
        Thread.sleep(1500);
        assertEquals("Received!", result.getPageContent());

        driver.quit();
    }
}
