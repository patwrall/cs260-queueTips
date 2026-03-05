package M2_Arrays;
import M1_OOP.Position3D;


public class TheBagExample {
   public static void main(String[] args) {
       Position3DArrayBag myBag = new Position3DArrayBag();
       System.out.println("Current Capacity: " + myBag.getCapacity());
       myBag.add(new Position3D());
       myBag.add(new Position3D());
       myBag.add(Position3D.origin);
       System.out.println("Current Capacity: " + myBag.getCapacity());
       myBag.trimToSize();
       System.out.println("Current Capacity: " + myBag.getCapacity());
       System.out.print(myBag.toString());
   }
}
