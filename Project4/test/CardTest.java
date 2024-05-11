import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * Tests Card class
 * 
 * @author Jessica Young Schmidt
 * @author Abby Blancett
 */
public class CardTest {

    /** three of clubs */
    private Card c3;

    /** seven of hearts */
    private Card h7;
      

    /**
     * Sets up field for testing
     */
    @BeforeEach
    public void setUp() {
        c3 = new Card('c', 3);   
        h7 = new Card('h', 7);
    }

    /**
     * Test invalid constructor parameters
     */
    @Test
    public void testInvalidConstructorParameters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Card('a', 33),
                "invalid suit");
        assertEquals("Invalid suit", exception.getMessage(), "invalid suit - exception message");

        exception = assertThrows(IllegalArgumentException.class, () -> new Card('c', 1),
                "invalid value (1)");
        assertEquals("Invalid value", exception.getMessage(),
                "invalid value (1) - exception message");

        // NOTE: You are not required to add additional invalid parameter test. However,
        // you may add tests if you would like. Not all paths through your Card
        // constructors will be tested by the given tests above.
    }
    
  

    /**
     * Test getSuit for three of clubs
     */
    @Test
    public void testGetSuitA() {
        assertEquals('c', c3.getSuit(), "Test getSuit for three of clubs");
    }

    /**
     * Test getSuit for seven of hearts
     */

    @Test
    public void testGetSuitB() {

        assertEquals('h', h7.getSuit(), "Test getSuit for seven of hearts");
       
                
    }

    /**
     * Test getValue for three of clubs
     */
    @Test
    public void testGetValueA() {
        assertEquals(3, c3.getValue(), "Test getValue for three of clubs");
    }

    /**
     * Test getValue for seven of hearts
     */

    @Test
    public void testGetValueB() {
        assertEquals(7, h7.getValue(), "Test getValue for seven of hearts");
        
    }

    /**
     * Test hasBeenPlayed
     */
    @Test
    public void testHasBeenPlayed() {
        assertFalse(c3.hasBeenPlayed(), "Test hasBeenPlayed for three of clubs before played");
        c3.setPlayed(true);
        assertTrue(c3.hasBeenPlayed(), "Test hasBeenPlayed for three of clubs after played");
    }
    
/**
     * Test hasBeenPlayed
     */
    
    @Test
    public void testSetPlayed() {
    
        c3.setPlayed(true);

        assertTrue(c3.hasBeenPlayed(), "Test hasBeenPlayed for three of clubs before played");
        c3.setPlayed(false);
        assertFalse(c3.hasBeenPlayed(), "Test hasBeenPlayed for three of clubs after played");
   
                
    }
       

    /**
     * Test isHeart for three of clubs
     */
    @Test
    public void testIsHeartA() {
        assertFalse(c3.isHeart(), "Test isHeart for three of clubs");
    }

    /**
     * Test isHeart for seven of hearts
     */
    @Test
    public void testIsHeartB() {
        assertTrue(h7.isHeart(), "Test isHeart for seven of hearts");
       
    }

    /**
     * Test toString for three of clubs
     */
    @Test
    public void testToStringA() {
        assertEquals("c3", c3.toString(), "Test toString for three of clubs");
    }

/**
     * Test toString for seven of hearts
     */
    @Test
    public void testToStringB() {
        assertEquals("h7", h7.toString(), "Test toString for seven of hearts");     
        
    }

    /**
     * Test equals for three of clubs
     */
    @Test
    public void testEqualsA() {
        assertTrue(c3.equals(c3), "Test equals for three of clubs");
    }
