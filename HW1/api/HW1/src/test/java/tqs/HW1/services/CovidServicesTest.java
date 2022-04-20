package tqs.HW1.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.HW1.cache.Cache;

@ExtendWith(MockitoExtension.class)
public class CovidServicesTest {

    @Mock
    private Cache c;

    @InjectMocks
    private CovidServices covidServices;

    @Test
    public void testGetAllRegions(){
        
    }
    
}
