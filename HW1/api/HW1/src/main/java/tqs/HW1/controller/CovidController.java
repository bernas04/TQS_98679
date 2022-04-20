package tqs.HW1.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        List <Regions> allRegions = service.getAllRegions();
        Collections.sort(allRegions);

        return allRegions;
    }

    @GetMapping("/region")
    public CountryData getCountryInfo(@RequestParam(value = "iso") String iso) throws IOException, InterruptedException{
        return service.getInformationByIso(iso);
    }

    @GetMapping("/historic")
    public List<CountryData> getLastSixMonthsInfo(@RequestParam(value = "iso") String iso) throws IOException, InterruptedException{
        List<CountryData> ret = service.getLastFiveMonthsData(iso);
        ret.add(service.getInformationByIso(iso));
        return ret;
    }

    @GetMapping("/world")
    public CountryData getWorldData() throws IOException, InterruptedException{
        return service.getWorldInformation();
    }

    @GetMapping("/percentages")
    public List<Integer> get7DaysWorldInformation() throws IOException, InterruptedException{
        return service.getPercentageInfo();
    }

    @GetMapping("/cache")
    public Map<String,Integer> getCacheInfo(){
        return service.getCacheInfo();
    }
    
}
