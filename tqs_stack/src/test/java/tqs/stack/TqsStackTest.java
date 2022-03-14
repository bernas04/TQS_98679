package tqs.stack;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Unit test for simple App.
 */
public class TqsStackTest 
{
    private TqsStack<String> collection;
    private TqsStack<String> limitedStack;


    @BeforeEach
    public void beforeEach(){
        collection = new TqsStack<>();
        limitedStack = new TqsStack<>(2);
        limitedStack.push("Hello");
        limitedStack.push("World");
    }

    @DisplayName("A stack is empty on construction")
    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(collection.isEmpty());

        collection.push("Hello World");
        assertFalse(collection.isEmpty());

        collection.pop();
        assertTrue(collection.isEmpty());
    }

    @DisplayName("A stack has size 0 on construction.")
    @Test
    public void testeSizeZero(){
        assertEquals(collection.size(), 0);
    }

    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    public void testSize(){
        assertTrue(collection.isEmpty());

        collection.push("a");
        collection.push("e");
        collection.push("i");
        collection.push("o");
        collection.push("u");

        assertFalse(collection.isEmpty());
        assertEquals(collection.size(), 5);
    }

    @DisplayName("If one pushes x then pops, the value popped is x.")
    @Test
    public void testPop(){
        collection.push("First element");
        assertEquals(collection.pop(), "First element");
        assertTrue(collection.isEmpty());
    }

    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    @Test
    public void testPeek(){
        collection.push("Hello");
        assertEquals(collection.peek(), "Hello");
        assertEquals(collection.size(), 1);
    }

    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    @Test
    public void testEmptyAfterPop(){
        for (int i =0; i<5; i++)
            collection.push("e");
        
        assertEquals(collection.size(), 5);

        for (int i =0; i<5; i++)
            collection.pop();

        assertTrue(collection.isEmpty());
        assertEquals(collection.size(), 0);
    }

    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    @Test
    public void testEmptyStack(){
        assertTrue(collection.isEmpty());
        
        NoSuchElementException thrown = Assertions.assertThrows(NoSuchElementException.class, () -> {
            collection.pop();
        });
    }

    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    public void peekEmptyStack(){
        assertTrue(collection.isEmpty());
        NoSuchElementException thrown = Assertions.assertThrows(NoSuchElementException.class, () -> {
            collection.peek();
        });
    }

    @DisplayName("For bounded stacks only: pushing onto a full stack does throw an IllegalStateException")
    @Test
    public void pushFullStack(){
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> {
            limitedStack.push("One");
        });
    }


}
