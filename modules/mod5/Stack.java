package cs260.queueTips.mod5;
import cs260.queueTips.mod4.LinkedList;

import java.io.*;

public class Stack<E> implements Serializable, Cloneable {
    @Serial
    private static final long serialVersionUID = 1L;

    LinkedList<E> list;
    public Stack() {
        list = new LinkedList<>();
    }
    /**
     * Adds the element to the top of the stack (head of the list)
     **/
    public void push(E element) {
        LinkedList<E> newList = new LinkedList<>();

        if (isEmpty()){
            newList.add(0, element);
        }
        else {
            newList.add(0, element);

            for (int i = 0; i < list.size(); i++){
                newList.add((E) list.nodeAt(i));
            }

            list = newList;
        }
    }
    /**
     * Removes and returns the top element from the stack (head of the list)
     */
    public E pop() {
        if (isEmpty()) {
            throw new NullPointerException("List is empty");
        }

        E old = peek();
        list.removeAt(0);
        return old;
    }
    /**
     * Returns the top element from the stack (head of the list)
     * O(1)
     **/
    public E peek() {
        return list.iterator().next();
    }

    public int size() {
        return list.size();
    }
    public boolean isEmpty() {
        return list.size() == 0;
    }
    public String toString() {
        return list.toString();
    }

    public Stack<E> reverse() throws CloneNotSupportedException {
        Stack<E> reversedStack = new Stack<>();
        Stack<E> tempStack = this.clone();
        for (int i = 0; i < tempStack.size(); i++) {
            reversedStack.push(tempStack.pop());
        }

        return reversedStack;
    }


    @SuppressWarnings("unchecked")
    @Override
    public Stack<E> clone() throws CloneNotSupportedException {
        Stack<E> clone = (Stack<E>) super.clone();
        clone.list = this.list.clone();
        return clone;
    }



    public static void main(String[] args) throws CloneNotSupportedException {
        Stack<Stack<Character>> newStack = null;

        try (FileInputStream fileIn = new FileInputStream("modules/assets/stack.bin");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            newStack = (Stack<Stack<Character>>) in.readObject();

            System.out.println("newStack : "  + newStack.toString());

            String[] elements = {"D", "A", "T", "A",
                    "S", "T", "R", "U", "C", "T", "U", "R", "E", "S"};
            Stack<String> stack = new Stack<>();
            for (String s : elements)
                stack.push(s);
            System.out.println(stack);
            System.out.println("Peek: " + stack.peek());
            String original = "";
            while (!stack.isEmpty()) {
                original = stack.pop() + original;
            }
            System.out.println("Original: " + original);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException c) {
        System.out.println("Stack class not found");
        c.printStackTrace();
        return;
    }
    }
}