/**
     * Test equals for three of clubs and seven of hearts
     */

    @Test
    public void testEqualsB() {
        assertFalse(c3.equals(h7), "Test equals for three of clubs and seven of hearts");     
    }

    /**
     * Test equals for three of clubs and two 
     */

    @Test
    public void testEqualsC() {
        Card c2 = new Card('c',2);
        assertFalse(c3.equals(c2), "Test equals for three and two of clubs");      
    }

    /**
     * Test equals for three of clubs and two diff played
     */

    @Test
    public void testEqualsD() {
        Card c2 = new Card('c',2);
        c2.setPlayed(false);
        c3.setPlayed(true);
        assertFalse(c3.equals(c2), "Test equals for three and two of clubs different played");
            
    }

    /**
     * Test compareTo for built in tests
     */
    @Test
    public void testCompareToA() {
        Card s3 = new Card('s', 3);
        assertTrue(c3.compareTo(s3) < 0, "Test compareTo for three of clubs and three of spades");
    }

    /**
     * Test compareTo for built in tests
     */

    @Test
    public void testCompareToB() {
        Card c2 = new Card('c', 2);
        assertTrue(c3.compareTo(c2) > 0,"Test compareTo for three of clubs and two of clubs");
    }

    /**
     * Test compareTo for built in tests
     */

    @Test
    public void testCompareToC() {
        Card h2 = new Card('h', 2);
        assertTrue(h2.compareTo(c3) > 0, "Test compareTo for two of hearts and three of clubs");
    }

    /**
     * Test compareTo for built in tests
     */


    @Test
    public void testCompareToD() {
        Card c4 = new Card('c', 4);
        assertTrue(c3.compareTo(c4) < 0, "Test compareTo for three of clubs and four of clubs");
    }

    /**
     * Test compareTo for built in tests
     */

    @Test
    public void testCompareToE() {
        Card c3Two = new Card('c', 3);
        assertEquals(0, c3.compareTo(c3Two), 
                     "Test compareTo for three of clubs and three of clubs");
    }

    /**
     * Test isQueenOfSpades for three of clubs
     */
    @Test
    public void testIsQueenOfSpadesA() {
        assertFalse(c3.isQueenOfSpades(), "Test isQueenOfSpades for three of clubs");
    }
/**
     * Test isQueenOfSpades for wueen of spades
     */
    
    @Test
    public void testIsQueenOfSpadesB() {
        Card q12 = new Card('s',12);
        assertTrue(q12.isQueenOfSpades(), "Test isQueenOfSpades true");        
    }

    /**
     * Test isQueenOfSpades for nine of spades
     */

    @Test
    public void testIsQueenOfSpadesC() {
        Card s9 = new Card('s', 9);
        assertFalse(s9.isQueenOfSpades(), "Test isQueenOfSpades with a spades");
                
    }

    /**
     * Test isHigherThan for four of clubs and three of clubs
     */

    @Test
    public void testIsHigherA() {
        Card c4 = new Card('c', 4);
        assertTrue(c4.isHigherThan(c3), "Test isHigherThan - four of clubs/three of clubs");        
    }

    /**
     * Test isHigher for three of clubs and four of hearts
     */

    @Test
    public void testIsHigherB() {
        Card h4 = new Card('h', 4);
        assertFalse(h4.isHigherThan(c3), "Test isHigherThan four of hearts/three of clubs");       
    }
/**
     * Test isHigher for three and two of clubs and four of hearts
     */

    @Test
    public void testIsHigherC() {
        Card c2 = new Card('c', 2);
        assertFalse(c2.isHigherThan(c3), "Test isHigherThan two of clubs/three of clubs");       
    }

    /**
     * Tests values for public constants
     */
    @Test
    public void testClassConstants() {
        assertEquals('c', Card.CLUBS, "Test CLUBS constant");
        assertEquals('d', Card.DIAMONDS, "Test DIAMONDS constant");
        assertEquals('s', Card.SPADES, "Test SPADES constant");
        assertEquals('h', Card.HEARTS, "Test HEARTS constant");
        assertEquals(2, Card.LOWEST_VALUE, "Test LOWEST_VALUE constant");
        assertEquals(14, Card.HIGHEST_VALUE, "Test HIGHEST_VALUE constant");
        assertEquals(12, Card.QUEEN_VALUE, "Test QUEEN_VALUE constant");
    }

}