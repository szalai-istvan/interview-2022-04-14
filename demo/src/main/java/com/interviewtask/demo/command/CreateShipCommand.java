package com.interviewtask.demo.command;

public class CreateShipCommand {
    public final String name;

    public final double initialLatitude;
    public final double initialLongitude;
    public final int date;
    public final int time;
    public final long speed;

    public CreateShipCommand(String name,
                             double initialLatitude,
                             double initialLongitude,
                             int date,
                             int time,
                             long speed) {
        this.initialLatitude = initialLatitude;
        this.initialLongitude = initialLongitude;
        this.date = date;
        this.time = time;
        this.speed = speed;
        this.name = name;
    }
}
