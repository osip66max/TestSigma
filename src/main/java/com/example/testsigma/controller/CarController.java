package com.example.testsigma.controller;

import com.example.testsigma.model.Car;
import com.example.testsigma.repository.CarRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    private final CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Operation(summary = "Add car")
    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car _car = carRepository.save(new Car(car.getNumber(), car.getName()));
        return new ResponseEntity<>(_car, HttpStatus.CREATED);
    }
}