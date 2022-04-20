package tqs.HW1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CountryDataTest {
    private static CountryData c;

    @BeforeAll
    public static void setUp(){
        c= new CountryData("2022-04-19", 3745569, 22088, 1237, 2783, 123, 1234, "2022-04-20 04:20:45", 17236, 12873, 0.079);
    }

    @DisplayName("Test data formats")
    @Test
    public void dataFormats() throws NoSuchFieldException, SecurityException, ParseException{
        assertEquals(c.getClass(), CountryData.class);
        assertEquals(c.getDate().getClass(), String.class);
        assertEquals(c.getLast_update().getClass(), String.class);
        
        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(c.getDate());  
        assertEquals(date.getClass(), Date.class);

        assertEquals(c.getFatality_rate(), 0.079);
    }

    @DisplayName("Test data")
    @Test
    public void testData(){
        assertEquals(c.getDate(), "2022-04-19");
        assertEquals(c.getConfirmed(), 3745569);
        assertEquals(c.getDeaths(),22088);
        assertEquals(c.getRecovered(), 1237);
        assertEquals(c.getConfirmed_diff(), 2783);
        assertEquals(c.getDeaths_diff(), 123);
        assertEquals(c.getRecovered_diff(), 1234);
        assertEquals(c.getLast_update(), "2022-04-20 04:20:45");
        assertEquals(c.getActive(), 17236);
        assertEquals(c.getActive_diff(), 12873);

        // Change parameters and test
        c.setActive(999);
        c.setConfirmed(999);
        assertEquals(c.getActive(), 999);
        assertEquals(c.getConfirmed(), 999);
    }

}
