/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sets;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
public class SetOfNaturalsTest {
    private SetOfNaturals setA;
    private SetOfNaturals setB;
    private SetOfNaturals setC;
    private SetOfNaturals setD;

    @BeforeEach
    public void setUp() {
        setA = new SetOfNaturals();
        setB = SetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});

        setC = new SetOfNaturals();
        for (int i = 5; i < 50; i++) {
            setC.add(i * 10);
        }
        setD = SetOfNaturals.fromArray(new int[]{30, 40, 50, 60, 10, 20});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = setD = null;
    }

    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        setB.add(11);
        assertTrue(setB.contains(11), "add: added element not found in set.");
        assertEquals(7, setB.size(), "add: elements count not as expected.");
    }

    @Test
    public void testAddBadArray() {
        int[] elems = new int[]{10, 20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }


    @Test
    public void testIntersectForNoIntersection() {
        assertFalse(setA.intersects(setB), "no intersection but was reported as existing");

    }

    @Test
    public void testDuplicatedElemets(){
        int [] arr = {10,20,30,20,40};
        assertThrows(IllegalArgumentException.class, () -> setA.fromArray(arr));
    }

    @Test
    public void testContains(){
        assertTrue(setB.contains(40));
        assertTrue(setD.contains(40));
        for (int i=1; i<10; i+=2){
            assertFalse(setB.contains(i));
        }
    }

    @Test
    public void testSize(){
        assertEquals(setB.size(), 6);
        assertEquals(setC.size(), 45);
        assertEquals(setD.size(), 6);
    }

    @Test
    public void addIncorrectElement(){
        assertThrows(IllegalArgumentException.class, () -> setA.add(-1));
        assertThrows(IllegalArgumentException.class, () -> setB.add(30));

    }

}
