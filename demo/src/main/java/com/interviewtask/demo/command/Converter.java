package com.interviewtask.demo.command;

import com.interviewtask.demo.entity.LocationData;
import com.interviewtask.demo.entity.Ship;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static Ship convertCreateCommandCommandToShip(CreateShipCommand command) {
        LocationData data = new LocationData(command.initialLatitude,
                command.initialLongitude,
                toLocalDate(command.date),
                toTime(command.time),
                command.speed);

        List<LocationData> loc = new ArrayList<>();
        loc.add(data);

        return new Ship(command.name, loc);
    }

    public static LocationData convertReportCommandToLocationData(ReportLocationCommand command) {
        return new LocationData(command.latitude,
                command.longitude,
                toLocalDate(command.date),
                toTime(command.time),
                command.speed);
    }

    private static LocalDate toLocalDate(int number) {
        int day = segment(number, 6, 7);
        int month = segment(number, 4, 5);
        int year = segment(number, 0, 3);
        return LocalDate.of(year, month, day);
    }

    private static LocalTime toTime(int number) {
        if (number == 0) {
            return LocalTime.of(0, 0);
        } else if (number < 1_000) {
            int hour = segment(number, 0, 0);
            int minute = segment(number, 1, 2);
            return LocalTime.of(hour, minute);
        } else {
            int hour = segment(number, 0, 1);
            int minute = segment(number, 2, 3);
            return LocalTime.of(hour, minute);
        }
    }

    private static int segment(int number, int digitCountFrom, int digitCountTo) {
        int[] digits = new int[(int) Math.log10(number) + 1];
        int index = digits.length - 1;
        while (number > 0) {
            digits[index--] = (int) number % 10;
            number /= 10;
        }

        int out = 0;
        int multiplier = 1;

        for (int i = digitCountTo; i >= digitCountFrom; i--) {
            out += digits[i] * multiplier;
            multiplier *= 10;
        }

        return out;
    }
}
