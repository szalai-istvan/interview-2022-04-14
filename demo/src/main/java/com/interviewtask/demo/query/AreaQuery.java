package com.interviewtask.demo.query;

public class AreaQuery {
    public final double minLatitude;
    public final double maxLatitude;
    public final double minLongitude;
    public final double maxLongitude;

    public AreaQuery(double minLatitude,
                     double maxLatitude,
                     double minLongitude,
                     double maxLongitude) {
        this.minLatitude = minLatitude;
        this.minLongitude = minLongitude;
        this.maxLatitude = maxLatitude;
        this.maxLongitude = maxLongitude;
    }
}
