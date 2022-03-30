package com.tqs.carService.services;

import java.util.Arrays;
import java.util.List;

import com.tqs.carService.model.Car;
import com.tqs.carService.repository.CarRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class CarServicesMockTest {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp(){
        Car c1 = new Car("Peugeot", "206");
        c1.setCarId(649L);
        Car c2 = new Car("Peugeot", "207");
        Car c3 = new Car("Peugeot", "208");

        List<Car> carList = Arrays.asList(c1,c2,c3);

        Mockito.when(carRepository.findAll()).thenReturn(carList);
        Mockito.when(carRepository.findByCarId(c1.getCarId())).thenReturn(c1);
        Mockito.when(carRepository.findByCarId(c2.getCarId())).thenReturn(c2);
        Mockito.when(carRepository.findByCarId(c3.getCarId())).thenReturn(c3);

    }

    @Test
    public void validIdCarTest(){
        Car resultCar = carManagerService.getCarDetails(649L);


        Car secondCar = new Car(resultCar.getMaker(), resultCar.getModel());

        assertThat(secondCar.getModel()).isEqualTo("206");
        findByCarIdCalledOnce();
    }

    @Test 
    public void invalidIdCarTest(){
        Car resultCar = carManagerService.getCarDetails(504L);
        assertThat(resultCar).isNull();
        findByCarIdCalledOnce();
    }



    @Test
    public void allCarsTest(){
        Car c1 = new Car("Peugeot", "206");
        Car c2 = new Car("Peugeot", "207");
        Car c3 = new Car("Peugeot", "208");

        List<Car> aCars = carManagerService.getAllCars();

        Mockito.verify(carRepository, times(1)).findAll();

        assertThat(aCars).hasSize(3).extracting(Car::getModel).contains(c1.getModel(), c2.getModel(), c3.getModel());

        assertThat(aCars).hasSize(3).extracting(Car::getMaker).contains(c1.getMaker(), c2.getMaker(), c3.getMaker());

        
    }

    private void findByCarIdCalledOnce(){
        Mockito.verify(carRepository, times(1)).findByCarId(Mockito.anyLong());
    }

    
}
