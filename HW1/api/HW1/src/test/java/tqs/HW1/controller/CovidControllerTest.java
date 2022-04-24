package tqs.HW1.controller;

import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tqs.HW1.model.Regions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;



import tqs.HW1.services.CovidServices;

@WebMvcTest(CovidController.class)
public class CovidControllerTest {
    

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CovidServices covidServices;


    @Test
    public void testGetAllRegions() throws Exception{
        List<Regions> allRegions = new ArrayList<>();
        allRegions.add(new Regions("PRT", "Portugal"));
        allRegions.add(new Regions("FRC", "French"));
        allRegions.add(new Regions("CUW", "Curacao"));
        allRegions.add(new Regions("GUY", "Guyana"));
        allRegions.add(new Regions("KNA", "Saint Kitts and Nevis"));
        allRegions.add(new Regions("ESH", "Western Sahara"));
        allRegions.add(new Regions("YEM", "Yemen"));

        when(covidServices.getAllRegions()).thenReturn(allRegions);

        mvc.perform(get("/api/regions").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(6))
                );
    }

}
