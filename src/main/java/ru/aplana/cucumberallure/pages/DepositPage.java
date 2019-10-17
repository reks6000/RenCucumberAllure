package ru.aplana.cucumberallure.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static java.lang.Thread.sleep;

public class DepositPage extends BasePage{
    String oldValue;

    //Валюты
    @FindBy(xpath = "//span[@class='calculator__currency-field-text']")
    List<WebElement> currencies;

    //Сумма вклада
    @FindBy(xpath = "//input[@name='amount']")
    WebElement amountField;

    //На срок
    @FindBy(xpath = "//div[@id='period-styler']")
    WebElement selectField;

    public static final By selectOptions = By.xpath(".//option");

    //Ежемесячное пополнение
    @FindBy(xpath = "//input[@name='replenish']")
    WebElement replenishField;

    //Галочки
    @FindBy(xpath = "//div[@class='calculator js-deposits-calculator']//span[@class='calculator__check-text']")
    List<WebElement> checkboxNames;

    public static final By checkbox = By.xpath(".//ancestor::label/span/div");

    //Служебное значение для сверки
    @FindBy(xpath = "//span[@class='js-calc-result js-calc-result-noanim']")
    WebElement verifyElem;

    //Ставка
    @FindBy(xpath = "//span[@class='js-calc-rate']")
    WebElement calcRate;

    //К снятию через n месяцев
    @FindBy(xpath = "//span[@class='js-calc-result']")
    WebElement calcResult;

    //Пополнение за 6 месяцев
    @FindBy(xpath = "//span[@class='js-calc-replenish']")
    WebElement calcReplenish;

    //Начислено
    @FindBy(xpath = "//span[@class='js-calc-earned']")
    WebElement calcEarned;

    public void rememberOldValue() {
        oldValue = verifyElem.getText();
    }

    public void checkOldValue() {
        fw.waitForChange(verifyElem, oldValue);
    }

    public void chooseCurrency(String currencyName) {
        String oldValue = verifyElem.getText();
        fw.wait(currencies.get(0));
        for (WebElement element : currencies) {
            if(element.getText().equals(currencyName)) {
                fw.waitAndClick(element);
                return;
            }
        }
        Assert.fail(String.format("Валюта %s не найдена", currencyName));
        fw.waitForChange(verifyElem, oldValue);
    }

    public void writeAmount(String amountValue) {
        String oldValue = verifyElem.getText();
        fw.waitAndSendKey(amountField, amountValue);
        fw.waitForChange(verifyElem, oldValue);
    }

    public void choosePeriod(String periodName) {
        String oldValue = verifyElem.getText();
        fw.waitAndClick(selectField);
        for (WebElement element : selectField.findElements(selectOptions)) {
            if(element.getText().equals(periodName)) {
                fw.waitAndClick(element);
                return;
            }
        }
        Assert.fail(String.format("Период %s не найден", periodName));
        fw.waitForChange(verifyElem, oldValue);
    }

    public void writeReplenish(String replenishValue) {
        String oldValue = verifyElem.getText();
        fw.waitAndSendKey(replenishField, replenishValue);
        fw.waitForChange(verifyElem, oldValue);
    }

    public void clickCheckbox(String checkboxName) {
        String oldValue = verifyElem.getText();
        fw.wait(currencies.get(0));
        for (WebElement element : checkboxNames) {
            if(element.getText().equals(checkboxName)) {
                fw.waitAndClick(element.findElement(checkbox));
                return;
            }
        }
        Assert.fail(String.format("Чекбокс %s не найден", checkboxName));
        fw.waitForChange(verifyElem, oldValue);
    }

    public void checkCalcRate(String expected) {
        fw.wait(calcRate);
        Assert.assertEquals(expected, calcRate.getText());
    }

    public void checkCalcResult(String expected) {
        fw.wait(calcResult);
        Assert.assertEquals(expected, calcResult.getText());
    }

    public void checkCalcReplenish(String expected) {
        fw.wait(calcReplenish);
        Assert.assertEquals(expected, calcReplenish.getText());
    }

    public void checkCalcEarned(String expected) {
        fw.wait(calcEarned);
        Assert.assertEquals(expected, calcEarned.getText());
    }
}
