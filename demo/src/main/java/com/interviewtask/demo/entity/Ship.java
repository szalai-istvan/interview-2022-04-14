package com.interviewtask.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name="SHIP")
public class Ship {

    @Id
    @GeneratedValue
    private long id;
    @Column(name="NAME", unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy="ship", fetch= FetchType.EAGER, cascade= CascadeType.ALL)
    private List<LocationData> locations;
    
    public Ship() {
        this.locations = new ArrayList<>();
    }

    public Ship(String name, List<LocationData> locations) {
        this.name = name;
        this.locations = locations;
    }

    public long getId() {
        return this.id;
    }

    public void addLocationData(LocationData locationData) {
        this.locations.add(locationData);
    }

    public List<LocationData> getLocationData() {
        return this.locations;
    }

    public LocationData getMostRecentLocationData() {
        return this.locations.stream()
                .max(Comparator.comparing(LocationData::getDateTime))
                .orElse(null);
    }
}
