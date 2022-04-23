package tqs.HW1.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.lenient;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;

import org.assertj.core.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;


import tqs.HW1.cache.Cache;
import tqs.HW1.model.CountryData;
import tqs.HW1.model.Regions;
import tqs.HW1.request.Request;

@ExtendWith(MockitoExtension.class)
public class CovidServicesTest {

    @Mock
    private Cache c;

    @Mock
    private Request r;

    @InjectMocks
    private CovidServices covidServices;

    

    @Test
    public void testGetAllRegions() throws IOException, InterruptedException{
        List<Regions> respoList = new ArrayList<>();


        Regions r1 = new Regions("PRT", "Portugal");
        Regions r2 = new Regions("USA", "United States");
        Regions r3 = new Regions("FRN", "French");
        Regions r4 = new Regions("ARM", "Armenia");
        respoList.add(r1);
        respoList.add(r2);
        respoList.add(r3);
        respoList.add(r4);
        
        lenient().when(r.request(Mockito.anyString())).thenReturn("{data:["+respoList.get(0).toString()+", "+respoList.get(1).toString()+", "
                                            + respoList.get(2).toString()+", " + respoList.get(3)+"]}");


        List<Regions> apiReturn = covidServices.getAllRegions();


        assertEquals(respoList.size(), apiReturn.size());
    }

    @Test
    public void testGetLastFiveMonthsData() throws IOException, InterruptedException{
        List<CountryData> repoList = new ArrayList<>();
        repoList.add(new CountryData("2021-11-19", 3745569, 22088, 1237, 2783, 123, 1234, "2021-11-17 04:20:45", 17236, 12873, 0.079));
        repoList.add(new CountryData("2021-12-19", 3678001, 19476, 1987, 6475, 97, 5643, "2021-12-20 04:20:45", 46354, 13465, 0.080));
        repoList.add(new CountryData("2022-01-19", 5938475, 47387, 5635, 2837, 100, 9838, "2022-01-20 04:20:45", 11726, 11987, 0.078));
        repoList.add(new CountryData("2022-02-19", 1903984, 75983, 8374, 1928, 80, 8475, "2022-02-20 04:20:45", 10264, 10456, 0.065));
        repoList.add(new CountryData("2022-03-19", 7635241, 74635, 8576, 7564, 67, 1928, "2022-03-20 04:20:45", 9876, 10789, 0.070));
    
        lenient().when(r.request(Mockito.anyString())).thenReturn("{data: ["+repoList.get(0).toString()+", "+repoList.get(1).toString()+", "+
        repoList.get(2).toString()+", "+repoList.get(3).toString()+", "+repoList.get(4).toString()+"]}");
        
        List<CountryData> lastFiveMonths = covidServices.getLastFiveMonthsData("PRT");
        assertEquals(repoList.size(), lastFiveMonths.size());

        assertThat(lastFiveMonths).hasSize(5).extracting(CountryData::getDate).contains(repoList.get(4).getDate());
    }

    @Test
    public void testGetInformationByIso() throws IOException, InterruptedException{
        CountryData ptData= new CountryData("2021-11-19", 3745569, 22088, 1237, 2783, 123, 1234, "2021-11-17 04:20:45", 17236, 12873, 0.079);
        lenient().when(r.request(Mockito.anyString())).thenReturn("{data: ["+ptData.toString()+"]}");
        
        CountryData info = covidServices.getInformationByIso("PRT");
        assertEquals(ptData.toString(), info.toString());
    }

    @Test
    public void testGetWorldInformation() throws IOException, InterruptedException{
        CountryData worldInfo = new CountryData("2022-03-22", 76352401, 746305, 85076, 75604, 607, 19208, "2022-03-20 04:20:45", 9876, 10789, 0.070);
        lenient().when(r.request(Mockito.anyString())).thenReturn("{data: "+worldInfo.toString()+"}");
        CountryData infoFromApi = covidServices.getWorldInformation();
        assertEquals(worldInfo.toString(), infoFromApi.toString());
    }

    @Test
    public void testGetPercentageInfo() throws IOException, InterruptedException, JSONException {
        List<Integer> p1 = new ArrayList<>();
        p1.add(17);
        p1.add(-30);
        p1.add(1);


        String s1 = "{'data': {'confirmed_diff':"+p1.get(0)+", 'deaths_diff':"+p1.get(1)+", 'fatality_rate' : "+p1.get(2)+"}}";
        lenient().when(r.request(Mockito.anyString())).thenReturn(s1);


        List<Integer> retList = new ArrayList<>();
        retList.add((p1.get(0)-p1.get(0))/p1.get(0) * 100); // vai dar sempre 0
        retList.add((p1.get(1)-p1.get(1))/p1.get(1) * 100); // vai dar sempre 0
        retList.add((p1.get(2)-p1.get(2))/p1.get(2) * 100); // vai dar sempre 0


        assertEquals(covidServices.getPercentageInfo(), retList);
    }

    @Test
    public void testGetCacheInfo() throws IOException, InterruptedException{
    

        String cache_stats = "{hits=0, misses=0, requests=0}";
    
        lenient().when( c.toString() ).thenReturn( cache_stats );

        String info = covidServices.getCacheInfo().toString();

        assertEquals(cache_stats, info);
        assertEquals(c.getRequestsHitsMisses(), 0);

    }
}
