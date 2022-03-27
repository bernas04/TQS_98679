package com.tqs.carService.controller;

import java.util.List;

import com.tqs.carService.model.Car;
import com.tqs.carService.services.CarManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CarController {
    
    @Autowired
    private CarManagerService carManagerService;

    @PostMapping(path="/addCar")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        return new ResponseEntity<Car>(carManagerService.save(car), HttpStatus.CREATED);
    }

    @GetMapping(path = "/allCars")
    public List<Car> getAllCars(){
        return carManagerService.getAllCars();
    }

    @GetMapping(path = "cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long id) {
        Car resultCar = carManagerService.getCarDetails(id);
        return ResponseEntity.ok().body(resultCar);
    }
}
