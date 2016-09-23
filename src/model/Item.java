/**
 * TCSS 305 – Winter 2016. 
 * Assignment 2 - Shopping cart
 */
package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * This class stores information about an individual item. 
 * @author Hsin-Jung Wang (Cindy)
 * @version 4.0
 */
public final class Item {
    
    /**
     * This instant variable stores the name of an individual item. 
     */
    private final String myName;
    
    /**
     * This instant variable stores the price of an individual item. 
     */
    private final BigDecimal myPrice;
    
    /**
     * This instant variable stores the total bulk quantity of the item.
     */
    private int myQuantity;
    
    /**
     * This instant variable stores the overall price for a bulk quantity. 
     */
    private BigDecimal myBulkPrice; 
    
    /** 
     * Initiate a constructor that takes a name and a price arguments.
     * @param theName stores the name of an individual item. 
     * @param thePrice stores the price of an individual item. 
     * @throws IllegalArgumentException if the product price is less than 0 
     * or if there is no product name. 
     * @throws NullPointerException if the product price or the product name is null.
     */
    public Item(final String theName, final BigDecimal thePrice) {
        final String name = Objects.requireNonNull(theName);
        final BigDecimal price = Objects.requireNonNull(thePrice);
        if (name.length() == 0 || price.doubleValue() < 0) {
            throw new IllegalArgumentException();
        }
        myName = theName;
        myPrice = thePrice;
    }

    /**
     * Initiate a constructor that takes a name, a single-item price, a bulk quantity, 
     * and a bulk price as arguments.
     * @param theName stores the name of an individual item. 
     * @param thePrice stores the price of an individual item.
     * @param theBulkQuantity stores the overall quantity of an item. 
     * @param theBulkPrice stores the overall price of the bulk quantity of a particular item.
     * @throws IllegalArgumentException either if the product price, the bulk price,
     * or the bulk quantity is less than 0, or if there is no product name. 
     * @throws NullPointerException if the product price, the bulk price, 
     * or the product name is null. 
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {
        this(theName, thePrice);
        final BigDecimal bulkPrice = Objects.requireNonNull(theBulkPrice);
        if (theBulkQuantity < 0 || bulkPrice.doubleValue() < 0) {
            throw new IllegalArgumentException();
        }
        myQuantity = theBulkQuantity;
        myBulkPrice = bulkPrice;
    }

    /**
     * @return the single item price for the item.
     */
    public BigDecimal getPrice() {
        return myPrice;
    }
    
    /**
     * @return the bulk quantity for the item.
     */
    public int getBulkQuantity() {
        return myQuantity; 
    }
    
    /**
     * @return the bulk price for the item.
     */
    public BigDecimal getBulkPrice() {
        return myBulkPrice;
    }

    /**
     * @return True if the item has bulk pricing; false otherwise.
     */
    public boolean isBulk() {
        return myBulkPrice != null; 
    }

    /**
     * Returns a String representation of the Item: name, followed by a comma 
     * and a space, followed by price. 
     * If the item has a bulk price, then indicates it with a parenthesized description
     * of the bulk pricing and bulk quantity. 
     */
    @Override
    public String toString() {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        final StringBuffer itemDescription = new StringBuffer();
        itemDescription.append(myName);
        itemDescription.append(", ");
        itemDescription.append(nf.format(myPrice.doubleValue())); 
        if (myBulkPrice != null)  {
            itemDescription.append(" (" + myQuantity
                            + " for " + nf.format(myBulkPrice.doubleValue()) + ")");
        }
        return itemDescription.toString(); 
    }

    /**
     * Returns true if the specified object is equivalent to this Item, and false otherwise. 
     */
    @Override
    public boolean equals(final Object theOther) {
        boolean checkClassType = false;
        if (theOther != null) {
            if (this == theOther) {
                checkClassType = true;
            } else if (this.getClass() == theOther.getClass()) {
                final Item tempItem = (Item) theOther;
                checkClassType =  (myPrice.equals(tempItem.myPrice)) 
                              && (myQuantity == tempItem.myQuantity) 
                              && (myBulkPrice.equals(tempItem.myBulkPrice))
                              && (myName.equals(tempItem.myName));
            }
        }
        return checkClassType;
    }
    
    /**
     * Returns an integer hash code for this item. 
     */
    @Override
    public int hashCode() {
        return Objects.hash(myName, myPrice, myQuantity);
    }

}
