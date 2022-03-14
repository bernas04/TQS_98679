package tqs.stack;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Hello world!
 *
 */
public class TqsStack <T> {
    public static void main(String[] args) {
        System.out.println("For unbounded stack!");
        TqsStack l = new TqsStack<>();
        System.out.println("Push an element!");
        l.push("First Element");
        System.out.println("Push another element!");
        l.push("Second Element");
        System.out.println("Size: " + l.size());
        System.out.println("Top element in stack:" + l.peek());
        l.pop();
        System.out.println("Is empty? "+l.isEmpty());
        l.pop();
        System.out.println("Is empty? "+l.isEmpty());
        //l.pop(); // This is going to thrown an error, please comment to continue
System.out.println();
        System.out.println("For bounded stack with size 2!");
        TqsStack limitedStack = new TqsStack<>(2);
        System.out.println("Push an element!");
        limitedStack.push("First Element");
        System.out.println("Push another element!");
        limitedStack.push("Second Element");
        System.out.println("Right now the stack is full");
        System.out.println("Size: " + limitedStack.size());
        System.out.println("Top element in stack:" + limitedStack.peek());
        System.out.println("Let's push another element (to thrown the error)");
        //limitedStack.push("Three"); // This is going to thrown an error, please comment to continue
        System.out.println("Pop: " + limitedStack.pop());
        System.out.println("Pop: " + limitedStack.pop());



    }
    
    private LinkedList <T> stack; 
    private int limit;

    public TqsStack(){
        this.stack= new LinkedList<>();
    }

    /* This is a bounded stack, used for the last test */
    public TqsStack(int limit){
        this.stack = new LinkedList<>();
        this.limit = limit;
    }

    public void push(T x){
        if (this.limit>0){
            if (this.stack.size() < this.limit){
                this.stack.push(x);
            }
            else{
                throw new IllegalStateException();
            }
        }
        else{
            this.stack.push(x);
        }
    }

    public T pop(){
        if (this.stack.size()==0){
            throw new NoSuchElementException();
        }
        return this.stack.pop();
    }

    public T peek(){
        if (this.stack.size()==0){
            throw new NoSuchElementException();
        }
        return this.stack.peek();
    }

    public int size(){
        return this.stack.size();
    }

    public boolean isEmpty(){
        return this.stack.isEmpty();
    }
}
