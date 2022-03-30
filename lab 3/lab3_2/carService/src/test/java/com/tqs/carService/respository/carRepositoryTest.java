package com.tqs.carService.respository;

import com.tqs.carService.model.Car;
import com.tqs.carService.repository.CarRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class carRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void validIdCarTest(){
        Car c1 = new Car("Peugeot","206");

        entityManager.persistAndFlush(c1);
        Car returnCar = carRepository.findByCarId(c1.getCarId());
        assertThat(c1.getCarId()).isEqualTo(returnCar.getCarId());

    }

    @Test
    public void invalidIdCarTest(){
        assertThat(carRepository.findByCarId(1234L)).isNull();
    }

    @Test
    public void allCarsTest(){
        Car c1 = new Car("Peugeot","206");
        Car c2 = new Car("Peugeot","207");

        entityManager.persist(c1);
        entityManager.persist(c2);
        entityManager.flush();

        List<Car> aCars = carRepository.findAll();

        assertThat(aCars).hasSize(2).extracting(Car::getMaker).containsOnly(c1.getMaker(), c2.getMaker());

    }
    
}
