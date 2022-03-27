package com.tqs.carService.services;

import java.util.List;

import com.tqs.carService.model.Car;
import com.tqs.carService.repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CarManagerService {
    
    @Autowired
    private CarRepository carRepository;

    public Car save(Car c){
        return carRepository.save(c);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }
    
    public Car getCarDetails(Long id){
        return carRepository.findByCarId(id);
    }
}
