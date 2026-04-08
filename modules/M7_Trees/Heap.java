package M7_Trees;
import M6_Queues.*;
import java.util.Arrays;

/*
Heap implementation using an array. The heap behaves as a max-heap / priority queue.
Only the max element is available for removal (at the root).
*/
public class  Heap<E extends Comparable<E>> {
    private static final int DEFAULT_CAPACITY = 100;
    private E[] heap;
    private int size;

    @SuppressWarnings("unchecked")
    public Heap() {
        heap = (E[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(E element) {
        ensureCapacity();
        heap[size] = element;
        reheapifyUp(size);
        size++;
    }

    public E remove() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty.");
        E max = heap[0];
        heap[0] = heap[--size];
        heap[size] = null; // Avoid loitering
        reheapifyDown(0);
        return max;
    }

    public E peek() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty.");
        return heap[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }


    // Also referred to as "sift up" or "bubble up"
    private void reheapifyUp(int index) {
        // Get parent index
        int parentIndex = (index - 1) / 2;

        while (index > 0 && heap[index].compareTo(heap[parentIndex]) > 0) {
            swap(parentIndex, index);
            index = parentIndex;
        }
    }

    // Also referred to as "sift down" or "bubble down"
    private void reheapifyDown(int index) {
        while (index < size) { //Avoid out of bounds
            int left = 2 * index + 1; // Left child index
            int right = 2 * index + 2; // Right child index
            int largest = index;

            /*
            TODO Swap with the largest child and continue down the tree if the current node is smaller
            */
            if(left < size && heap[left].compareTo(heap[largest]) > 0 ) {
                largest = left;
            }

            if(right < size && heap[right].compareTo(heap[largest]) > 0) {
                largest = right;
            }

            if(largest == index) {
                break;
            }

            swap(index, largest);
            index = largest;
        }
    }

    private void swap(int i, int j) {
        E temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        // Double the size of the heap array if it is full
        if (size >= heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    //Print tree structure of the heap
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Heap:\n");
        buildString(sb, 0, 0);
        return sb.toString();
    }
    // Helper method to build a string representation of the heap
    private void buildString(StringBuilder sb, int index, int depth) {
        if (index >= size) return;

        for (int i = 1; i < depth; i++) {
            sb.append("    ");
        }
        if (depth > 0) {
            sb.append("\\___");
        }
        sb.append(heap[index]).append("\n");

        int left = 2 * index + 1;
        int right = 2 * index + 2;

        buildString(sb, right, depth + 1);
        buildString(sb, left, depth + 1);
    }

    //Patient class for testing a priority queue
    private static class Patient implements Comparable<Patient> {
        private String name;
        private String condition;
        private int healthRiskLevel;
        private long admittedDate; // Timestamp to track admission order

        public Patient(String name, String condition, int healthRiskLevel) {
            this.name = name;
            this.condition = condition;
            this.healthRiskLevel = healthRiskLevel;
            this.admittedDate = System.currentTimeMillis(); // Set admission time
        }

        public int getHealthRiskLevel() {
            return healthRiskLevel;
        }

        @Override
        public int compareTo(Patient other) {
            // Compare by health risk level first
            int priorityComparison = Integer.compare(this.healthRiskLevel, other.healthRiskLevel);
            if (priorityComparison != 0) {
                return priorityComparison;
            }
            // If priorities are equal, compare by admittedDate
            return Long.compare(other.admittedDate, this.admittedDate);
        }

        @Override
        public String toString() {
            return name + " - " + condition + " (Risk Level: " + healthRiskLevel + ")";
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //M7.3 Heap - Test Code ***************
        Heap<Patient> patientQueue = new Heap<>();

        //Patients arrive to check in
        //Three patients came in and were checked in
        Patient p1 = new Patient("Alice", "Puncture wound", 2);
        Patient p2 = new Patient("Bob", "Small Laceration", 1);
        Patient p3 = new Patient("Charlie", "Heart Attack", 3);

        //Adding patients to the queue
        patientQueue.add(p1);
        patientQueue.add(p2);
        patientQueue.add(p3);

        //Wait a second to make admission time different
        Thread.sleep(1000);

        //More patients arrived
        Patient p4 = new Patient("David", "Bone Fracture", 2);
        Patient p5 = new Patient("Eve", "Seizures", 3);
        //Adding more patients to the queue
        patientQueue.add(p4);
        patientQueue.add(p5);

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
            Patient nextPatient = patientQueue.remove();
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