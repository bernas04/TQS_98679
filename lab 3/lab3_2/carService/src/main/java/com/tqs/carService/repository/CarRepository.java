package com.tqs.carService.repository;

import com.tqs.carService.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    public Car findByCarId(Long id);
    public List<Car> findAll();
}