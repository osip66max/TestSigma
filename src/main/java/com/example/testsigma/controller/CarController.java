package com.example.testsigma.controller;

import com.example.testsigma.model.Car;
import com.example.testsigma.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    private final CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @PostMapping(value = "/cars")
    public ResponseEntity<?> create(@RequestBody Car car) {
        carRepository.save(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/cars")
    public ResponseEntity<List<Car>> read() {
        final List<Car> cars = (List<Car>) carRepository.findAll();

        return !cars.isEmpty()
                ? new ResponseEntity<>(cars, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cars/{id}")
    public ResponseEntity<Car> read(@PathVariable(name = "id") long id) {
        final Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        return car != null
                ? new ResponseEntity<>(car, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/cars/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Car car) {
        if (carRepository.existsById((long) id)) {
            carRepository.save(car);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/cars/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        Car car = carRepository.findById((long) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        carRepository.delete(car);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}