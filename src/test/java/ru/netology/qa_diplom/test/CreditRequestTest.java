package ru.netology.qa_diplom.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.qa_diplom.data.DataHelper;
import ru.netology.qa_diplom.data.SQLHelper;
import ru.netology.qa_diplom.page.StartPage;

import static com.codeborne.selenide.Selenide.open;

public class CreditRequestTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @DisplayName("Кредит – Успешная покупка.")
    @Test
    public void shouldConfirmCreditApprovedCard() {
        var startPage = new StartPage();
        var buyCredit = startPage.openBuyCredit();
        var approvedCardInformation = DataHelper.getValidCard();
        buyCredit.enterCreditCardData(approvedCardInformation);
        buyCredit.verifySuccessNotificationCreditCard();

        var statusPayment = SQLHelper.getStatusCredit();
        Assertions.assertEquals("APPROVED", statusPayment.getStatus());
    }

    @DisplayName("Кредит - Успешная покупка с текущей датой..")
    @Test
    public void shouldConfirmCreditWithCurrentMonthAndYear() {
        var startPage = new StartPage();
        var buyCredit = startPage.openBuyCredit();
        var validCardInformation = DataHelper.getCurrentMonthAndYear();
        buyCredit.enterCreditCardData(validCardInformation);
        buyCredit.verifySuccessNotificationCreditCard();

        var statusPayment = SQLHelper.getStatusCredit();
        Assertions.assertEquals("APPROVED", statusPayment.getStatus());
    }

    @DisplayName("Кредит – отклоненная карта")
    @Test
    public void shouldNotCreditDeclinedCard() {
        var startPage = new StartPage();
        var buyCredit = startPage.openBuyCredit();
        var declinedCard = DataHelper.getDeclinedCard();
        buyCredit.enterCreditCardData(declinedCard);
        buyCredit.verifyErrorWarningCreditCard();

        var statusPayment = SQLHelper.getStatusCredit();
        Assertions.assertEquals("DECLINED", statusPayment.getStatus());
    }

    @DisplayName("Кредит - поле номер карты пустое")
    @Test
    public void shouldNotCreditEmptyCard() {
        var startPage = new StartPage();
        var buyCredit = startPage.openBuyCredit();
        var fieldCardEmpty = DataHelper.getCardNumberEmpty();
        buyCredit.enterCreditCardData(fieldCardEmpty);
        buyCredit.verifyIncorrectFormatCreditCard();
    }

    @DisplayName("Кредит – поле месяца пусто")
    @Test
    public void shouldNotCreditEmptyMonth() {
        var startPage = new StartPage();
        var buyCredit = startPage.openBuyCredit();
        var fieldMonthEmpty = DataHelper.getMonthEmpty();
        buyCredit.enterCreditCardData(fieldMonthEmpty);
        buyCredit.verifyIncorrectFormatCreditCard();
    }

    @DisplayName("Кредит — поле года пусто.")
    @Test
    public void shouldNotCreditEmptyYear() {
        var startPage = new StartPage();
        var buyCredit = startPage.openBuyCredit();
        var fieldYearEmpty = DataHelper.getYearEmpty();
        buyCredit.enterCreditCardData(fieldYearEmpty);
        buyCredit.verifyIncorrectFormatCreditCard();
    }

    @DisplayName("Кредит – держатель поля пуст")
    @Test
    public void shouldNotCreditEmptyHolder() {
        var startPage = new StartPage();
        var buyCredit = startPage.openBuyCredit();
        var fieldHolderEmpty = DataHelper.getHolderEmpty();
        buyCredit.enterCreditCardData(fieldHolderEmpty);
        buyCredit.verifyRequiredFieldCreditCard();
    }

    @DisplayName("Кредит - поле CVV пусто")
    @Test
    public void shouldNotCreditEmptyCvv() {
        var startPage = new StartPage();
        var buyCredit = startPage.openBuyCredit();
        var fieldCvvEmpty = DataHelper.getCVVEmpty();
        buyCredit.enterCreditCardData(fieldCvvEmpty);
        buyCredit.verifyIncorrectFormatCreditCard();
    }

}
