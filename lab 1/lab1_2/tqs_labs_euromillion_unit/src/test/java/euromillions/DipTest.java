/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package euromillions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import euromillions.Dip;
import javax.lang.model.type.ArrayType;

import org.junit.jupiter.api.*;

import sets.SetOfNaturals;

/**
 * @author ico0
 */
public class DipTest {

    private Dip instance;


    public DipTest() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2});
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }


    //@Disabled  --> to skip one test
    @Test
    public void testConstructorFromBadArrays() {
        // todo: instantiate a dip passing valid or invalid arrays
        int [] numbers = {2,3,4,5};
        int [] stars = {2,3};

        assertThrows(IllegalArgumentException.class, () -> {
            new Dip(numbers, stars);
        });
    }

    @Test
    public void testFormat() {
        // note: correct the implementation of the format(), not the test...
        String result = instance.format();
        assertEquals("N[ 10 20 30 40 50] S[  1  2]", result, "format as string: formatted string not as expected. ");
    }

    @Test
    public void testRange(){
        Iterator starsIterator = instance.getStarsColl().iterator();
        while (starsIterator.hasNext()){
            assertTrue((int) starsIterator.next()<=Dip.MAX_STAR); // using consts instead of magic numbers
            assertTrue((int) starsIterator.next()>=Dip.MIN_RANGE);
        }
    }

}
