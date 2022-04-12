package com.interviewtask.demo.controller;

import com.interviewtask.demo.command.CreateShipCommand;
import com.interviewtask.demo.command.ReportLocationCommand;
import com.interviewtask.demo.entity.LocationData;
import com.interviewtask.demo.entity.Ship;
import com.interviewtask.demo.query.AreaQuery;
import com.interviewtask.demo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shipdata")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @GetMapping("/list")
    public List<Ship> getShips() {
        return shipService.getShips();
    }

    @GetMapping("/list-ships-in-area")
    public List<Ship> getShipsInArea(AreaQuery query) {
        return shipService.getShipsInArea(query);
    }

    @PostMapping("/create-ship")
    public long saveAShip(CreateShipCommand command) {
        return shipService.saveShip(command);
    }

    @PostMapping("/report")
    public void reportLocationData(ReportLocationCommand command) {
        shipService.addLocationData(command);
    }

    @GetMapping("/locationdata/{shipid}")
    public List<LocationData> getLocationHistoryOfShip(@PathVariable("shipid") long shipId) {
        return shipService.getShip(shipId).getLocationData();
    }

    @GetMapping("/locationdata/{shipid}/{date}")
    public List<LocationData> getLocationOfShipOnDay(@PathVariable("shipid") long shipId, @PathVariable("date") String date) {
        return shipService.getLocationsOfShipOnDay(shipId, date);
    }


}
