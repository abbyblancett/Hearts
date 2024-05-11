import java.util.Arrays;


/**
     * Creates a player class for the users info
     * @author Abby Blancett
     */ 

public class Player {

    /**
     * cards in users hand
     */ 

    public static final int CARDS_IN_HAND =  13;

    /**
     * users name
     */ 

    private String name;

    /**
     * cards played in total hand
     */ 

    private int overallPoints;

    /**
     * points in users hand
     */ 

    private int handPoints;

    /**
     * cards in users hand
     */ 

    private Card[] hand;

    /**
     * index of next empty spot
     */ 

    private int next;

/**
     * Contructor that takes name
     * @param name of user
     */ 

    public Player(String name){
        this.name = name;
        hand = new Card[CARDS_IN_HAND]; 
    }
/**
     * Users name getter
     * @return user name
     */ 

    public String getName(){
        return this.name;
    }

/**
     * adds card to hand
     * @param card is to be added
     * @throws IllegalStateException if full hand
     */ 

    public void addCard(Card card){
        if (this.next >= CARDS_IN_HAND){
            throw new IllegalStateException("Full hand");
        }

        hand[next] = card;
        next++;
       
        Arrays.sort(hand, 0, next);
        
        

    }

    /**
     * gets points in hand
     * @return hand points
     */ 

    public int getHandPoints(){
        return handPoints;
    }
/**
     * gets points overall
     * @return all the points
     */ 

    public int getOverallPoints(){
        return overallPoints;
    }

    /**
     * Adds points to hand
     * @param points to add
     */ 

    public void addToHandPoints(int points){
        handPoints += points;
        overallPoints += points;
    }
/**
     * gets card at index
     * @param index of card to get
     * @return card to return
     * @throws IllegalArgumentException if index is not valid
     * 
     */ 

    public Card getCard(int index){
        if (index < 0 || index >= CARDS_IN_HAND){
            throw new IllegalArgumentException("Invalid index");
        }

        return hand[index];
    }

    /**
     * Sees if card in hand o given suit
     * @param suit of given card desired
     * @return if there is a card
     * @throws IllegalStateException if hand isn't full
     */ 

    public boolean hasActiveCardOfSuit(char suit){
        if (next < CARDS_IN_HAND){
            throw new IllegalStateException("Hand not full");
        }

        for(int x = 0; x < CARDS_IN_HAND; x++){
            if ((hand[x].getSuit() == suit) && (hand[x].hasBeenPlayed() == false)){
                return true;

            }
        }

        return false;
    }

    /**
     * if there is only hearts in hand
     * @return if only hearts
     * @throws IllegalStateException if hand isn't full
     */ 

