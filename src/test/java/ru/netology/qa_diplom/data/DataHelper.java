package ru.netology.qa_diplom.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

public class DataHelper {
    static CardNumber cardNumber = new CardNumber();
    static DataGenerator dataGenerator = new DataGenerator();


    public static CardInformation getValidCard() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear().getYear(),
                dataGenerator.shiftMonth().getMonth(),
                dataGenerator.shiftOwner().getOwner(),
                dataGenerator.shiftCvv().getCvv());
    }

    public static CardInformation getCurrentMonthAndYear() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.currentYear().getYear(),
                dataGenerator.currentMonth().getMonth(),
                dataGenerator.shiftOwner().getOwner(),
                dataGenerator.shiftCvv().getCvv());
    }

    public static CardInformation getDeclinedCard() {
        return new CardInformation(
                cardNumber.getDeclinedCardNumber(),
                dataGenerator.shiftYear().getYear(),
                dataGenerator.shiftMonth().getMonth(),
                dataGenerator.shiftOwner().getOwner(),
                dataGenerator.shiftCvv().getCvv());
    }

    public static CardInformation getCardNumberEmpty() {
        return new CardInformation(
                " ",
                dataGenerator.shiftYear().getYear(),
                dataGenerator.shiftMonth().getMonth(),
                dataGenerator.shiftOwner().getOwner(),
                dataGenerator.shiftCvv().getCvv());
    }

    public static CardInformation getYearEmpty() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                " ",
                dataGenerator.shiftMonth().getMonth(),
                dataGenerator.shiftOwner().getOwner(),
                dataGenerator.shiftCvv().getCvv());
    }

    public static CardInformation getMonthEmpty() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear().getYear(),
                " ",
                dataGenerator.shiftOwner().getOwner(),
                dataGenerator.shiftCvv().getCvv());
    }

    public static CardInformation getHolderEmpty() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear().getYear(),
                dataGenerator.shiftMonth().getMonth(),
                " ",
                dataGenerator.shiftCvv().getCvv());
    }

    public static CardInformation getCVVEmpty() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear().getYear(),
                dataGenerator.shiftMonth().getMonth(),
                dataGenerator.shiftOwner().getOwner(),
                "");
    }

    public static CardInformation getExpiredYear() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.lastYear().getYear(),
                dataGenerator.shiftMonth().getMonth(),
                dataGenerator.shiftOwner().getOwner(),
                dataGenerator.shiftCvv().getCvv());
    }

    public static CardInformation getExpiredMonth() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.currentYear().getYear(),
                dataGenerator.lastMonth().getMonth(),
                dataGenerator.shiftOwner().getOwner(),
                dataGenerator.shiftCvv().getCvv());
    }

    public static CardInformation getWrongYear() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.wrongYear(),
                dataGenerator.shiftMonth().getMonth(),
                dataGenerator.shiftOwner().getOwner(),
                dataGenerator.shiftCvv().getCvv());
    }

    public static CardInformation getInvalidMonth() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear().getYear(),
                dataGenerator.wrongMonth().getMonth(),
                dataGenerator.shiftOwner().getOwner(),
                dataGenerator.shiftCvv().getCvv());
    }

    public static CardInformation getInvalidName() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear().getYear(),
                dataGenerator.shiftMonth().getMonth(),
                "Петро123(*?(?(?",
                dataGenerator.shiftCvv().getCvv());
    }

    public static CardInformation getInvalidCVV() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear().getYear(),
                dataGenerator.shiftMonth().getMonth(),
                dataGenerator.shiftOwner().getOwner(),
                dataGenerator.invalidCvv().getCvv());
    }

    public static CardInformation getZeroCard() {
        return new CardInformation(
                "0000000000000000",
                dataGenerator.shiftYear().getYear(),
                dataGenerator.shiftMonth().getMonth(),
                dataGenerator.shiftOwner().getOwner(),
                dataGenerator.shiftCvv().getCvv());
    }

    public static CardInformation getZeroMonth() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear().getYear(),
                "00",
                dataGenerator.shiftOwner().getOwner(),
                dataGenerator.shiftCvv().getCvv());
    }

    public static CardInformation getZeroCVV() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear().getYear(),
                dataGenerator.shiftMonth().getMonth(),
                dataGenerator.shiftOwner().getOwner(),
                "000");
    }

    public static CardInformation getInvalidFormatCVV() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear().getYear(),
                dataGenerator.shiftMonth().getMonth(),
                dataGenerator.shiftOwner().getOwner(),
                "77");
    }

    public static CardInformation getInvalidFormatMonth() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear().getYear(),
                "7",
                dataGenerator.shiftOwner().getOwner(),
                dataGenerator.shiftCvv().getCvv());
    }
    public static CardInformation getInvalidNameFormat() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear().getYear(),
                dataGenerator.shiftMonth().getMonth(),
                "G",
                dataGenerator.shiftCvv().getCvv());
    }


    @Value
    public static class CardInformation {
        String cardNumber;
        String year;
        String month;
        String owner;
        String CVV;
    }

    @Value
    public static class PaymentId {
        String id;
    }

    @Data
    @NoArgsConstructor
    public static class StatusPayment {
        private String status;
    }

    @Data
    @NoArgsConstructor
    public static class StatusCredit {
        private String status;
    }
}
