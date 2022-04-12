package com.interviewtask.demo;

import com.interviewtask.demo.command.Converter;
import com.interviewtask.demo.command.CreateShipCommand;
import com.interviewtask.demo.entity.LocationData;
import com.interviewtask.demo.entity.Ship;
import com.interviewtask.demo.query.AreaQuery;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

class ShipTrackerApplicationTests {

    @Test
    public void testMostRecentLocationData() {
        Ship ship = new Ship("SERENITY", new ArrayList<>());

        LocationData locationData = new LocationData(
                10,
                15,
                LocalDate.of(2021, 4, 13),
                LocalTime.of(15, 45),
                10
        );

        LocationData locationData0 = new LocationData(
                10,
                15,
                LocalDate.of(2022, 4, 13),
                LocalTime.of(15, 45),
                10
        );

        LocationData locationData1 = new LocationData(
                10,
                15,
                LocalDate.of(2022, 4, 13),
                LocalTime.of(15, 44),
                10
        );

        ship.addLocationData(locationData);
        ship.addLocationData(locationData0);
        ship.addLocationData(locationData1);

        assert ship.getMostRecentLocationData().equals(locationData0);
    }

    @Test
    public void testCommandConvert() {
        CreateShipCommand command = new CreateShipCommand(
                "TITANIC",
                -12.11,
                22.22,
                19120410,
                1012,
                10
        );

        Ship ship = Converter.convertCreateCommandCommandToShip(command);
        LocalDateTime dateTime = ship.getMostRecentLocationData().getDateTime();

        assert dateTime.getYear() == 1912;
        assert dateTime.getMonthValue() == 4;
        assert dateTime.getDayOfMonth() == 10;
        assert dateTime.getHour() == 10;
        assert dateTime.getMinute() == 12;
    }

    @Test
    public void testCommandConvertThreeDigit() {
        CreateShipCommand command = new CreateShipCommand(
                "MAYFLOWER",
                -12.11,
                22.22,
                16200815,
                315,
                6
        );

        Ship ship = Converter.convertCreateCommandCommandToShip(command);
        LocalDateTime dateTime = ship.getMostRecentLocationData().getDateTime();

        assert dateTime.getYear() == 1620;
        assert dateTime.getMonthValue() == 8;
        assert dateTime.getDayOfMonth() == 15;
        assert dateTime.getHour() == 3;
        assert dateTime.getMinute() == 15;
    }

    @Test
    public void testCommandConvertZero() {
        CreateShipCommand command = new CreateShipCommand(
                "MILLENIUM FALCON",
                20.11,
                30.44,
                99991215,
                0,
                6_000_000
        );

        Ship ship = Converter.convertCreateCommandCommandToShip(command);
        LocationData mostRecentLocationData = ship.getMostRecentLocationData();
        LocalDateTime dateTime = mostRecentLocationData.getDateTime();

        assert dateTime.getYear() == 9999;
        assert dateTime.getMonthValue() == 12;
        assert dateTime.getDayOfMonth() == 15;
        assert dateTime.getHour() == 0;
        assert dateTime.getMinute() == 0;

        assert mostRecentLocationData.isInArea(new AreaQuery(20.10, 20.12,
                30.43, 30.45));
    }
}
