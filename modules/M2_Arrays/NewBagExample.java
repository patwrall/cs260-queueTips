package M2_Arrays;
import M1_OOP.Position3D;

public class NewBagExample {
   public static void main(String[] args) {
       Position3DArrayBagEnhanced myBag = new Position3DArrayBagEnhanced();
       myBag.add(new Position3D());
       myBag.add(new Position3D());
       myBag.add(new Position3D());
       myBag.add(Position3D.origin);
       myBag.add(Position3D.origin);
       myBag.add(Position3D.origin);
       myBag.add(Position3D.origin);
       System.out.println(myBag.toString());
       myBag.trimToSize();
       System.out.println(myBag.toString());
       myBag.remove(Position3D.origin);
       System.out.println(myBag.toString());
       myBag.removeAll(Position3D.origin);
       myBag.trimToSize();
       System.out.println(myBag.toString());
          
   }
}
