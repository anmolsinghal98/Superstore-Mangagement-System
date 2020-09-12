package database;

import java.io.Serializable;

public class Product implements Serializable, Comparable<Product> {

    private String name;
    private long id;
    private static long latest_id;
    private long quantity;
    private float fPrice;
    private SubCategory parentSubC;

    // H, K
    private float hPrice;
    private float kPrice;

    public Product(String name, long quantity, float fPrice) {
        this.name = name;
        this.quantity = quantity;
        this.fPrice = fPrice;
        latest_id++;
        id = latest_id;
        hPrice = 0;
        kPrice = 0;
    }

    public Product(String name, long quantity, float fPrice, float hPrice,float kPrice,SubCategory parentSubC) {
        this.name = name;
        this.quantity = quantity;
        this.fPrice = fPrice;
        this.parentSubC = parentSubC;
        latest_id++;
        id = latest_id;
        this.hPrice = hPrice;
        this.kPrice = kPrice;
    }

    public long getId() {
        return id;
    }

    public double calcEOQ(float d) {
        double eoq=Math.sqrt((2*d*kPrice)/hPrice);
        return eoq;
    }

    public void setParentSubC(SubCategory parentSubC) {
        this.parentSubC = parentSubC;
    }

    public String getName() {
        return name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void reduceQuantity(long quant) throws DatabaseException {
        if(quant <= quantity) quantity -= quant;
        else throw new DatabaseException("Insufficient Quantity available");
    }

    public void incQuantity(long quant) {
        quantity += quant;
    }

    public float getfPrice() {
        return fPrice;
    }

    @Override
    public boolean equals(Object o) {
        if(o!=null && getClass().equals(o.getClass())) {
            Product p = (Product) o;
            if(p.getName().equals(((Product) o).getName())){
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }

    public float gethPrice() {
        return hPrice;
    }

    public float getkPrice() {
        return kPrice;
    }

    @Override
    public int compareTo(Product p) {
        return 1;
    }

}
