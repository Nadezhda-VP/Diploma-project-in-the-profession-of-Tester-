package ru.netology.qa_diplom.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

public class DataGenerator {
    static Faker faker = new Faker();
    private final LocalDate actualData = LocalDate.now();
    private final DateTimeFormatter formatterYears = DateTimeFormatter.ofPattern("yy");
    private final DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
    private final int numberOfYearsMonth = faker.random().nextInt(4) + 1;
    private final int getValidCVV = faker.random().nextInt(899) + 100;

    public Year shiftYear() {
        LocalDate newDate = actualData.plusYears(numberOfYearsMonth);
        return new Year(formatterYears.format(newDate));
    }

    public Year currentYear() {
        LocalDate newDate = actualData.plusYears(0);
        return new Year(formatterYears.format(newDate));
    }

    public String wrongYear() {
        return actualData.plusYears(15).format(formatterYears);
    }

    public Year lastYear() {
        LocalDate newDate = actualData.minusYears(1);
        return new Year(formatterYears.format(newDate));
    }

    public Month shiftMonth() {
        LocalDate newDate = actualData.plusMonths(numberOfYearsMonth);
        return new Month(formatterMonth.format(newDate));
    }

    public Month currentMonth() {
        LocalDate newDate = actualData.plusMonths(0);
        return new Month(formatterMonth.format(newDate));
    }

    public Month wrongMonth() {
        return new Month(Integer.toString(faker.random().nextInt(13, 20)));
    }

    public Month lastMonth() {
        LocalDate newDate = actualData.minusMonths(1);
        return new Month(formatterMonth.format(newDate));
    }

    public CVV shiftCvv() {
        return new CVV(Integer.toString(getValidCVV));
    }

    public CVV invalidCvv() {
        return new CVV(Integer.toString(faker.random().nextInt(1, 99)));
    }

    public Owner shiftOwner() {
        return new Owner(faker.name().fullName());
    }

    @Value
    public static class Year {
        String year;
    }

    @Value
    public static class Month {
        String month;
    }

    @Value
    public static class CVV {
        String cvv;
    }

    @Value
    public static class Owner {
        String owner;
    }

}

