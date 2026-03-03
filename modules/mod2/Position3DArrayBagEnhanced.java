/**
 * Terrell Richey - richtj03@pfw.edu
 * CS260 - Lab 2
 * */

package cs260.queueTips.mod2;
import cs260.queueTips.mod1.Position3D;

/** Lab 2: Position3D Enhancements

Add the following features to the Position3DArrayBag collection class that you built in class:

boolean remove(Position3D target) – deletes one copy of a specific element from the bag. The method should return false if the target element doesn’t exist in the bag.

boolean removeAll(Position3D target) – deletes all copies of a specific element from the bag. The method should return false if the target element doesn’t exist in the bag.

String toString() – Neatly prints a representation of each contained element in the bag.

In a multi-line comment above each method, add a running-time analysis of the methods and be prepared to discuss them. */

public class Position3DArrayBagEnhanced {
   private Position3D[] data;
   private int manyItems;

   public Position3DArrayBagEnhanced() {
       final int INITIAL_CAPACITY=2;
       manyItems=0;
       data=new Position3D[INITIAL_CAPACITY];
   }

      public Position3DArrayBagEnhanced(int INITIAL_CAPACITY) {
       if(INITIAL_CAPACITY < 0) {
        throw new IllegalArgumentException("INITIAL_CAPACITY must be a positive natural number");
       }
       manyItems=0;
       data=new Position3D[INITIAL_CAPACITY];
   }

   public void add(Position3D element) {
       if (manyItems==data.length)
           ensureCapacity((manyItems + 1)*2);
       data[manyItems]=element;
       manyItems++;
   }

   public void ensureCapacity(int minimumCapacity) {
       Position3D[] biggerArray;
       if (data.length < minimumCapacity) {
           biggerArray=new Position3D[minimumCapacity];
           System.arraycopy(data, 0, biggerArray, 0, manyItems);
           data=biggerArray;
       }
   }

   public int getCapacity() {
       return data.length;
   }

   public void trimToSize() {
       Position3D[] trimmedArray;
       if (data.length!=manyItems) {
           trimmedArray=new Position3D[manyItems];
           System.arraycopy(data, 0, trimmedArray, 0, manyItems);
           data=trimmedArray;
       }
   }

   public static Position3DArrayBagEnhanced union(Position3DArrayBagEnhanced b1, Position3DArrayBagEnhanced b2) {
       Position3DArrayBagEnhanced bag=new Position3DArrayBagEnhanced(b1.manyItems + b2.manyItems);
       for(int i=0; i < b1.manyItems; i++) {
        bag.add(b1.data[i]);
       }

       for(int i=0; i < b2.manyItems; i++) {
        bag.add(b2.data[i]);
       }
       return bag;
   };

    /** public boolean remove(Position3D target)
     * 
     * Deletes one copy of a specific element from the bag.
     * The method should return false if the target element doesn’t exist in the bag.
     * 
     * Running time analysis - O(2n) (n = this.manyItems)
     * 
     * The run time is linear because as the input grows, the output grows at twice the rate.
     * This is because we loop through the Items in the bag twice, and all other operations
     * run in constant time.
     * 
     */
    public boolean remove(Position3D target) {
        boolean found=false;
        
        for (int i=0; i < manyItems; i++) {
            if(data[i]!=null && data[i].equals(target)) {
                data[i]=null;
                found=true;
                break;
            }
        }

        if(found==false) {
            return found;
        }

        Position3D[] arr=new Position3D[data.length]; 

        for (int i=0, j=0; i < manyItems; i++) {
            if(data[i]==null) continue;
            arr[j]=data[i];
            j++;
        }
        data=arr;
        manyItems--;
        return found;
    }


    /** public boolean removeAll(Position3D target)
     * 
     * Deletes all copies of a specific element from the bag. 
     * The method should return false if the target element doesn’t exist in the bag.
     * 
     * Running time analysis - O(2n) (n = this.manyItems)
     * 
     * The run time is linear because as the input grows, the output grows at twice the rate.
     * This is because we loop through the Items in the bag twice, and all other operations
     * run in constant time.
     */
    public boolean removeAll(Position3D target) {
        
        boolean found=false;
        int targetsFound=0;
        int newManyItems=0;
        
        for (int i=0; i < manyItems; i++) {
            if(data[i]!=null && data[i].equals(target)) {
                data[i]=null;
                targetsFound++;
            }
        }

        if(targetsFound==0) {
            return found;
        } else {
            found=true;
        }

        Position3D[] arr=new Position3D[data.length]; 

        for (int i=0; i < manyItems; i++) {
            if(data[i]==null) continue;
            arr[newManyItems]=data[i];
            newManyItems++;
        }

        data=arr;
        manyItems=newManyItems;
        return found;
    }

    /**@Override public String toString()
     * 
     * Neatly prints a representation of each contained element in the bag.
     * 
     * Running time analysis - O(n) (n = this.manyItems)
     * 
     * The run time is linear because as the input grows, the output grows at the same rate.
     * This is because we loop through the items in the bag once. All other operations run 
     * in constant time.
     */
   @Override
   public String toString() {
    StringBuilder sb=new StringBuilder("Bag ");
    sb.append(this.hashCode())
        .append(":\n")
        .append("\n\tTotal Items: ")
        .append(manyItems)
        .append("\n")
        .append("\tCurrent Capacity: ")
        .append(this.data.length)
        .append("\n");

    for (int i=0; i < manyItems; i++) {
        sb.append("\n\tPosition3D #")
            .append(i+1)
            .append(": ")
            .append(data[i].toString())
            .append("\n");
    }
    sb.append("----------------------------------------------------------------------");
    return sb.toString();
   }

}
