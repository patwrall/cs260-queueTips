package cs260.queueTips.mod3;
import java.lang.reflect.Array;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class Table<K, V> {
    private int manyItems;
    private K[] keys;
    private V[] data;
    private boolean[] hasBeenUsed;
    private float loadFactor;
    private int collisionCount;

    public Table(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity is negative");
	    //Ensure capacity is a power of 2 above the given capacity
            if (Integer.bitCount(capacity) != 1) {
            int newCapacity = Integer.highestOneBit(capacity) << 1;
            capacity = newCapacity;
        }
        keys = (K[]) Array.newInstance(Object.class, capacity);
        data = (V[]) Array.newInstance(Object.class, capacity);
        hasBeenUsed = new boolean[capacity];
   }

   private void setLoadFactor() {
	this.loadFactor =  ((float) manyItems / (float) data.length); 
   }

   public int getCollisionCount() {
	return collisionCount;
   }

    public float getLoadFactor() {
	return loadFactor;
    }
    public int findIndex(K key) {
        if(key==null) {
	   return -1;
	}
	int count = 0;
        int i = hash(key);
        while (count < data.length && hasBeenUsed[i]) {
            if (keys[i] != null && key.equals(keys[i]))
                return i;
            count++;
            i = nextIndex(i);
        }
        return -1;
    }

    /**
     * A hash function used in Java's HashMap
     * Expects the table size to be a power of 2
     */
  /*  private int hash(K key) {
        int h = 0;
        //Performs a bitwise exclusive OR of the two halves of the 32-bit int from hashCode().
        if (key != null) {
            h = key.hashCode();
            h = (h >>> 16) % data.length; // Shift 32-bit to the right by 16 bits and compress to the size of the table
        }
        return h;
    }*/
    private int nextIndex(int i) {
        if (i + 1 == data.length)
            return 0;
        else
            return i + 1;
    }

    /**
     * @return Previous value for this key, or null if the key is new
     */
    public V put(K key, V element) {
	boolean collision = false;
        int index = findIndex(key);
        V answer;
        if (index != -1) { // The key is already in the table.
            answer = data[index];
            data[index] = element;
            return answer;
        } else if (manyItems < data.length) { // The Table is not full.
            index = hash(key);
            while (keys[index] != null) {
                index = nextIndex(index);
		collision=true;
	    }

	    if(collision==true) 
	    	collisionCount++;
	    keys[index] = key;
            data[index] = element;
            hasBeenUsed[index] = true;
            manyItems++;
	    setLoadFactor();
            return null;
        } else
	   // The table is full.
           throw new IllegalStateException("Table is full.");
    }

    public V get(K key) {
        int index = findIndex(key);
        if (index == -1)
            return null;
        else
            return data[index];
    }
    public int size() {
        return manyItems;
    }

    // Determines if a key is in the table.
    public boolean containsKey(K key) {
        if(key == null) {
            return false;
        }

        int i = findIndex(key);

        if (i == -1) {
            return false;
        }

        return keys[i] != null;
    }

    // Removes an element from the table and returns
     public V remove(K key) {


        if (!containsKey(key)) {
            return null;
        }

        int i = findIndex(key);

        V t = data[i];

        keys[i] = null;
        data[i] = null;
	manyItems--;
	setLoadFactor();
        return t;
     }

/*
Lab 4: Table stats
Modify the Table class to keep two statistics:
Load factor α (0.0 to 1.0) – Indicates how full the table is
Collision Count – Total # of collisions that happen throughout the table’s existence

Write a main method that creates a table of 1024 capacity and adds 750 elements using the provided sample file SSN_NAME.csv. In this scenario, the SSN is the table key. Test that the table has an expected load factor and report the total collisions. Repeat the same collision test with a new simpler hash function and discuss any differences in the collision count:

private int hash(K key) {
    return (key.hashCode() & Integer.MAX_VALUE) % data.length;
}
*/

private int hash(K key) {
    return (key.hashCode() & Integer.MAX_VALUE) % data.length;
}

public static void main(String[] args) {
	final int capacity = 1024;
	//We will use buffer to read the file line by line.
	String buffer;
	String key;
	String value;
        Table<String, String> tbl = new Table<>(capacity);
	
	File ssnFile = new File("./edu/pfw/richtj03/assets/SSN_NAME.csv");
	try(Scanner scanner = new Scanner(ssnFile)) {
	    while(scanner.hasNextLine()) {
		buffer = scanner.nextLine();
		for(int i = 0; i < buffer.length();i++ ) {
		 if(buffer.charAt(i) == ',') {
		 key = buffer.substring(0,i);
		 value = buffer.substring(i+1);
		 tbl.put(key,value);
		 System.out.println(value + " has been added with ssn " + key + "\n");
                 System.out.println("Current Load Factor: " + tbl.getLoadFactor());
                 System.out.println();
		 break;
		 }
		}
	    }

	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
	System.out.println("Table Item Count: " + tbl.size());
	System.out.println(tbl.getCollisionCount());
    }
}
