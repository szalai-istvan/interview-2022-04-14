package com.interviewtask.demo.entity;

import com.interviewtask.demo.query.AreaQuery;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="LOCATION_DATA")
public class LocationData {

    @Id
    @GeneratedValue
    private long id;

    @JoinColumn(name = "SHIP_ID")
    @ManyToOne(cascade=CascadeType.ALL)
    private Ship ship;

    @Column(name="LATITUDE")
    private double latitude;
    @Column(name="LONGITUDE")
    private double longitude;
    @Column(name="DATE")
    private LocalDate date;
    @Column(name="TIME")
    private LocalTime time;
    @Column(name="SPEED")
    private long speed;

    public LocationData() {
    }

    public LocationData(double latitude,
                        double longitude,
                        LocalDate date,
                        LocalTime time,
                        long speed) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.time = time;
        this.speed = speed;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.of(this.date, this.time);
    }

    public boolean isInArea(AreaQuery query) {
        return this.latitude >= query.minLatitude &&
                this.latitude <= query.maxLatitude &&
                this.longitude >= query.minLongitude &&
                this.longitude <= query.maxLongitude;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof LocationData)) {
            return false;
        }
        LocationData loc = (LocationData) o;

        return this.latitude == loc.latitude &&
                this.longitude == loc.longitude &&
                this.date.equals(loc.date) &&
                this.time.equals(loc.time) &&
                this.speed == loc.speed;
    }
}
