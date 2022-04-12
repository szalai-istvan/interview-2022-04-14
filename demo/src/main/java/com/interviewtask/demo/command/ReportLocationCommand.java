package com.interviewtask.demo.command;

public class ReportLocationCommand {
    public final long shipId;
    public final double latitude;
    public final double longitude;
    public final int date;
    public final int time;
    public final long speed;

    public ReportLocationCommand(long shipId,
                                 double latitude,
                                 double longitude,
                                 int date,
                                 int time,
                                 long speed) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.time = time;
        this.speed = speed;
        this.shipId = shipId;
    }
}
