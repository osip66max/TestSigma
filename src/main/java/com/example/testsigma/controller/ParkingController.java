package com.example.testsigma.controller;

import com.example.testsigma.model.Parking;
import com.example.testsigma.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ParkingController {
    private final ParkingRepository parkingRepository;

    @Autowired
    public ParkingController(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @GetMapping("/parkings")
    public ResponseEntity<List<Parking>> getAllParkings() {
        List<Parking> parkings = new ArrayList<>(parkingRepository.findAll());
        if (parkings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(parkings, HttpStatus.OK);
    }

    @GetMapping("/parkings/{id}")
    public ResponseEntity<Parking> getParkingById(@PathVariable("id") long id) {
        Parking parking = parkingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found parking with id = " + id));
        return new ResponseEntity<>(parking, HttpStatus.OK);
    }

    @PostMapping("/parkings")
    public ResponseEntity<Parking> createParking(@RequestBody Parking parking) {
        Parking _parking = parkingRepository.save(new Parking(parking.getNumber(), parking.isOccupancy()));
        return new ResponseEntity<>(_parking, HttpStatus.CREATED);
    }

    @PutMapping("parkings/{id}")
    public ResponseEntity<Parking> updateParking(@PathVariable("id") long id, @RequestBody Parking parking) {
        Parking _parking = parkingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found parking with id = " + id));
        _parking.setNumber(parking.getNumber());
        _parking.setOccupancy(parking.isOccupancy());
        return new ResponseEntity<>(parkingRepository.save(_parking), HttpStatus.OK);
    }

    @DeleteMapping("/parkings")
    public ResponseEntity<HttpStatus> deleteAllParkings() {
        parkingRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/parkings/{id}")
    public ResponseEntity<HttpStatus> deleteParking(@PathVariable("id") long id) {
        parkingRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
