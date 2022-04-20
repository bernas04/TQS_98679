package tqs.HW1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegionsTest {

    private static List<Regions> regionsList = new ArrayList<>();

    @BeforeAll
    public static void setUp(){
        regionsList.add(new Regions("PRT", "Portugal"));
        regionsList.add(new Regions("USA", "United States of America"));
        regionsList.add(new Regions("FRN", "French"));
    }

    @DisplayName("Test data and respective format")
    @Test
    public void testFormat(){
        assertEquals(regionsList.size(), 3);
        assertEquals(regionsList.get(0).getCountry(), "Portugal");
        assertEquals(regionsList.get(1).getCountry(), "United States of America");
        assertEquals(regionsList.get(2).getCountry(), "French");

        assertEquals(regionsList.get(0).getIso(), "PRT");
        assertEquals(regionsList.get(1).getIso(), "USA");
        assertEquals(regionsList.get(2).getIso(), "FRN");
    }

    @DisplayName("Test toString")
    @Test
    public void testToString(){
        assertEquals(regionsList.get(0).toString(), "{ iso='PRT', country='Portugal'}");
    }

    @DisplayName("Test CompareTo")
    @Test
    public void testCompareTo(){
        assertNotEquals(regionsList.get(0).compareTo(regionsList.get(1)), 0);
        assertEquals(regionsList.get(0).compareTo(regionsList.get(0)), 0);
    }
    
}
