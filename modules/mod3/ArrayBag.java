package cs260.queueTips.mod3;

/*
    Ellie Pike - pikeas01@pfw.edu
    Terrell Richey - rich03@pfw.edu
    Patrick Rall - rallpw0pfw.edu
    Ethan Quispe - quiseh01@pfw.edu
    Seth Pfister - pfissg01@pfw.edu
 */
import java.util.Arrays;
import java.util.Random;

class ArrayBag<E extends Comparable<E>> implements Cloneable {

    private E[] data;
    private int manyItems;
    private Random rnd;

    @SuppressWarnings("unchecked")
    public ArrayBag() {
        final int INITIAL_CAPACITY = 2;
        manyItems = 0;
        data = (E[]) new Comparable<?>[INITIAL_CAPACITY];
        rnd = new Random();
    }
    // Overloaded constructor to copy an existing Position3DArrayBag
    public ArrayBag(ArrayBag<E> other) {
        this.manyItems = other.manyItems; // Copy the number of items
        this.data = other.data.clone(); // Allocate new memory for the array
        this.rnd = other.rnd;
    }

    public void add(E element) {
        // Ensure twice as much space as we need.
        if (manyItems == data.length) {
            ensureCapacity((manyItems + 1) * 2);
        }

        data[manyItems] = element;
        manyItems++;
    }

