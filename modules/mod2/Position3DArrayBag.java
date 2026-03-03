/*
   Ellie Pike - pikeas01@pfw.edu
   Terrell Richey - rich03@pfw.edu
   Patrick Rall - rallpw0pfw.edu
   Ethan Quispe - quiseh01@pfw.edu
   Seth Pfister - pfissg01@pfw.edu
*/
package cs260.queueTips.mod2;
import cs260.queueTips.mod1.Position3D;
// Invariant of the IntArrayBag class:
// 1. The number of elements in the bag is in the instance variable
// manyItems, which is no more than data.length.
// 2. For an empty bag, we do not care what is stored in any of data;
// for a non-empty bag, the elements in the bag are stored in data[0]
// through data[manyItems-1], and we don’t care what’s in the
// rest of data.
class Position3DArrayBag implements Cloneable {
   private Position3D[] data;
   private int manyItems;

   public Position3DArrayBag() {
       final int INITIAL_CAPACITY = 2;
       manyItems = 0;
       data = new Position3D[INITIAL_CAPACITY];
   }

      public Position3DArrayBag(int INITIAL_CAPACITY) {
       if(INITIAL_CAPACITY < 0) {
        throw new IllegalArgumentException("INITIAL_CAPACITY must be a positive natural number");
       }
       manyItems = 0;
       data = new Position3D[INITIAL_CAPACITY];
   }

   public void add(Position3D element) {
       if (manyItems == data.length)
           ensureCapacity((manyItems + 1)*2);
       data[manyItems] = element;
       manyItems++;
   }

   public void ensureCapacity(int minimumCapacity) {
       Position3D[] biggerArray;
       if (data.length < minimumCapacity) {
           biggerArray = new Position3D[minimumCapacity];
           System.arraycopy(data, 0, biggerArray, 0, manyItems);
           data = biggerArray;
       }
   }

   public int getCapacity() {
       return data.length;
   }

   public void trimToSize() {
       Position3D[] trimmedArray;
       if (data.length != manyItems) {
           trimmedArray = new Position3D[manyItems];
           System.arraycopy(data, 0, trimmedArray, 0, manyItems);
           data = trimmedArray;
       }
   }

   public static Position3DArrayBag union(Position3DArrayBag b1, Position3DArrayBag b2) {
       Position3DArrayBag bag = new Position3DArrayBag(b1.manyItems + b2.manyItems);
       for(int i = 0; i < b1.manyItems; i++) {
        bag.add(b1.data[i]);
       }

       for(int i = 0; i < b2.manyItems; i++) {
        bag.add(b2.data[i]);
       }       
       return bag;
   };
}


