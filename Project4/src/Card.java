/**
     * Creats a card object to compare
     * @author Abby Blancett
     */

public class Card implements Comparable<Card> {
    /**
     * club var
     */
    public static final char CLUBS = 'c';

    /**
     * diamond var
     */

    public static final char DIAMONDS = 'd';

    /**
     * spades var
     */

    public static final char SPADES = 's';

    /**
     * heart var
     */

    public static final char HEARTS = 'h';

    /**
     * lowest value var
     */

    public static final int LOWEST_VALUE = 2;

    /**
     * highest value var
     */

    public static final int HIGHEST_VALUE = 14;

    /**
     * queen value var
     */

    public static final int QUEEN_VALUE = 12;

    /**
     * suit stored var
     */

    private char suit;

    /**
     *  value stored var
     */

    private int value;

    /**
     * if has been played var
     */

    private boolean hasBeenPlayed;

    /**
     * Constructor
     * @param suit of card
     * @param value of card
     * @throws IllegalArgumentException if suit is invalid
     */


    public Card(char suit, int value){
        if (!((suit == 'h') || (suit == 'd') || (suit == 'c') || (suit == 's'))){
            throw new IllegalArgumentException("Invalid suit");
        }
        if ((value > HIGHEST_VALUE) || (value <= (LOWEST_VALUE - 1))){
            hasBeenPlayed = false;
            throw new IllegalArgumentException("Invalid value");
        }
        this.suit = suit;
        this.value = value;
    }

    /**
     * gets suit
     * @return suit of card
     */

    public char getSuit(){
        return this.suit;
    }
    /**
     * gets value
     * @return value of card
     */

    public int getValue(){
        return this.value;
    }

    /**
     * gets if has been played
     * @return if played
     */

    public boolean hasBeenPlayed(){
        return this.hasBeenPlayed;
    }

    /**
     * makes card played or not
     * @param played is the correct state
     */

    public void setPlayed(boolean played){
        this.hasBeenPlayed = played;
    }

    /**
     * sees if is heart card
     * @return if correct
     */

    public boolean isHeart(){
        if (this.suit == 'h'){
            return true;
        }
        return false;
    }

    /**
     * gets if card is queen of spades
     * @return if is queen of spades
     */

    public boolean isQueenOfSpades(){
        if (this.suit == 's' && (this.value == QUEEN_VALUE)){
            return true;
        }
        return false;
    }

    /**
     * sees which card is higher
     * @param other is the compared card
     * @return if value is higher
     */

    public boolean isHigherThan(Card other){
        if (this.suit == other.suit){
            if (this.value > other.value){
                return true;
            }
        }
        return false;
    }

    /**
     * gets suit
     * @param o is the compared card
     * @return suit of card
     * 
     */

    public boolean equals(Object o){
        if(o instanceof Card){
            Card other = (Card) o;
        
            if(other.getSuit() == (this.suit)){
                if(other.getValue() == (this.value)){
 
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Prints card in format
     * @return string of card
     * 
     */
    

    public String toString(){
        String out = "";
        return out + this.suit + this.value;
    }

    /**
     * This method is used for sorting cards.
     * It orders the cards by suit (Clubs, Diamonds, Spades, Hearts) and then by
     * value within the suit
     * @param other The Card object to which this Card is being compared.
     * @return  negative value if this Card should be before the other Card,
     *          positive value if this Card should be after the other Card,
     *          0 if this Card has the same suit and value as the other Card.
     */
    public int compareTo(Card other) {
        if (getSuit() == other.getSuit()) {
            if (getValue() < other.getValue()) {
                return -1;
            } else if (getValue() > other.getValue()) {
                return 1;
            }
            else {
                return 0;
            }
        } 
        else {
            switch(getSuit()) {
                case HEARTS:
                    return 1;
                case SPADES:
                    if (other.getSuit() == HEARTS) {
                        return -1;
                    } 
                    else {
                        return 1;
                    }
                case DIAMONDS:
                    if (other.getSuit() == HEARTS || other.getSuit() == SPADES) {
                        return -1;
                    } 
                    else {
                        return 1;
                    }
                //CLUBS
                default:
                    return -1;
            }
        }
    }

}
