package M6_Queues;
import M4_LinkedLists.LinkedList;
import M5_Stacks.Stack;
import java.io.*;

/*
TEAM MEMBERS:
    
    Ellie Pike - pikeas01@pfw.edu
    Terrell Richey - rich03@pfw.edu
    Patrick Rall - rallpw0pfw.edu
    Ethan Quispe - quiseh01@pfw.edu
    Seth Pfister - pfissg01@pfw.edu


PART A:
    i. Copy your Stack class code into a new Queue class. Modify your list-based stack
        to behave like a queue with the following methods:
        a. enqueue – Adds the element to the back of the queue (tail of the list).
        b. dequeue – Removes and returns the element at the front of the queue
        (head of the list).
        c. peek - Returns the front element of the queue (head of the list)
        d. Keep the isEmpty, size, and toString methods unchanged.
PART B:
    ii. Use the main method below to test your Queue class on palindrome Strings.
    iii. Comment on the O(?) time complexity of the enqueue and dequeue methods.
*
* */
public class Queue<E> implements Serializable, Cloneable {
    @Serial
    private static final long serialVersionUID = 1L;

    LinkedList<E> list;
    public Queue() {
        list = new LinkedList<>();
    }

    // A -> B -> C -> D
    // h              t

    /**
     * Adds the element to the back of the queue (tail of the list)
     * O(1)
     **/
    public void enqueue(E element) {
        list.add(element);
    }

    /**
     * Removes and returns the first element from the queue (head of the list)
     *
     * O(1) - each operation is done in constant time. Meaning that the number of operations
     * is not affected by the input size.
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new NullPointerException("List is empty");
        }

        E old = peek();
        list.removeAt(0);
        return old;
    }

    /**
     * Returns the top element from the queue (head of the list)
     * O(1)
     **/
    public E peek() {
        if (isEmpty()) {
            throw new NullPointerException("Queue is empty");
        }
        return list.get(0);
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

    public Queue<E> reverse() throws CloneNotSupportedException {
        Queue<E> reversedStack = new Queue<>();

        for (E e: this.list) {
            reversedStack.enqueue(e);
        }

        return reversedStack;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Queue<E> clone() throws CloneNotSupportedException {
        Queue<E> clone = (Queue<E>) super.clone();
        clone.list = this.list.clone();
        return clone;
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //M6.1 Queue - Test Code ***************
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue); // [1, 2, 3, 4]
        System.out.println("Dequeue: " + queue.dequeue()); // 1
        System.out.println(queue); // [2, 3, 4]
        System.out.println("Poll: " + queue.peek()); // 2
        System.out.println(queue); // [2, 3, 4]
        //Palindrome with a Stack and Queue of characters
        String[] sentences = {"bob", "able was i saw elba",
                "madam im adam", "data structures"};

        for (String sentence: sentences) {
            Stack<Character> charStack = new Stack<>();
            Queue<Character> charQueue = new Queue<>();
            for (char c : sentence.toCharArray()) {
                if (Character.isLetter(c)) {
                    charStack.push(Character.toLowerCase(c));
                    charQueue.enqueue(Character.toLowerCase(c));
                }
            }
            boolean isPalindrome = true;
            while (!charStack.isEmpty() && isPalindrome) {
                if (!charStack.pop().equals(charQueue.dequeue())) {
                    isPalindrome = false;
                }
            }
            System.out.printf("'%s' is %sa palindrome\n",
                    sentence, isPalindrome ? "" : "not ");
        }
    }
}
