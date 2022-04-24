package tqs.HW1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tqs.HW1.model.CountryData;
import tqs.HW1.model.Regions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
            .andExpect(jsonPath("$.[0].iso", is(allRegions.get(0).getIso())))
            .andExpect(jsonPath("$.[0].name", is(allRegions.get(0).getName())))
            .andExpect(jsonPath("$.[6].iso", is(allRegions.get(6).getIso())))
            .andExpect(jsonPath("$.[6].name", is(allRegions.get(6).getName())))
            .andExpect(jsonPath("$.*", hasSize(7))
        );
        

        
        verify(covidServices, times(1)).getAllRegions();
    }

    @Test
    public void testGetCountryInfo() throws Exception{
        CountryData c= new CountryData("2022-04-19", 3745569, 22088, 1237, 2783, 123, 1234, "2022-04-20 04:20:45", 17236, 12873, 0.079);


        when(covidServices.getInformationByIso("PRT")).thenReturn(c);

        mvc.perform(get("/api/region?iso=PRT").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.date", is(c.getDate())))
            .andExpect(jsonPath("$.confirmed", is((int)c.getConfirmed())))
            .andExpect(jsonPath("$.deaths", is((int)c.getDeaths())))
            .andExpect(jsonPath("$.fatality_rate", is(c.getFatality_rate()))
        );
        
        verify(covidServices, times(1)).getInformationByIso("PRT");
        verify(covidServices, times(0)).getInformationByIso("PR");

    }

    @Test
    public void testGetLastSixMonthsInfo() throws Exception{
        List<CountryData> cdList = new ArrayList<>();
        cdList.add(new CountryData("2022-04-19", 3745569, 22088, 1237, 2783, 123, 1234, "2022-04-20 04:20:45", 17236, 12873, 0.079));
        cdList.add(new CountryData("2022-03-19", 2334, 22088, 123, 2783, 123, 1234, "2022-03-20 04:20:45", 17236, 12873, 0.079));
        cdList.add(new CountryData("2022-02-19", 234, 1234, 423, 2783, 123, 1234, "2022-02-20 04:20:45", 17236, 12873, 0.080));
        cdList.add(new CountryData("2022-01-19", 6543, 4321, 234, 2783, 123, 1234, "2022-01-20 04:20:45", 17236, 12873, 0.091));
        cdList.add(new CountryData("2021-12-19", 12321, 4321, 234, 2783, 123, 1234, "2021-12-20 04:20:45", 17236, 12873, 0.0911));
        cdList.add(new CountryData("2021-11-19", 1232321, 4321, 435, 2783, 123, 1234, "2021-11-20 04:20:45", 17236, 12873, 0.0729));


        when(covidServices.getLastFiveMonthsData("PRT")).thenReturn(cdList);
        mvc.perform(get("/api/historic?iso=PRT").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0].date", is(cdList.get(0).getDate())))
            .andExpect(jsonPath("$.[1].confirmed", is((int)cdList.get(1).getConfirmed())))
            .andExpect(jsonPath("$.[2].deaths", is((int)cdList.get(2).getDeaths())))
            .andExpect(jsonPath("$.[3].fatality_rate", is(cdList.get(3).getFatality_rate())))
            .andExpect(jsonPath("$.*", hasSize(cdList.size()))
        );

        
        verify(covidServices, times(1)).getInformationByIso("PRT");
        verify(covidServices, times(1)).getLastFiveMonthsData("PRT");
    }

    @Test
    public void testGetWorldData() throws Exception{
        CountryData cd = new CountryData("2021-12-19", 12321, 4321, 234, 2783, 123, 1234, "2021-12-20 04:20:45", 17236, 12873, 0.0911);
        when(covidServices.getWorldInformation()).thenReturn(cd);
    
        mvc.perform(get("/api/world").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.confirmed", is((int)cd.getConfirmed())))
            .andExpect(jsonPath("$.deaths", is((int)cd.getDeaths()))
        );
        verify(covidServices, times(1)).getWorldInformation();
    }

    @Test
    public void testGet7DaysWorldInformation() throws Exception{
        List<Integer> percentages = new ArrayList<>();
        percentages.add(17);
        percentages.add(-15);
        percentages.add(12);
        when(covidServices.getPercentageInfo()).thenReturn(percentages);

        mvc.perform(get("/api/percentages").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0]", is(percentages.get(0))))
            .andExpect(jsonPath("$.[1]", is(percentages.get(1))))
            .andExpect(jsonPath("$.[2]", is(percentages.get(2))))
            .andExpect(jsonPath("$.*", hasSize(percentages.size()))
        );
        verify(covidServices, times(1)).getPercentageInfo();
    }

    @Test
    public void testGetCacheInfo() throws Exception{
        Map<String, Integer> cacheInfo = new HashMap<>();
        cacheInfo.put("hits", 14);
        cacheInfo.put("misses", 12);
        cacheInfo.put("requests", 30);
        when(covidServices.getCacheInfo()).thenReturn(cacheInfo);

        mvc.perform(get("/api/cache").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hits", is(cacheInfo.get("hits"))))
            .andExpect(jsonPath("$.misses", is(cacheInfo.get("misses"))))
            .andExpect(jsonPath("$.requests", is(cacheInfo.get("requests"))))
            .andExpect(jsonPath("$.*", hasSize(cacheInfo.size()))
        );

        verify(covidServices, times(1)).getCacheInfo();
    }

}
