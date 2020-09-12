package database;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {

    private ArrayList<Product> products;
    private ArrayList<Long> quantity;
    private int price;
    Store s;
    EndUser user;

    Cart(EndUser user) {
        price = 0;
        products=new ArrayList<>();
        quantity=new ArrayList<>();
        this.user=user;
    }

    public void addToCart(Product p, long quant) {
        if(products == null) products = new ArrayList<>();
        if(quantity == null) quantity = new ArrayList<>();
        try {
            p.reduceQuantity(quant);
            products.add(p);
            quantity.add(quant);
            price += p.getfPrice() * quant;
        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean removeFromCart(String p) throws ProductException {
        for (int i=0; i<products.size(); i++) {
            if(products.get(i).getName().equals(p)) {
                Product pr=s.searchProduct(p);
                pr.incQuantity(quantity.get(i));
                price-=products.get(i).getfPrice();
                products.remove(i);
                quantity.remove(i);
                return true;
            }
        }
        return false;
    }

    public void checkout() throws DatabaseException, ProductException {
        if(price>user.getFunds()){
            throw new DatabaseException("Insufficient Funds Available");
        }
        else{
            for(int i=0;i<products.size();i++){
                Product p=products.remove(i);
                s.checkEOQLevel(p);
            }
            products = null;
            quantity = null;
            s.setRevenue(s.getRevenue()+price);
            user.updateFunds();
        }

    }

    public int getPrice() {
        return price;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Long> getQuantity() {
        return quantity;
    }

    public Store getS() {
        return s;
    }

    public void setS(Store s) {
        this.s = s;
    }
}
