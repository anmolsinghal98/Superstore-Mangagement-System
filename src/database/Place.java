package database;

import java.io.Serializable;
import java.util.ArrayList;

public class Place implements Serializable {

    private LocDetails details;
    private ArrayList<Product> products;
    private int revenue;


    public Place(String name,String loc){
        details=new LocDetails(name,loc);
        products=new ArrayList<>();
    }

    public void addProduct(Product p) {

        if(products == null) {
            products = new ArrayList<>();
        }
        products.add(p);
    }

    public void addCategory(Category c) throws CategoryException {
        if (Main.getMySuperStore().getCategories() == null){
            Main.getMySuperStore().setCategories(new ArrayList<>());
        }
        for(Category cat : Main.getMySuperStore().getCategories()){
            if(cat.getName().equals(c.getName())){
                throw new CategoryException("Category Already Exists");
            }
        }
        Main.getMySuperStore().getCategories().add(c);
    }

    public Product searchProduct(String name) throws ProductException {

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                return products.get(i);
            }
        }
        throw new ProductException("Product not found");
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public LocDetails getDetails() {
        return details;
    }

    public void setDetails(LocDetails details) {
        this.details = details;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
}
