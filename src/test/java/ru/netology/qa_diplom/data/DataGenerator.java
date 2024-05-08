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
    private final int getValidCVC = faker.random().nextInt(899) + 100;

    public Year shiftYear() {
        LocalDate newDate = actualData.plusYears(numberOfYearsMonth);
        return new Year(formatterYears.format(newDate));
    }

    public String wrongYear() {
        return actualData.plusYears(10).format(formatterYears);
    }

    public Month shiftMonth() {
        LocalDate newDate = actualData.plusMonths(numberOfYearsMonth);
        return new Month(formatterMonth.format(newDate));
    }

    public Month wrongMonth() {
        return new Month(Integer.toString(faker.random().nextInt(13,20)));
    }

    public CVC shiftCvc() {
       return new CVC(Integer.toString(getValidCVC));
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
    public static class CVC {
        String cvc;
    }
    @Value
    public static class Owner {
        String owner;
    }

}

