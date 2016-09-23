/**
 * TCSS 305 – Winter 2016. 
 * Assignment 2 - Shopping cart
 */

package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class stores information about the customer's overall purchase. 
 * @author Hsin-Jung Wang (Cindy)
 * @version 4.0
 */
public class ShoppingCart {
    
    /**
     * A list that stores all the purchases from the customers. 
     */
    private final List<ItemOrder> myOrderList;
   
    /**
     * An instant variable that checks whether a customer has an 
     * existing membership or not. 
     */
    private boolean myMember; 
    
    /**
     * Initiates a constructor that creates an empty shopping cart.
     */
    public ShoppingCart() {
        myOrderList = new ArrayList<ItemOrder>();
        myMember = false; 
    }

    /**
     * Adds an order to the shopping cart, replacing any 
     * previous order for an equivalent item with the new order. 
     * @param theOrder is a new order that is being added to the shopping cart.
     * @throws NullPointerException if the item order is null.
     */
    public void add(final ItemOrder theOrder) {
        final ItemOrder order = Objects.requireNonNull(theOrder);
        final int index = replaceItemIndex(order);
        if (index > -1) {
            myOrderList.set(index, order);
        } else {
            myOrderList.add(order);
        }
    }

    /**
     * Checks whether there is an existing order that is the 
     * same as the new order. If so, returns the index that stores the
     * existing order. Otherwise, returns the last index of the list 
     * (default value of the index). 
     * @param theOrder is a new order that is being added to the shopping cart.
     * @return the index that contains an existing order that is equivalent 
     * to the new order. 
     */
    private int replaceItemIndex(final ItemOrder theOrder) { 
        int index = -1;
        for (int i = 0; i < myOrderList.size(); i++) {
            if (myOrderList.get(i).getItem().equals(theOrder.getItem())) {
                index = i;
                break;
            }
        }
        return index;
    }
    
    /**
     * Sets whether or not the customer for this shopping cart 
     * has a store membership.
     * @param theMembership is a type boolean indicating membership. 
     */
    public void setMembership(final boolean theMembership) {
        myMember = theMembership; 
    }

    /**
     * @return the total cost of this shopping cart as a BigDecimal.
     */
    public BigDecimal calculateTotal() {
        BigDecimal total = new BigDecimal("0.00");
        for (int i = 0; i < myOrderList.size(); i++) {
            final int tempTotalQuantity = myOrderList.get(i).getQuantity();
            BigDecimal tempRegularPrice = myOrderList.get(i).getItem().getPrice();
            final int tempBulkQuantity = myOrderList.get(i).getItem().getBulkQuantity();
            if (myMember && tempBulkQuantity != 0) {
                final int numBulkSet = tempTotalQuantity / tempBulkQuantity;
                final int remainder = tempTotalQuantity % tempBulkQuantity;
                BigDecimal tempBulkPrice = myOrderList.get(i).getItem().getBulkPrice();
                tempBulkPrice = tempBulkPrice.multiply(new BigDecimal(numBulkSet));
                tempRegularPrice = tempRegularPrice.multiply(new BigDecimal(remainder));
                total = total.add(tempBulkPrice.add(tempRegularPrice));
            } else {
                tempRegularPrice = tempRegularPrice.multiply
                                (new BigDecimal(tempTotalQuantity));
                total = total.add(tempRegularPrice);
            }
        }
        return total.setScale(2, BigDecimal.ROUND_HALF_EVEN); 
    }
    
    /**
     * Removes all orders from the cart. The return is void.
     */
    public void clear() {
        myOrderList.clear();
    }

    /**
     * Returns a String representation of this ShoppingCart.
     */
    @Override
    public String toString() {
        final StringBuffer shoppingCartList = new StringBuffer();
        for (int i = 0; i < myOrderList.size(); i++) {
            shoppingCartList.append(myOrderList.get(i).getItem());
            shoppingCartList.append('\t');
            shoppingCartList.append(myOrderList.get(i).getQuantity());
            shoppingCartList.append('\n');
        }
        return shoppingCartList.toString();
    }

}
