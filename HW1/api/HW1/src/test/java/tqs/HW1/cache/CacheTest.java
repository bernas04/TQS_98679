package tqs.HW1.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class CacheTest {
    
    private Cache c;

    @BeforeEach
    public void setUp(){
        this.c = new Cache(5000); // miliseconds 5 segundos
    }

    @AfterEach
    public void endUp(){
        this.c.clearAll();
    }

    @DisplayName("Add items to cache")
    @Test
    public void addItems(){
        List <Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        this.c.push("objectTest1", numbers);
        assertEquals(this.c.getSize(), 1);
        assertEquals(this.c.get("objectTest1"), numbers);
    }

    @DisplayName("Remove items from cache")
    @Test
    public void removeItems(){
        this.c.push("object1", "Value from object 1");
        this.c.push("object2", "Value from object 2");
        this.c.push("object3", "Value from object 3");
        this.c.push("object4", "Value from object 4");

        assertTrue(this.c.clear("object1"));
        assertFalse(this.c.clear("object1"));
        assertEquals(this.c.getSize(), 3);
        this.c.clearAll();
        assertFalse(this.c.clear("object2"));
    }

    @DisplayName("Cache time test")
    @Test
    public void cacheTest() throws InterruptedException{
        this.c.push("object1", "Value from object 1");
        this.c.push("object2", "Value from object 2");

        assertEquals(this.c.getSize(), 2);
        assertEquals(this.c.get("object1"), "Value from object 1");
        assertNotEquals(this.c.get("object2"), "Value from object 1");
        
        Thread.sleep(6000);

        assertEquals(this.c.getSize(), 0);

    }

    @DisplayName("Get items from cache")
    @Test
    public void getFromCache(){
        this.c.push("object1", "Value from object 1");
        this.c.push("object2", "Value from object 2");
        this.c.push("object3", "Value from object 3");
        this.c.push("object4", "Value from object 4");

        assertEquals(this.c.getSize(), 4);
        assertEquals(this.c.get("object1"), "Value from object 1");
        assertEquals(this.c.get("object2"), "Value from object 2");
        assertEquals(this.c.get("object3"), "Value from object 3");
        assertEquals(this.c.get("object4"), "Value from object 4");

        this.c.clearAll();
        assertEquals(this.c.getSize(), 0);
    }

}
