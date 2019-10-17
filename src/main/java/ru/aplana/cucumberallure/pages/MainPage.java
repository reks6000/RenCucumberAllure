package ru.aplana.cucumberallure.pages;

import cucumber.api.java.ru.Когда;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {


//    @FindBy(xpath = "//div[@class='service__title-text']")
    @FindBy(xpath = "//div[@class='service__title']")
    List<WebElement> menuItems;

    public void clickMenuItem(String menuName) {
        fw.wait(menuItems.get(0));
        for (WebElement element : menuItems) {
            if(element.getText().equals(menuName)) {
                fw.waitAndClick(element);
                return;
            }
        }
        Assert.fail(String.format("Пункт меню %s не найден", menuName));
    }
}
