/**
 * TCSS 305 – Winter 2016. 
 * Assignment 2 - Shopping cart
 */

package model;

import java.util.Objects;

/**
 * This class stores information about a purchase for a particular item. 
 * @author Hsin-Jung Wang (Cindy)
 * @version 4.0
 */
public final class ItemOrder {
    
    /**
     * This instant variable stores a reference to the item itself. 
     */
    private final Item myItem;
    
    /**
     * This instant variable stores the quantity of the item being purchased. 
     */
    private final int myQuantity; 

    /**
     * Initiate a constructor that creates an item order 
     * for the given quantity of the given Item.
     * @param theItem is the item being purchased for a particular purchase order. 
     * @param theQuantity is the amount being purchased for a particular purchase order.
     * @throws IllegalArgumentException if the quantity of the item order is less than 0.  
     * @throws NullPointerException if the item in the item order is null.
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        final Item item = Objects.requireNonNull(theItem);
        if (theQuantity < 0) {
            throw new IllegalArgumentException();
        }
        myItem = item; 
        myQuantity = theQuantity;
    }

    /**
     * @return a reference to the Item in this ItemOrder.
     */
    public Item getItem() {
        return myItem;
    }
    
    /**
     * @return the quantity for this ItemOrder.
     */
    public int getQuantity() {
        return myQuantity;
    }

    /**
     * Returns a String representation of this ItemOrder. 
     */
    @Override
    public String toString() {
        return myItem + "\t" + myQuantity;
    }

}