    @SuppressWarnings("unchecked")
    public void ensureCapacity(int minimumCapacity) {
        E[] biggerArray;
        if (data.length < minimumCapacity) {
            biggerArray = (E[]) new Comparable<?>[minimumCapacity];
            //Tip: shallow copying, but thats OK!
            System.arraycopy(data, 0, biggerArray, 0, manyItems);
            data = biggerArray;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayBag<E> clone() throws CloneNotSupportedException {
        ArrayBag<E> clone = (ArrayBag<E>) super.clone();
        clone.data = this.data.clone();
        clone.manyItems = this.manyItems;
        clone.rnd = this.rnd;
        return clone;
    }

    public int getCapacity() {
        return data.length;
    }

    public int size() {
        return manyItems;
    }

    @SuppressWarnings("unchecked")
    public void trimToSize() {
        E[] trimmedArray;
        if (data.length != manyItems) {
            trimmedArray = (E[]) new Comparable<?>[manyItems];
            System.arraycopy(data, 0, trimmedArray, 0, manyItems);
            data = trimmedArray;
        }
    }

    // Grab is O(1) because in the end only one integer is being picked
    public E grab() {
        if (manyItems == 0) return null;
        else return data[rnd.nextInt(manyItems)];
    }

    @SafeVarargs
    // This is O(n) because it iterates through all of the element args, then appends to the existing array, which grows linearly.
    public final void addMany(E... elements) {
        // reserve 2x the capacity of many items + elements size at the start so we dont resize every iteration.
        if ((manyItems + elements.length) >= data.length) { // Ensure twice as much space as we need.
            ensureCapacity((manyItems + elements.length + 1) * 2);
        }

        for (E element : elements) {
            data[manyItems] = element;
            manyItems++;
        }
    }

    // This is also O(n) for the same reasons as addMany().
    public void addAll(ArrayBag<E> bag) {
        if ((manyItems + bag.manyItems) >= data.length) { // Ensure twice as much space as we need.
            ensureCapacity((manyItems + bag.manyItems + 1) * 2);
        }

        for (int i = 0; i < bag.manyItems; i++) {
            data[manyItems] = bag.data[i];
            manyItems++;
        }
    }

    public int countOccurrences(E e) {
        int c = 0;
        for (int i = 0; i < manyItems; i++) {
            if (e.equals(data[i])) {
                c++;
            }
        }
        return c;
    }

    // Static method for union of two bags
    public static <E extends Comparable<E>> ArrayBag<E> union(ArrayBag<E> bag1, ArrayBag<E> bag2) {
        // Create a new empty bag with enough capacity to hold all elements
        ArrayBag<E> result = new ArrayBag<>();
        result.ensureCapacity(bag1.manyItems + bag2.manyItems);

        // Add all elements from the first bag
        for (int i = 0; i < bag1.manyItems; i++) {
            result.add(bag1.data[i]);
        }

        // Add all elements from the second bag
        for (int i = 0; i < bag2.manyItems; i++) {
            result.add(bag2.data[i]);
        }
        return result;
    }

    public void sort() {
        Arrays.sort(data, 0, manyItems);
    }

    @Override
    public String toString() {
        /*String str = "[ ";
        for (E element : data) {
            if (element != null) {
                str += element.toString() + ", ";
            }
        }
        str = str.substring(0, str.length() - 2) + " ]";
        return str;*/

     StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < manyItems; i++) {
            if (data[i] == null) {
                continue;      
            }

            sb.append(data[i].toString());

            if (i < manyItems - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();

    }

    // O(n)
    // B.ii
    public int indexOf(E target) {
        for (int i = 0; i < manyItems; i++) {
            if (data[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    // O(log(n))
    // The bag must be sorted before using this method
    public int binarySearch(int first, int size, E target) {
        int mid;
        if (size <= 0) {
            return -1;
        } else {
            mid = first + (size/2);
            if (target.compareTo(data[mid]) == 0) {
                return mid;
            } else if (target.compareTo(data[mid]) < 0) {
                return binarySearch(first, size / 2, target);
            } else if (target.compareTo(data[mid]) > 0) {
                return binarySearch(mid + 1, (size - 1) / 2, target);
            }
        }
        return -1;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        ArrayBag<Integer> intBag = new ArrayBag<>();
        intBag.add(20);
        intBag.add(-100);
        intBag.add(10);
        intBag.add(3);
        intBag.add(50);
        System.out.println("There are " + intBag.countOccurrences(3) + " copies of 3.");
        System.out.println("There are " + intBag.countOccurrences(30) + " copies of 30");
        System.out.println("Current size: " + intBag.size());
        System.out.println("Original Bag (Unsorted): " + intBag);
        intBag.sort();
        System.out.println("Original Bag (Sorted): " + intBag);
        ArrayBag<Integer> copyBag = intBag.clone();

        //B.iii
        System.out.println(intBag.indexOf(99));
        System.out.println(intBag.indexOf(10));
        System.out.println("Copy Bag: " + copyBag.toString());

 
        //Part D
        //D.i
        //D.i.1
        System.out.println("Adding 10,000,000 items to the bag");
        Random random = new Random();
        for (int i = 0; i < 10_000_000; i++) {
            intBag.add(random.nextInt());
        }

         System.out.println("Grabbing 20 random items from the bag");
        //D.i.1.a
        Integer[] targets = new Integer[20];
        for (int i = 0; i < targets.length; i++) {
            targets[i] = intBag.grab();
        }
        long timeStart;
        long timeStop;
        long indexTimeDiff = 0;

        System.out.println("Sorting the elements in the bag");
        timeStart = System.nanoTime();
        intBag.sort();
        timeStop = System.nanoTime();
        long sortTime =  timeStop - timeStart;
        double seconds = sortTime / 1_000_000_000.0;


        System.out.println("Seconds taken to sort: " + seconds);
        //D.i.2
        
        for (Integer i : targets) {
            timeStart = System.nanoTime();
            intBag.indexOf(i);
            timeStop = System.nanoTime();

            if (indexTimeDiff < (timeStop - timeStart)) {
                indexTimeDiff = timeStop - timeStart;
            }
        }

        seconds = indexTimeDiff / 1_000_000_000.0;

        System.out.println("Index Time Difference in seconds: " + seconds);
        //D.i.3
        long binaryTimeDiff = 0;
        for (Integer i : targets) {
            timeStart = System.nanoTime();
            intBag.binarySearch(0, intBag.size(), i);
            timeStop = System.nanoTime();

            if (binaryTimeDiff < (timeStop - timeStart)) {
                binaryTimeDiff = timeStop - timeStart;
            }
        }
        
        System.out.println("Binary Time Difference in seconds: " + (binaryTimeDiff / 1_000_000_000.0));
        timeStart = System.nanoTime();
        intBag.toString();
        timeStop = System.nanoTime();
        System.out.println("Seconds to make toString: " + ((timeStop - timeStart) / 1_000_000_000.0));
        
    }
}
