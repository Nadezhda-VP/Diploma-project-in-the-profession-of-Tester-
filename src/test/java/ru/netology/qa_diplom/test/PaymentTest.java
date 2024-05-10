package ru.netology.qa_diplom.test;


import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.qa_diplom.data.DataHelper;
import ru.netology.qa_diplom.data.SQLHelper;
import ru.netology.qa_diplom.page.StartPage;


import static com.codeborne.selenide.Selenide.open;

public class PaymentTest {

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

    void startPage() {

    }

    @DisplayName("Карта – Удачная покупка.")
    @Test
    public void shouldConfirmPaymentApprovedCard() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var approvedCardInformation = DataHelper.getValidCard();
        payCard.enterCardData(approvedCardInformation);
        payCard.verifySuccessNotificationCard();

        var statusPayment = SQLHelper.getStatusPayment();
        Assertions.assertEquals("APPROVED", statusPayment.getStatus());
    }

    @DisplayName("Карта - Успешная покупка с текущей датой..")
    @Test
    public void shouldConfirmPaymentCurrentMonthAndYear() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var validCardInformation = DataHelper.getCurrentMonthAndYear();
        payCard.enterCardData(validCardInformation);
        payCard.verifySuccessNotificationCard();

        var statusPayment = SQLHelper.getStatusPayment();
        Assertions.assertEquals("APPROVED", statusPayment.getStatus());
    }

    @DisplayName("Карта – отклоненная карта")
    @Test
    public void shouldNotPayDeclinedCard() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var declinedCard = DataHelper.getDeclinedCard();
        payCard.enterCardData(declinedCard);
        payCard.verifyErrorWarningCard();

        var statusPayment = SQLHelper.getStatusPayment();
        Assertions.assertEquals("DECLINED", statusPayment.getStatus());
    }

    @DisplayName("Карта - поле номер карты пустое")
    @Test
    public void shouldNotPayEmptyCard() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var fieldCardEmpty = DataHelper.getCardNumberEmpty();
        payCard.enterCardData(fieldCardEmpty);
        payCard.verifyIncorrectFormatCard();
    }

    @DisplayName("Карта - поле месяца пусто")
    @Test
    public void shouldNotPayEmptyMonth() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var fieldMonthEmpty = DataHelper.getMonthEmpty();
        payCard.enterCardData(fieldMonthEmpty);
        payCard.verifyIncorrectFormatCard();
    }

    @DisplayName("Карта - поле года пустое")
    @Test
    public void shouldNotPayEmptyYear() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var fieldYearEmpty = DataHelper.getYearEmpty();
        payCard.enterCardData(fieldYearEmpty);
        payCard.verifyIncorrectFormatCard();
    }

    @DisplayName("Карта — держатель карты пустое поле")
    @Test
    public void shouldNotPayEmptyHolder() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var fieldHolderEmpty = DataHelper.getHolderEmpty();
        payCard.enterCardData(fieldHolderEmpty);
        payCard.verifyRequiredFieldCard();
    }

    @DisplayName("Карта – поле CVV пустое.")
    @Test
    public void shouldNotPayEmptyCvv() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var fieldCvvEmpty = DataHelper.getCVVEmpty();
        payCard.enterCardData(fieldCvvEmpty);
        payCard.verifyIncorrectFormatCard();
    }

}
