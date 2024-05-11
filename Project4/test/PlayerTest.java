import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * Tests Player class
 * 
 * @author Jessica Young Schmidt
 * @author Abby Blancett
 */
public class PlayerTest {

    /** Test player */
    private Player testPlayer;

    /** Three of clubs */
    private Card c3;

    /**
     * Set up fields for tests
     */
    @BeforeEach
    public void setUp() {
        testPlayer = new Player("Human");
        c3 = new Card('c', 3);
    }

    /**
     * Set up construcot for tests
     */

    @Test
    public void testConstructor() {
        assertEquals("Human", testPlayer.getName(), "Test constructor: getName");
        for (int i = 0; i < 13; i++) {
            assertNull(testPlayer.getCard(i), "Test that card " + i + " is null");
        }
        assertEquals(0, testPlayer.getHandPoints(), "Test constructor: getHandPoints");
        assertEquals(0, testPlayer.getOverallPoints(), "Test constructor: getOverallPoints");
    }

    /**
     * test add one card
     */

    @Test
    public void testAddCardA() {
        // Test that there are no cards in hand
        String id = "test that first card of hand is null";
        assertNull(testPlayer.getCard(0), id);

        // Add a card to the hand
        testPlayer.addCard(c3);

        id = "test that card is added as first card";
        assertEquals(new Card('c', 3), testPlayer.getCard(0), id);
        for (int i = 1; i < 13; i++) {
            assertNull(testPlayer.getCard(i), "Test that card " + i + " is null");
        }
    }

    /**
     * test add  cards
     */

    @Test
    public void testAddCardB() {
        testPlayer.addCard(c3);
        Card c5 = new Card('c', 5);
        testPlayer.addCard(c5);
        String id = "test that card is added as first and second card";
        assertEquals(new Card('c', 3), testPlayer.getCard(0), id);
        assertEquals(new Card('c', 5), testPlayer.getCard(1), id);
        for (int i = 2; i < 13; i++) {
            assertNull(testPlayer.getCard(i), "Test that card " + i + " is null");
        }      
    }

    /**
     * Tests possible exception
     */

    @Test
    public void testGetCardException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> testPlayer.getCard(-1), "Invalid index");
        assertEquals("Invalid index", exception.getMessage(), "Invalid index - exception message");
    }

    /**
     * Test hasActiveSuit
     */

    @Test
    public void testHasActiveCardOfSuitException() {
        Exception exception = assertThrows(IllegalStateException.class,
            () -> testPlayer.hasActiveCardOfSuit('c'), "Hand not full");
        assertEquals("Hand not full", exception.getMessage(), "Hand not full - exception message");
    }

    /**
     * Test is only hearts in hand
     */

    @Test
    public void testOnlyHasHeartsException() {
        Exception exception = assertThrows(IllegalStateException.class,
            () -> testPlayer.onlyHasHearts(), "Hand not full");
        assertEquals("Hand not full", exception.getMessage(), "Hand not full - exception message");
    }

    /**
     * Test getNames of cards
     */

    @Test
    public void testGetCardNamesException() {
        Exception exception = assertThrows(IllegalStateException.class,
            () -> testPlayer.getCardNames(), "Hand not full");
        assertEquals("Hand not full", exception.getMessage(), "Hand not full - exception message");
    }


    /**
     * test hasActiveCardOfSuit for hand with all cards of suit already played
     */     
    @Test
    public void testHasActiveCardOfSuitA() {
        // hand has to be full
        testPlayer.addCard(c3);
        for (int i = 2; i < 14; i++) {
            testPlayer.addCard(new Card (Card.HEARTS, i));
        }
        Card card = testPlayer.getCard(0);
        card.setPlayed(true);
        assertFalse(testPlayer.hasActiveCardOfSuit('c'));
    }
