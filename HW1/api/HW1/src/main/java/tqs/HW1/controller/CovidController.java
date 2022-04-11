package tqs.HW1.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tqs.HW1.model.CountryData;
import tqs.HW1.model.Regions;
import tqs.HW1.services.CovidServices;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CovidController {
    

    @Autowired
    private CovidServices service;

    @GetMapping("/regions")
    public List<Regions> getAllRegions() throws IOException, InterruptedException{
        return service.getAllRegions();
    }

    @GetMapping("/region")
    public CountryData getCountryInfo(@RequestParam(value = "iso") String iso) throws IOException, InterruptedException{
        return service.getInformationByIso(iso);
    }

    @GetMapping("/world")
    public CountryData getWorldData() throws IOException, InterruptedException{
        return service.getWorldInformation();
    }
    
}
