package com.tqs.carService.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tqs.carService.model.Car;
import com.tqs.carService.services.CarManagerService;
import com.tqs.carService.JsonUtils;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.is;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;


@WebMvcTest
public class CarControllerMockTest {
    
    @Autowired
    private MockMvc mvClient;

    @MockBean
    private CarManagerService carManagerService;


    @Test
    public void addCarTest() throws IOException, Exception{
        
        Car ferrari = new Car("Ferrari", "Superfast");
        when(carManagerService.save(Mockito.any())).thenReturn(ferrari);

        mvClient.perform(post("/api/addCar").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(ferrari)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is("Superfast")))
                .andExpect(jsonPath("$.maker", is("Ferrari")));

        verify(carManagerService, times(1)).save(ferrari);
    }

    @Test
    public void validCarIdTest() throws Exception {
        Car mercedes = new Car("Mercedes", "AMG GT");
        mercedes.setCarId(649L);

        when(carManagerService.getCarDetails(mercedes.getCarId())).thenReturn(mercedes);
        
        mvClient.perform(get("/api/cars/649")).andExpect(status().isOk())
                                              .andExpect(jsonPath("maker").value("Mercedes"))
                                              .andExpect(jsonPath("model").value("AMG GT"));
        
        verify(carManagerService, times(1)).getCarDetails(mercedes.getCarId());
    }


    @Test
    public void getAllCarsTest() throws Exception{
        Car c1 = new Car("Peugeot","206");
        Car c2 = new Car("Peugeot","207");
        Car c3 = new Car("Peugeot","208");
        Car c4 = new Car("Peugeot","2008");

        List<Car> carsList = Arrays.asList(c1,c2,c3,c4);


        given(carManagerService.getAllCars()).willReturn(carsList);

        mvClient.perform(get("/api/allCars").contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk())
                .andExpect(jsonPath("$[0].maker", is(c1.getMaker())))
                .andExpect(jsonPath("$[0].model", is(c1.getModel())))
                .andExpect(jsonPath("$[1].maker", is(c2.getMaker())))
                .andExpect(jsonPath("$[1].model", is(c2.getModel())))
                .andExpect(jsonPath("$[2].maker", is(c3.getMaker())))
                .andExpect(jsonPath("$[2].model", is(c3.getModel())));
        
        verify(carManagerService, times(1)).getAllCars();
    }
}
