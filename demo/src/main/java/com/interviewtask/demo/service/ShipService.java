package com.interviewtask.demo.service;

import com.interviewtask.demo.command.CreateShipCommand;
import com.interviewtask.demo.command.Converter;
import com.interviewtask.demo.command.ReportLocationCommand;
import com.interviewtask.demo.entity.LocationData;
import com.interviewtask.demo.entity.Ship;
import com.interviewtask.demo.query.AreaQuery;
import com.interviewtask.demo.repository.ShipRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipService {

    private static final Logger LOGGER = Logger.getLogger(ShipService.class);

    @Autowired
    private ShipRepository shipRepository;

    public List<Long> getShipIds() {
        return shipRepository.findAll().stream().map(ship -> ship.getId()).collect(Collectors.toList());
    }

    public List<Ship> getShips() {
        return shipRepository.findAll();
    }

    public Ship getShip(long id) {
        return shipRepository.findById(id).orElse(null);
    }

    public long saveShip(CreateShipCommand command) {
        return shipRepository.save(Converter.convertCreateCommandCommandToShip(command)).getId();
    }

    public void addLocationData(ReportLocationCommand command) {
        Ship ship = shipRepository.findById(command.shipId).orElse(null);
        if (ship == null) {
            LOGGER.warn("Cannot complete location reporting, because no ship is found with id " + command.shipId);
            return;
        }
        ship.addLocationData(Converter.convertReportCommandToLocationData(command));
        shipRepository.saveAndFlush(ship);
    }

    public List<LocationData> getLocationsOfShipOnDay(long shipId, String date) {
        LocalDate localDate = LocalDate.parse(date);
        return shipRepository.getById(shipId).getLocationData()
                .stream().filter(data -> data.getDate().equals(localDate))
                .collect(Collectors.toList());

    }

    public List<Ship> getShipsInArea(AreaQuery query) {
        return getShips().stream()
                .filter(ship -> ship.getMostRecentLocationData().isInArea(query))
                .collect(Collectors.toList());
    }
}
