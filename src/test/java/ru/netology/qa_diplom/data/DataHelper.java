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
                dataGenerator.shiftCvc().getCvc());
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
