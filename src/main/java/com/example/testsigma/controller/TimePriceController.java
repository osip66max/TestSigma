package com.example.testsigma.controller;

import com.example.testsigma.model.Car;
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

    @Operation(summary = "Add link")
    @PostMapping("/timeprices")
    public ResponseEntity<TimePrice> createTimePrice(@RequestBody TimePrice timePrice) {
        TimePrice _timePrice = timePriceRepository.
                save(new TimePrice(timePrice.getTime(), timePrice.getPrice(), timePrice.getCar(), timePrice.getParking()));
        return new ResponseEntity<>(_timePrice, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all links")
    @GetMapping("/timeprices")
    public ResponseEntity<List<TimePrice>> getAllTimePrices() {
        List<TimePrice> timePrices = new ArrayList<>(timePriceRepository.findAll());
        if (timePrices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(timePrices, HttpStatus.OK);
    }

    @Operation(summary = "Update links by number of car")
    @PutMapping("timeprices/{number}")
    public ResponseEntity<List<TimePrice>> updateTimePrice(@Parameter(description = "Number of car to be update links")
                                                               @PathVariable("number") String number,
                                                           @RequestBody TimePrice timePrice) {
        List<TimePrice> _timePrice = new ArrayList<>();
        timePriceRepository.findAllByCarNumber(number).forEach(timePrice1 -> {
            timePrice1.setTime(timePrice.getTime());
            timePrice1.setPrice(timePrice.getPrice());
            _timePrice.add(timePrice1);
        });
        return new ResponseEntity<>(timePriceRepository.saveAll(_timePrice), HttpStatus.OK);
    }

    @Operation(summary = "Delete all links or by number of car")
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