/**
     * test hasActiveCardOfSuit for hand with all cards of suit to get true
     */ 

    @Test
    public void testHasActiveCardOfSuitB() {
        
        testPlayer.addCard(c3);
        for (int i = 2; i < 14; i++) {
            testPlayer.addCard(new Card (Card.HEARTS, i));
        }
        Card card = testPlayer.getCard(5);
        card.setPlayed(true);
        assertTrue(testPlayer.hasActiveCardOfSuit('h'));       
    }

    /**
     * test hasActiveCardOfSuit for hand with all cards of not the suit
     */ 

    @Test
    public void testHasActiveCardOfSuitC() {
        testPlayer.addCard(c3);
        for (int i = 2; i < 14; i++) {
            testPlayer.addCard(new Card (Card.HEARTS, i));
        }
        Card card = testPlayer.getCard(5);
        card.setPlayed(true);
        assertFalse(testPlayer.hasActiveCardOfSuit('d'));   
    }  
    
    /**
     * test hasOnlyHearts when does
     */ 


    @Test
    public void testOnlyHasHeartsA() {
        for (int i = 2; i <= 14; i++) {
            testPlayer.addCard(new Card (Card.HEARTS, i));
        }
        assertTrue(testPlayer.onlyHasHearts(), "player has only hearts");
    }

    /**
     * test hasOnlyHearts when none are
     */ 

    @Test
    public void testOnlyHasHeartsB() {
        for (int i = 2; i <= 14; i++) {
            testPlayer.addCard(new Card (Card.DIAMONDS, i));
        }
        assertFalse(testPlayer.onlyHasHearts(), "player has only diamonds");  
    }
    
    /**
     * test hasOnlyHearts some are
     */ 

    @Test
    public void testOnlyHasHeartsC() {
        testPlayer.addCard(new Card (Card.HEARTS, 2));
        for (int i = 3; i <= 14; i++) {
            testPlayer.addCard(new Card (Card.DIAMONDS, i));
        }
        assertFalse(testPlayer.onlyHasHearts(), "player has a mix");       
    }
    
    /**
     * Test getCardNames for card multiple
     */

    @Test
    public void testGetCardNames() {
        String[] names = new String[13];
        for (int i = 2; i <= 14; i++) {
            testPlayer.addCard(new Card (Card.CLUBS, i));
            names[i - 2] = "" + Card.CLUBS + i;
        }
        assertArrayEquals(names, testPlayer.getCardNames(), "test getCardNames() for all clubs");  
    }

    /**
     * Test add points to hand for multiple attempts
     */


    @Test
    public void testAddToHandPoints() {

        testPlayer.addToHandPoints(1);
        testPlayer.addToHandPoints(3);
        int total = testPlayer.getHandPoints();
        assertEquals(4, total, "Added 4 points to hand");
        assertEquals(4, testPlayer.getOverallPoints(), "Added 4 points to hand and got overall");
        
        

    }

    /**
     * Tests reset hand points after addition
     */

    @Test
    public void testResetHandPoints() {
           
        testPlayer.addToHandPoints(1);
        testPlayer.addToHandPoints(3);
        testPlayer.resetHandPoints();
        assertEquals(testPlayer.getHandPoints(), 0, "Added 4 points to hand");
    }

    /**
     * Test dumpCards from hand
     */

    @Test
    public void testDumpCards() {
        
        testPlayer.addCard(c3);
        String id = "test dump Cards(): cards [0] not null before dump";
        assertEquals(new Card('c', 3), testPlayer.getCard(0), id);
        testPlayer.dumpCards();
        id = "test dumpCards: cards[0] = null after dump";
        assertEquals(null, testPlayer.getCard(0), id);
        testPlayer.addCard(c3);
        id = "test dump Cards(): next was reset to 0";
        assertEquals(new Card('c', 3), testPlayer.getCard(0), id);       
    }
    
    /**
     * Tests toString
     */
    @Test
    public void testToString() {
        assertEquals("Human: 0", testPlayer.toString(), "Test toString");
    }

    /**
     * Test addToHand for non zero
     */

    @Test
    public void testToStringNonZeroPoints() {
        testPlayer.addToHandPoints(5);
        assertEquals("Human: 5", testPlayer.toString(), "Test toString");       
    }
    
    

    /**
     * Test getMove method
     */
    @Test
    public void testGetMove() {

        Card c2 = new Card('c', 2);
        Card c12 = new Card('c', 12);
        Card d4 = new Card('d', 4);
        Card d5 = new Card('d', 5);
        Card d7 = new Card('d', 7);
        Card d12 = new Card('d', 12);
        Card s5 = new Card('s', 5);
        Card s11 = new Card('s', 11);
        Card s12 = new Card('s', 12);
        Card h2 = new Card('h', 2);
        Card h3 = new Card('h', 3);
        Card h5 = new Card('h', 5);

        testPlayer.addCard(c3);
        testPlayer.addCard(h5);
        testPlayer.addCard(d7);
        testPlayer.addCard(s12);
        testPlayer.addCard(c12);
        testPlayer.addCard(c2);
        testPlayer.addCard(d4);
        testPlayer.addCard(h2);
        testPlayer.addCard(s5);
        testPlayer.addCard(s11);
        testPlayer.addCard(d5);
        testPlayer.addCard(h3);
        testPlayer.addCard(d12);

        // Test first round (trick) and the computer player has the 2 of Clubs
        Card c2Copy = new Card('c', 2);
        c2Copy.setPlayed(true);
        assertEquals(c2Copy, testPlayer.getMove(null, true, false), "Has 2 of clubs");
        Card c3copy = new Card('c', 3);
        c3copy.setPlayed(true);
        assertEquals(c3copy, testPlayer.getMove(new Card('c', 4), true, false), "Lowest clubs");
    }
}