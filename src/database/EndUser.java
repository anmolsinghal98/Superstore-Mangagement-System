package database;

import java.util.ArrayList;

public class EndUser extends User {

    private Cart cart;
    private Store linkedStore;
    private int funds;
    private ArrayList<Integer> transactions;

    public EndUser(String name, long mobile, String username, String password,String passHint) {
        super(name, mobile, username, password,passHint);
        cart = new Cart(this);
        linkedStore = null;
        transactions=new ArrayList<>();
    }

    public Product accessInventory(Store s) {
        return null;
    }

    public Cart getCart() {
        return cart;
    }

    public void sortCart() {

    }

    public void displayCart() {

    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = this.funds+funds;
    }

    public Store getLinkedStore() {
        return linkedStore;
    }

    public void setLinkedStore(Store linkedStore) {
        this.linkedStore = linkedStore;
        cart.setS(linkedStore);
    }
    public void updateFunds(){
        int amount=cart.getPrice();
        funds=funds-amount;
        transactions.add(amount);
    }

    public ArrayList<Integer> getTransactions() {
        return transactions;
    }
}
