package M6_Queues;

import java.util.HashMap;
import java.util.Random;

public class Deck { //Complete TODO comments

    private Queue<Card> cards;
    private final Random random = new Random();

    public Deck() {
        cards = new Queue<>();
    }

    //Get a random card from the deck
    public Card drawCard() {
        if(this.cards == null) {
            throw new NullPointerException("cards cannot be null");
        }

        if(this.cards.isEmpty()) {
            throw new IllegalStateException("cards cannot be empty");
        }

//        int randomCardIndex = random.nextInt(cards.list.size());
//        Card randomCard = cards.list.get(randomCardIndex);
//        cards.list.removeAt(randomCardIndex);
//        return randomCard;

        return cards.dequeue();
    }

    //Adds a card to the bottom of the deck
    public void addCard(Card card) {
        if (card == null) {
            throw new NullPointerException("New card cannot be null");
        }

        this.cards.enqueue(card);
    }

    //Adds all cards from another Deck to the bottom of the deck
    public void addCards(Deck otherDeck) {
        if(otherDeck == null) {
            throw new NullPointerException("OtherDeck cannot be null");
        }

        while(!otherDeck.cards.isEmpty()){
            cards.enqueue(otherDeck.cards.dequeue());
        }
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

    /**
     * Fills the deck with 52 cards, 4 of each face
     */
    public void fill(){
        //Remove all Cards
        cards = new Queue<>();

        HashMap<String, Integer> faceCount = new HashMap<>();

        //Add 4 of each face to the deck using static faces array from Card
        while (cards.size() < 52) {
            String face = Card.FACES[random.nextInt(13)];
            if (faceCount.containsKey(face)) {
                if (faceCount.get(face) < 4) {
                    cards.enqueue(new Card(face));
                    faceCount.put(face, faceCount.get(face) + 1);
                }
            } else {
                cards.enqueue(new Card(face));
                faceCount.put(face, 1);
            }
        }
    }

    //    Write a main method to complete the classic 2-player War card game. Use a Deck to handle all groups of Card’s in the game (initial deck, player hands, and draw pile).
    //    The rules are:
    //
    //    At the beginning of the game, each player gets half of a deck of cards.
    //
    //    Until a player runs out of cards, complete rounds of the following sequence:
    //
    //    Both players draw 1 card from their deck.
    //
    //    The player with the higher card wins both cards and places them into the bottom of their deck.
    //
    //    If the cards are equally valued (a tie), the tied cards go into a pile along with 2 “risked” cards from each player. This accrued pile is awarded to the player who wins a following round. Multiple ties can happen subsequently.V
    public static void main(String[] args) {
        Random rand = new Random();
        Deck mainDeck = new Deck();
        mainDeck.fill();

        int rounds = 0;

        Deck player1 = new Deck();
        Deck player2 = new Deck();

        for (int i = 0; i < 26; i++) {
            player1.addCard(mainDeck.drawCard());
        }

        player2.addCards(mainDeck);

        Deck aux = new Deck();


        while (!player1.cards.isEmpty() && !player2.cards.isEmpty()) {
            System.out.println("Round " + rounds);
            rounds++;

            Card p1Card = null;
            Card p2Card = null;

            if (rand.nextBoolean()) {
                p1Card = player1.drawCard();
                p2Card = player2.drawCard();
            } else {
                p2Card = player2.drawCard();
                p1Card = player1.drawCard();
            }

            System.out.println("Player 1: " + p1Card);
            System.out.println("Player 2: " + p2Card);

            if (rand.nextBoolean()) {
                aux.addCard(p1Card);
                aux.addCard(p2Card);
            } else {
                aux.addCard(p2Card);
                aux.addCard(p1Card);
            }
            if(p1Card.compareTo(p2Card) > 0) {// Player 1 wins
                player1.addCards(aux);
                aux.cards = new Queue<>();
                System.out.println("Player 1 wins the round");
                System.out.println();
            }else if(p1Card.compareTo(p2Card) < 0) {// Player 2 wins
                player2.addCards(aux);
                aux.cards = new Queue<>();
                System.out.println("Player 2 wins the round");
                System.out.println();
            }else{// Tie
                System.out.println();
                System.out.println("It's a tie. Each player lays 2 risk cards.");
                if (player1.cards.size() < 2) {
                    break;
                }
                aux.addCard(player1.drawCard());
                aux.addCard(player1.drawCard());

                if (player2.cards.size() < 2) {
                    break;
                }
                aux.addCard(player2.drawCard());
                aux.addCard(player2.drawCard());
            }
        }

        if (player1.cards.isEmpty()) {
            System.out.println("Player 1 is out of cards.");
            System.out.println("Player 2 wins the game!");
        } else {
            System.out.println("Player 2 is out of cards.");
            System.out.println("Player 1 wins the game!");
        }

    }
}