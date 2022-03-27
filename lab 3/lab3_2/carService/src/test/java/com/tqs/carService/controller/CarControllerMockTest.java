package com.tqs.carService.controller;

import static org.mockito.Mockito.when;

import com.tqs.carService.model.Car;
import com.tqs.carService.services.CarManagerService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest
public class CarControllerMockTest {
    
    @Autowired
    private MockMvc mvClient;

    @MockBean
    private CarManagerService carManagerService;


    @Test
    public void addCarTest(){
        
        Car ferrari = new Car("Ferrari", "Superfast");
        when(carManagerService.save(Mockito.any())).thenReturn(ferrari);
    }
}
