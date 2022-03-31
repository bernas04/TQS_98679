package com.tqs.carService.controller;

import java.util.List;

import com.tqs.carService.model.Car;
import com.tqs.carService.repository.CarRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "application-integrationtest.properties")
public class CarControllerDB {
    
    @LocalServerPort
    int serverRandomPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void resetDB(){
        carRepository.deleteAll();
    }

    @Test
    public void postCar(){
        Car ferrari = new Car("Ferrari", "Superfast");
        Car mercedes = new Car("Mercedes", "Superfast");

        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/addCar", ferrari, Car.class);
        entity = restTemplate.postForEntity("/api/addCar", mercedes, Car.class);

        List<Car> returnCar = carRepository.findAll();
        assertThat(returnCar).extracting(Car::getMaker).contains("Ferrari", "Mercedes");
    }

    @Test
    public void validCarIdTest(){
        Car ferrari = new Car("Ferrari", "Superfast");
        carRepository.saveAndFlush(ferrari);
        ResponseEntity<Car> entity = restTemplate.exchange("/api/cars/"+ ferrari.getCarId(), HttpMethod.GET, null, new ParameterizedTypeReference<Car>() {
                                                                                                                    });

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).extracting(Car::getMaker).isEqualTo("Ferrari");
    }

    @Test 
    public void invalidIdTest(){
        ResponseEntity<Car> entity = restTemplate.exchange("/api/cars/888", HttpMethod.GET, null, new ParameterizedTypeReference<Car>() {
        });
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK); // retorna lista vazia, o status é ok porque as exceptions não estão definidas, mas não contém nada
        assertThat(entity.getBody()).isNull();
    }

    @Test
    public void allCarsTest(){
        Car ferrari = new Car("Ferrari", "Superfast");
        Car mercedes = new Car("Mercedes", "Superfast");

        carRepository.save(ferrari);
        carRepository.save(mercedes);
        carRepository.flush();

        ResponseEntity<List<Car>> entity = restTemplate
                .exchange("/api/allCars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).extracting(Car::getMaker).containsOnly("Ferrari", "Mercedes");

    }
}
