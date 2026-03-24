package M6_Queues;
import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/*
TEAM MEMBERS:

    Ellie Pike - pikeas01@pfw.edu
    Terrell Richey - rich03@pfw.edu
    Patrick Rall - rallpw0pfw.edu
    Ethan Quispe - quiseh01@pfw.edu
    Seth Pfister - pfissg01@pfw.edu

PART A:
    i. Copy your completed Queue and LinkedList classes into a project.
    ii. Copy the PriorityQueue class (provided above) into the project and complete these features:
        a. Constructor – Create and initialize an array of queues, one for each priority level (1 – priorityCount)
        b. enqueue – Add an element to the queue of designated priority
        c. dequeue – Remove and return the next element in the non-empty queue of highest priority.
        d. peek – Same as dequeue without removal.
        e. toString() – Return a String representation of the priority queue, indicating the contents at each priority level.
PART B:
    iii. Copy the private static class Patient and main method (provided below) and use them to test the functionality of your PriorityQueue. Ensure your output matches the commented expected output.
        a. Data should match exactly. Slight formatting differences in printing are fine as long as the output is readable.
*/

public class PriorityQueue<E> {
    private Queue<E>[] queues;
    private int size = 0;
    private int priorityCount;

    @SuppressWarnings("unchecked")
    public PriorityQueue(int priorityCount) {
        //TODO - Create and initialize the array of queues
        if(priorityCount <= 0) {
            throw new IllegalStateException("Priority Count has to be > 0");
        }

        this.priorityCount = priorityCount;
        queues = (Queue<E>[]) new Queue<?>[priorityCount];

        for(int i = 0; i < priorityCount; i++) {
            queues[i] = new Queue<>();
        }

    }

    /**
     * Adds the element to the back of the queue of given priority (tail of the list)
     * O(1) time complexity (O(p) where p is the number of priority levels)
     **/
    public void enqueue(E element, int priority) {
        queues[priority - 1].enqueue(element);
        size++;
    }

    /**
     * Removes and returns the element at the front of the queue with priority order (head of the list)
     * O(1) time complexity (O(p) where p is the number of priority levels)
     */
    public E dequeue() {
        E old = null;

        if (isEmpty()) {
            throw new NullPointerException("List is empty");
        }

        for (int i = priorityCount - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                old = queues[i].peek();
                queues[i].dequeue();
                size--;
                break;
            }
        }

        return old;
    }

    /**
     * Returns the front element of the queue in priority order (head of the list)
     * O(1) time complexity (O(p) where p is the number of priority levels)
     */
    public E peek() {
        for (int i = priorityCount - 1; i >= 0; i--)  {
            if (!queues[i].isEmpty()) {
                return queues[i].peek();
            }
        }

        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < queues.length; i++) {
            sb.append(String.format("Priority %d: ",i + 1));
            if (!queues[i].isEmpty()) {
                sb.append(queues[i].toString());
            }
            if(i + 1 != queues.length)
            sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Patient {
        private String name;
        private String condition;
        private int healthRiskLevel;

        public Patient(String name, String condition, int healthRiskLevel) {
            this.name = name;
            this.condition = condition;
            this.healthRiskLevel = healthRiskLevel;
        }

        public int getHealthRiskLevel() {
            return healthRiskLevel;
        }

        public String toString() {
            return name + " - " + condition + " (Risk Level: " + healthRiskLevel + ")";
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //M6.3 Priority Queue - Test Code ***************
        PriorityQueue<Patient> patientQueue = new PriorityQueue<>(3);

        //Patients arrive to check in
        ArrayList<Patient> patients = new ArrayList<>();
        //Three patients came in and were checked in
        Patient p1 = new Patient("Alice", "Puncture wound", 2);
        Patient p2 = new Patient("Bob", "Small Laceration", 1);
        Patient p3 = new Patient("Charlie", "Heart Attack", 3);
        patients.add(p1);
        patients.add(p2);
        patients.add(p3);
        //Adding patients to the queue
        patientQueue.enqueue(p1, p1.getHealthRiskLevel());
        patientQueue.enqueue(p2, p2.getHealthRiskLevel());
        patientQueue.enqueue(p3, p3.getHealthRiskLevel());

        //More patients arrived
        Patient p4 = new Patient("David", "Bone Fracture", 2);
        Patient p5 = new Patient("Eve", "Seizures", 3);
        patients.add(p4);
        patients.add(p5);
        //Adding more patients to the queue
        patientQueue.enqueue(p4, p4.getHealthRiskLevel());
        patientQueue.enqueue(p5, p5.getHealthRiskLevel());

        //Print priority queue
        System.out.println("Priority Queue: \n" + patientQueue);
    /*
        Expected output:
        Priority Queue:
        [Priority 1: Bob - Small Laceration (Risk Level: 1),
        Priority 2: Alice - Puncture wound (Risk Level: 2) -> David - Bone Fracture (Risk Level: 2),
        Priority 3: Charlie - Heart Attack (Risk Level: 3) -> Eve - Seizures (Risk Level: 3)]
    */

        //Patients are being treated based on their health risk level
        while (!patientQueue.isEmpty()) {
            Patient nextPatient = patientQueue.dequeue();
            System.out.println("Treating patient: " + nextPatient);
        }
    /*
        Expected output:
        Treating patient: Charlie - Heart Attack (Risk Level: 3)
        Treating patient: Eve - Seizures (Risk Level: 3)
        Treating patient: Alice - Puncture wound (Risk Level: 2)
        Treating patient: David - Bone Fracture (Risk Level: 2)
        Treating patient: Bob - Small Laceration (Risk Level: 1)
    */
    }
}

