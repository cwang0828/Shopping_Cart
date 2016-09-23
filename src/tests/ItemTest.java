/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import model.Item;

import org.junit.Before;
import org.junit.Test;

/**
 * This program provides a set of unit tests for the Item class.
 * @author Hsin-Jung Wang (Cindy)
 * @version 4.0
 */

public class ItemTest {

    /**
     * The variable for the tolerance value (or delta).
     */
    private static final double TOL = 0.0000;
    
    /**
     * The variable for the item name.
     */
    private static final String NAME = "Silly Putty";
    
    /**
     * The variable for the item price.
     */
    private static final double PRICE = 4.41;
    
    /**
     * The variable for the item bulk price.
     */
    private static final double BULKPRICE = 10.04;
    
    /**
     * The variable for the item bulk quantity. 
     */
    private static final int QUANTITY = 6;
    
    /**
     * An instant variable for the item class (for nonMember).
     */
    private Item myItem1;
    
    /**
     * An instant variable for the item class (for Member).
     */
    private Item myItem2;
    
    /**
     * A default constructor.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() {
        myItem1 = new Item(NAME, BigDecimal.valueOf(PRICE));
        myItem2 = new Item(NAME, BigDecimal.valueOf(PRICE), 
                           QUANTITY, BigDecimal.valueOf(BULKPRICE));
    }

    /**
     * To compare whether the hashCode for the expected and actual are the same.
     * Test method for {@link model.Item#hashCode()}.
     */
    @Test
    public void testHashCode() {
        myItem1 = new Item(NAME, BigDecimal.valueOf(PRICE), 
                           QUANTITY, BigDecimal.valueOf(BULKPRICE));
        assertEquals(myItem1.hashCode(), 
                     Objects.hash(NAME, BigDecimal.valueOf(PRICE), QUANTITY));
        
    }

    /**
     * Test the default item class for nonMembers.
     * Test method for {@link model.Item#Item(java.lang.String, java.math.BigDecimal)}.
     */
    @Test
    public void testItemStringBigDecimal() {
        myItem1 = new Item(NAME, BigDecimal.valueOf(PRICE));
    }

    /**
     * Test the default item class for members.
     * Test method for {@link model.Item#Item
     * (java.lang.String, java.math.BigDecimal, int, java.math.BigDecimal)}.
     */
    @Test
    public void testItemStringBigDecimalIntBigDecimal() {
        myItem2 = new Item(NAME, BigDecimal.valueOf(PRICE), 
                           QUANTITY, BigDecimal.valueOf(BULKPRICE));
    }

    /**
     * This method checks if having a name with length equals to
     * zero would throw a new IllegalArgumentException. 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testZeroNameLength() { 
        myItem2 = new Item("", BigDecimal.valueOf(PRICE), 
                          QUANTITY, BigDecimal.valueOf(BULKPRICE));
    }
    
    /**
     * This method checks if having a negative price for the item
     * would throw a new IllegalArgumentException. 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativePrice() { 
        final BigDecimal negativePrce = BigDecimal.valueOf(-1.0);
        myItem2 = new Item(NAME, negativePrce, 
                          QUANTITY, BigDecimal.valueOf(BULKPRICE));
    }
    
    /**
     * This method checks if having a negative bulk price for the item
     * would throw a new IllegalArgumentException. 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeBulkPrice() { 
        final BigDecimal negativePrce = BigDecimal.valueOf(-1.0);
        myItem2 = new Item(NAME, BigDecimal.valueOf(PRICE), QUANTITY, negativePrce);
    }
    
    /**
     * This method checks if having a negative bulk quantity
     * would throw a new IllegalArgumentException. 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeQuantity() { 
        final int negativeQuantity = -1;
        myItem2 = new Item(NAME, BigDecimal.valueOf(PRICE), 
                           negativeQuantity, BigDecimal.valueOf(BULKPRICE));
    }
    
    /**
     * Test the getter for the item's price
     * when the price is 4.41.
     * Test method for {@link model.Item#getPrice()}.
     */
    @Test
    public void testGetPrice() {
        assertEquals(BigDecimal.valueOf(PRICE), myItem1.getPrice());
    }

    /**
     * Test the getter for the item's bulk quantity
     * when the bulk quantity is 6.
     * Test method for {@link model.Item#getBulkQuantity()}.
     */
    @Test
    public void testGetBulkQuantity() {
        assertEquals(QUANTITY, myItem2.getBulkQuantity(), TOL);
    }

    /**
     * Test the getter for the item's bulk price
     * when the price is 10.04.
     * Test method for {@link model.Item#getBulkPrice()}.
     */
    @Test
    public void testGetBulkPrice() {
        assertEquals(BigDecimal.valueOf(BULKPRICE), myItem2.getBulkPrice());
    }

    /**
     * Test when the order could receive a bulk price. 
     * Test method for {@link model.Item#isBulk()}.
     */
    @Test
    public void testIsBulk() {
        assertTrue(myItem2.isBulk());
    }

    /**
     * Test when the order could not receive a bulk price.
     * Test method for {@link model.Item#isBulk()}.
     */
    @Test
    public void testIsNotBulk() {
        assertFalse(myItem1.isBulk());
    }
    
    /**
     * Test when the item has a bulk price
     * and when it does not have a bulk price. 
     * Test method for {@link model.Item#toString()}.
     */
    @Test
    public void testToString() {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        final StringBuffer itemString = new StringBuffer(); 
        itemString.append(NAME);
        itemString.append(", ");
        itemString.append(nf.format(PRICE));
        assertEquals(itemString.toString(), myItem1.toString());

        itemString.append(" (");
        itemString.append(QUANTITY);
        itemString.append(" for ");
        itemString.append(nf.format(BULKPRICE));
        itemString.append(')');
        assertEquals(itemString.toString(), myItem2.toString());
    }

    /**
     * Test when the toString is null. 
     * Test method for {@link model.Item#toString()}.
     */
    @Test
    public void testNullToString() {
        assertEquals("Silly Putty, $4.41 (6 for $10.04)", 
                     myItem2.toString());
    }
    
    
    /**
     * Test when the classes are and are not equal. 
     * Test when the classes are and are not null. 
     * Test when the prices of the item are and are not the same.
     * Test when the bulk quantities of the item are and are not the same. 
     * Test when the bulk prices of the item are and are not the same.
     * Test when the names of the item are and are not the same. 
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
        final Item testClass = new Item(NAME, BigDecimal.valueOf(PRICE), 
                                        QUANTITY, BigDecimal.valueOf(BULKPRICE));
        assertEquals(testClass , testClass);
        assertEquals(testClass , myItem2);
        assertNotEquals(testClass, myItem1);
        assertFalse(testClass == null);
        assertFalse(testClass.equals(new Object()));
        assertFalse(testClass.equals(new Item(NAME, BigDecimal.valueOf(PRICE * 2),
                                              QUANTITY, BigDecimal.valueOf(BULKPRICE))));
        assertFalse(testClass.equals(new Item(NAME, BigDecimal.valueOf(PRICE), 
                                              QUANTITY * 2, BigDecimal.valueOf(BULKPRICE))));
        assertFalse(testClass.equals(new Item(NAME, BigDecimal.valueOf(PRICE),
                                              QUANTITY, BigDecimal.valueOf(BULKPRICE * 2))));
        assertFalse(testClass.equals(new Item(" ", BigDecimal.valueOf(PRICE), 
                                              QUANTITY, BigDecimal.valueOf(BULKPRICE))));
    }

}