    public boolean onlyHasHearts(){
        if (next < CARDS_IN_HAND){
            throw new IllegalStateException("Hand not full");
        }
        for(int x = 0; x < CARDS_IN_HAND; x++){
            if ((hand[x].getSuit() == 'h') && (hand[x].hasBeenPlayed() == false)){
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets card names
     * @return array of card names
     * @throws IllegalStateException if hand isn't full
     */

    public String[] getCardNames(){
        if (next < CARDS_IN_HAND){
            throw new IllegalStateException("Hand not full");
        }

        String[] cardStr = new String[CARDS_IN_HAND];
        for(int x = 0; x < CARDS_IN_HAND; x++){
            cardStr[x] = hand[x].toString();
            
        }
        return cardStr;
    }

    /**
     * Prints layout of cards
     * @return String of player
     */

    public String toString(){
        return "" + name + ": " + handPoints;

    }

    /**
     * clears cards in hand
     */

    public void dumpCards(){
        this.next = 0;
        for(int x = 0; x < CARDS_IN_HAND; x++){
            if (hand[x] != null){
                hand[x] = null;
            }
            
            
        }
        
    }

    /**
     * resets hand points to 0
     */

    public void resetHandPoints(){
        handPoints = 0;
    }

  /**
     * This method determines the card that a computer player will play in the
     * current round (trick). This method assumes the hand has been sorted and
     * is in order by suits - clubs, diamond, spades, hearts - and the values of
     * the cards in each suit are ordered from lowest to highest value.
     * @param startingCard the card that started the round
     * @param isFirstRound whether or not this is the first round of a hand
     * @param heartsStarted whether or not hearts are in play at this point in the hand
     * @return the card that will be played
     * @throws IllegalStateException if wrong state
     */
    public Card getMove(Card startingCard, boolean isFirstRound, boolean heartsStarted) {
        
        //If this is the first round (trick) and the computer player has the 2 of Clubs, 
        //they must play it. If the player has the 2 of Clubs, it should be the first 
        //card in their (sorted) hand
        if (isFirstRound && startingCard == null && 
            hand[0].getSuit() == Card.CLUBS && hand[0].getValue() == 2) {
            hand[0].setPlayed(true);
            return hand[0];
        } 
        
        //If an initial card was played, the computer player must follow suit
        //and will play the lowest card in that suit
        if (startingCard != null) {
            char currentSuit = startingCard.getSuit(); 
            for (int i = 0; i < CARDS_IN_HAND; i++) {
                if (!hand[i].hasBeenPlayed() && hand[i].getSuit() == currentSuit) {
                    hand[i].setPlayed(true);
                    return hand[i];
                }
            }
        }

        //If no card with a matching suit was found and it's not the first round(trick)
        //the computer player will play the Queen of Spades if they have it and it
        //hasn't been played yet
        //If not, they will play their highest valued Heart, if they have one and it
        //hasn't been played yet
        if (startingCard != null && !isFirstRound) {
            //Look for the Queen of Spades
            for (int i = 0; i < CARDS_IN_HAND; i++) {
                if (hand[i].getSuit() == Card.SPADES && hand[i].getValue() == Card.QUEEN_VALUE &&
                    !hand[i].hasBeenPlayed()) {
                    hand[i].setPlayed(true);
                    return hand[i];
                }
            }
            for (int i = CARDS_IN_HAND - 1; i >= 0; i--) {
                if (hand[i].getSuit() == Card.HEARTS && !hand[i].hasBeenPlayed()) {
                    hand[i].setPlayed(true);
                    return hand[i];
                }
            }
        }

        //If no card has been found yet, the first card that hasn't been played
        //in the sorted hand will be played
        for (int i = 0; i < hand.length; i++) {
            if (!hand[i].hasBeenPlayed()) {
                
                //A club or diamond is always valid
                if (hand[i].getSuit() == Card.CLUBS ||
                    hand[i].getSuit() == Card.DIAMONDS) {
                    hand[i].setPlayed(true);
                    return hand[i];
                }
                
                //All spades other than the queen are valid
                //The queen of spades can be played if it's not the
                //first round(trick)
                if (hand[i].getSuit() == Card.SPADES) {
                    if (hand[i].getValue() != Card.QUEEN_VALUE) {
                        hand[i].setPlayed(true);
                        return hand[i];
                    }
                    else if (!isFirstRound) {
                        hand[i].setPlayed(true);
                        return hand[i];
                    }
                }
                
                //A heart is valid if it's not the first round 
                //and either hearts have been played previously or
                //the player only has hearts. NOTE: This case would 
                //occur when the player is playing the initial card
                //in the trick
                if (hand[i].getSuit() == Card.HEARTS && !isFirstRound && 
                    (heartsStarted || onlyHasHearts())) {
                    hand[i].setPlayed(true);
                    return hand[i];
                }    
            }
        }
        //No card found so far - this could happen in the unlikely situation
        //that the player's hand contained only hearts or
        //the Queen of spades with the rest of the cards being hearts
        //Return the first unplayed card        
        for (int i = 0; i < hand.length; i++) {
            if (!hand[i].hasBeenPlayed()) {
                hand[i].setPlayed(true);
                return hand[i];
            }
        }
        //No unplayed card in hand
        throw new IllegalStateException("No unplayed card in hand");

    }
}
