package ru.aplana.cucumberallure.steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import ru.aplana.cucumberallure.pages.DepositPage;
import ru.aplana.cucumberallure.pages.MainPage;

public class PageSteps {
    MainPage main = new MainPage();
    DepositPage depositPage = new DepositPage();

    @Когда("пользователь выбирает пункт меню \"(.*)\"")
    public void clickMenuItem(String itemName) {
        main.clickMenuItem(itemName);
    }

    @Когда("пользователь выбирает валюту \"(.*)\"")
    public void chooseCurrency(String currencyName) {
        depositPage.chooseCurrency(currencyName);
    }

    @Когда("пользователь вводит значение \"(.*)\" в поле \"Сумма вклада\"")
    public void writeAmount(String amountValue) {
        depositPage.rememberOldValue();
        depositPage.writeAmount(amountValue);
        depositPage.checkOldValue();
    }

    @Когда("пользователь выбирает период \"(.*)\"")
    public void choosePeriod(String periodName) {
        depositPage.rememberOldValue();
        depositPage.choosePeriod(periodName);
        depositPage.checkOldValue();
    }

    @Когда("пользователь вводит значение \"(.*)\" в поле \"Ежемесячное пополнение\"")
    public void writeReplenish(String replenishValue) {
        depositPage.rememberOldValue();
        depositPage.writeReplenish(replenishValue);
        depositPage.checkOldValue();
    }

    @Когда("пользователь нажимает на чекбокс \"(.*)\"")
    public void clickCheckbox(String checkboxName) {
        depositPage.rememberOldValue();
        depositPage.clickCheckbox(checkboxName);
        depositPage.checkOldValue();
    }

//    @Тогда("проверка поля Ставка (ожидается значение \"(.*)\")")
    @Тогда("сравнение поля Ставка с \"(.*)\"")
    public void checkCalcRate(String expected) {
        depositPage.checkCalcRate(expected);
    }

    @Тогда("сравнение поля К снятию с \"(.*)\"")
    public void checkCalcResult(String expected) {
        depositPage.checkCalcResult(expected);
    }

    @Тогда("сравнение поля Пополнение с \"(.*)\"")
    public void checkCalcReplenish(String expected) {
        depositPage.checkCalcReplenish(expected);
    }

    @Тогда("сравнение поля Начислено с \"(.*)\"")
    public void checkCalcEarned(String expected) {
        depositPage.checkCalcEarned(expected);
    }
}


//
//        Сценарий 2(Использовать Allure + Cucumber)
//
//        1. https://rencredit.ru
//        2. Перейти в меню – Вклады
//        3. Выбрать – Рубли
//        4. Сумма вклада – 500 000
//        5. Срок – 6 месяцев
//        6. Ежемесячное пополнение – 70 000
//        7. Отметить – Ежемесячная капитализация
//        8. Отметить – частичное снятие
//        9. Проверить расчеты по вкладу:
//        a. Ставка
//        b. К снятию через 6 месяцев
//        c. Пополнение за 6 месяцев
//        d. Начислено