package M5_Stacks;

/*
    Ellie Pike - pikeas01@pfw.edu
    Terrell Richey - rich03@pfw.edu
    Patrick Rall - rallpw0pfw.edu
    Ethan Quispe - quiseh01@pfw.edu
    Seth Pfister - pfissg01@pfw.edu
*/

import java.lang.reflect.Array;
import java.util.EmptyStackException;
class ArrayStack<E> {
    private E[] data;
    private int size;
    public ArrayStack() {
        final int INITIAL_CAPACITY = 10;
        size = 0;
        data = (E[]) Array.newInstance(Object.class, INITIAL_CAPACITY);
    }
    public ArrayStack(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException
                    ("Initial Capacity " + initialCapacity + " is negative");
        size = 0;
        data = (E[]) Array.newInstance(Object.class, initialCapacity);
    }
    public void ensureCapacity(int minimumCapacity) {
        E[] biggerArray;
        if (data.length < minimumCapacity) {
            biggerArray = (E[]) Array.newInstance(Object.class, minimumCapacity);;
            System.arraycopy(data, 0, biggerArray, 0, size);
            data = biggerArray;
        }
    }
    public boolean isEmpty() {
        return (size == 0);
    }

    // Return the top item from the stack (end of the array)
    public E peek() {
        if (size == 0)
            throw new EmptyStackException();
        return data[size - 1];
    }
    // Remove the top item from the stack (end of the array)
    // The worst-case runtime for this method is O(1). This is because we never have to ensure capacity in this method.
    // In addition to that, the number of operations to remove an item from the stack is constant. Therefore, the worst-case
    // runtime is O(1)
    public E pop() throws EmptyStackException {
        if(size == 0) {
            throw new EmptyStackException();
        }
        E item = data[size - 1];
        size--;
        return item;
    }

    // Add an item to the top of the stack (end of the array)
    // O(n) - worst case is data is full and then we have to resize which involved arraycopy which is an O(n) operation
    public void push(E item) {
        if (size == data.length) { // Ensure twice as much space as we need.
            ensureCapacity((size + 1) * 2);
        }
        if (size == 0) {
            data[size] = item;
        } else {
            data[size - 1] = item;
        }
        size++;
    }
    public int size() {
        return size;
    }

    // Return a string representation of the stack
    // O(n) since we have to append for every n character to the string for every n iteration
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("/----\\\n");
        for (int i = size - 1; i >= 0; i--) {
            sb.append(data[i] + "\n");
        }
        sb.append("\\----/");
        return sb.toString();
    }
    public static void main(String[] args) {
        String[] elements = {"D", "A", "T", "A",
                "S", "T", "R", "U", "C", "T", "U", "R", "E", "S"};
        ArrayStack<String> stack = new ArrayStack<>(10);
        for (String s : elements)
            stack.push(s);
        System.out.println(stack);
        System.out.println("Peek: " + stack.peek());
        String original = "";
        while (!stack.isEmpty()) {
            original = stack.pop() + original;
        }
        System.out.println("Original: " + original);
    }
}