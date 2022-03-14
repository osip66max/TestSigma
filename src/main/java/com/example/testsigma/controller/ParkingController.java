package com.example.testsigma.controller;

import com.example.testsigma.model.Parking;
import com.example.testsigma.repository.ParkingRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingController {
    private final ParkingRepository parkingRepository;

    @Autowired
    public ParkingController(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Operation(summary = "Add parking")
    @PostMapping("/parkings")
    public ResponseEntity<Parking> createParking(@RequestBody Parking parking) {
        Parking _parking = parkingRepository.save(new Parking(parking.getNumber(), parking.isOccupancy()));
        return new ResponseEntity<>(_parking, HttpStatus.CREATED);
    }
}
