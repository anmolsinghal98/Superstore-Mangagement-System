package database;

import java.io.Serializable;
import java.util.ArrayList;

public class SubCategory implements Serializable {

    private String name;
    private ArrayList<Product> products;
    private Category parentCat;

    public SubCategory(String name, Category parentCat) {
        this.name = name;
        this.parentCat = parentCat;
    }

    public void setParentCat(Category parentCat) {
        this.parentCat = parentCat;
    }

    public Category getParentCat() {
        return parentCat;
    }

    public void addProduct(Product p) {
        if(products == null) {
            products = new ArrayList<>();
        }
        products.add(p);
    }

    public String getName() {
        return name;
    }

    public boolean deleteProduct(Product p){
        return false;
    }

    public Product searchProduct(String name) throws ProductException {
        for(int i=0;i<getProducts().size();i++){
            if(name.equals(getProducts().get(i).getName())){
                return getProducts().get(i);
            }
        }
        throw new ProductException("Product Not Found");
    }


    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
