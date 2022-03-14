package com.example.testsigma.controller;

import com.example.testsigma.model.TimePrice;
import com.example.testsigma.repository.CarRepository;
import com.example.testsigma.repository.ParkingRepository;
import com.example.testsigma.repository.TimePriceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TimePriceController {
    private final CarRepository carRepository;
    private final ParkingRepository parkingRepository;
    private final TimePriceRepository timePriceRepository;

    @Autowired
    public TimePriceController(CarRepository carRepository, ParkingRepository parkingRepository,
                               TimePriceRepository timePriceRepository) {
        this.carRepository = carRepository;
        this.parkingRepository = parkingRepository;
        this.timePriceRepository = timePriceRepository;
    }

    @GetMapping("/timeprices")
    public ResponseEntity<List<TimePrice>> getAllTimePrices() {
        List<TimePrice> timePrices = new ArrayList<>(timePriceRepository.findAll());
        if (timePrices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(timePrices, HttpStatus.OK);
    }

    @DeleteMapping("/timeprices/{id}")
    public ResponseEntity<HttpStatus> deleteTimePrice(@PathVariable("id") long id) {
        timePriceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/cars/{carId}/timeprices")
    public ResponseEntity<List<TimePrice>> deleteAllTimePricesOfCar(@PathVariable(value = "carId") Long carId) {
        if (!carRepository.existsById(carId)) {
            throw new IllegalArgumentException("Not found Car with id = " + carId);
        }
        timePriceRepository.deleteByCarId(carId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete all links")
    @DeleteMapping("/timeprices")
    public ResponseEntity<HttpStatus> deleteAllTimePrices(@Parameter(description = "Number of car to be delete links (optional)")
                                                              @RequestParam(required = false) String number) {
        if (number == null) {
            timePriceRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else if (!carRepository.existsByNumber(number)) {
            throw new IllegalArgumentException("Not found Car with number = " + number);
        } else {
            timePriceRepository.deleteByCarNumber(number);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
