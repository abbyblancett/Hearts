import java.util.Random;

/**
     * Creates class to hold Cards
     * @author Abbt Blancett
     */

public class Deck {

    /**
     * number of cards in deck
     */

    public static final int CARDS_IN_DECK =  52;

    /**
     * cards per siut
     */

    public static final int CARDS_PER_SUIT =  14;

    /**
     * cards array 
     */

    private Card[] cards;

    /**
     * next index of empty spot
     */

    private int next = 0;

    /**
     * Constructor for clean deck
     */

    public Deck(){
        cards = new Card[CARDS_IN_DECK];
        char[] suits = {'c','d','s','h'};

        int x = 0;

        for (int y = 0; y < suits.length; y++){
            for (int z = 2; z <= CARDS_PER_SUIT; z++){
                
                cards[x] = new Card(suits[y], z);
                x++;
            }
        }

        

    }

    /**
     * gets next value
     * @return next int
     */

    public int getNext(){
        return this.next;
    }

    /**
     * gets Card Array
     * @return cards
     */

    public Card[] getCards(){
        return this.cards;
    }

    /**
     * mixes up cards
     * 
     */

    public void shuffle(){
        Random rand = new Random();

        Card x;
        int r;

        for (int i = (CARDS_IN_DECK - 1); i > 1; i--){
            r = rand.nextInt(i);
            x = cards[i];
            cards[i] = cards[r];
            cards[r] = x;
            
        }

    }

    /**
     * makes cards not played
     */

    public void initialize(){

        for (int x = 0; x < CARDS_IN_DECK; x++){
            if (cards[x].hasBeenPlayed()){
                cards[x].setPlayed(false);
                
            }

        }
        next = 0;

    }

/**
     * gets next card
     * @return next int
     * @throws IllegalStateException for no next card
     */

    public Card nextCard(){
        if (next >= CARDS_IN_DECK){
            throw new IllegalStateException("No more cards");
        }

        int n = next;
        next++;
    
        return cards[n];
    }

    /**
     * Sees if two objects are equal
     * @param o for another Deck instance
     * @return if they are equal
     */

    public boolean equals(Object o){
        if(o instanceof Deck){
            Deck other = (Deck) o;
        
            if(other.getNext() == (this.next)){
                Card[] otherC = other.getCards();
                for (int x = 0; x < CARDS_IN_DECK; x++){
                    if (otherC[x].equals(this.cards[x])){
                        continue;
                    } else {
                        return false;
                    }

        
                }
            } else {
                return false;
            }
        }
        return true;




    }

    /**
     * makes deck to be printed
     * @return deck as string form
     */

    public String toString(){
        String out = "";
        for (int i = 0; i < CARDS_IN_DECK; i++){
            out += "card " + i + ": " + cards[i].toString() + "\n";
        }

        return out;

    }
    
    
}
