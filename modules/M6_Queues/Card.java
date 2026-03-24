package M6_Queues;

public class Card { //Fully completed, do not edi


    protected final static String[] FACES =
            {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    private String face;

    public Card(String f) {
        face = f;
    }

    public String getFace() {
        return face;
    }

    //Determine value of card (2-11, Jack 12, Queen 13, Ace 14)
    public int valueOf() {
        for (int i = 0; i < FACES.length; i++) {
            if (face == FACES[i]) return i + 2;
        }
        return -1; //Should not happen
    }

    public String toString() {
        return face;
    }

    //If this card is better, return > 0, if worse return < 0, if equal return 0
    public int compareTo(Card other) {
        return this.valueOf() - other.valueOf();
    }
}