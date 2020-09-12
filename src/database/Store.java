package database;

import java.io.Serializable;
import java.util.ArrayList;

public class Store extends Place {

    private Warehouse linked_warehouse;
    private ArrayList<Double> eoq_list;
    private long maxQuantity;
    private float dPrice;

    public Store(String name,String location){

        super(name,location);
        eoq_list=new ArrayList<>();
    }

    public Store(String name,String location, long maxQuantity, float dPrice){

        super(name,location);
        this.maxQuantity = maxQuantity;
        this.dPrice = dPrice;
        eoq_list=new ArrayList<>();
    }

    public float getdPrice() {
        return dPrice;
    }

    public void setdPrice(float dPrice) {
        this.dPrice = dPrice;
        calcEOQlist();
    }

    private void calcEOQlist() {
        for(int i=0;i<getProducts().size();i++){
            double eoq=getProducts().get(i).calcEOQ(dPrice);
            eoq_list.add(eoq);
        }
    }


    public void setLinked_warehouse(Warehouse w) {
        this.linked_warehouse=w;
    }




    public long getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(long maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public void checkEOQLevel(Product p) throws ProductException, DatabaseException {
        int eoq=(int) p.calcEOQ(dPrice);
        Product pr=searchProduct(p.getName());
        if(pr.getQuantity()<eoq){
            this.orderItems(pr,eoq);
        }

    }

    public void orderItems(Product pr,int eoq) throws ProductException, DatabaseException {
        boolean flag=false;
        Product searchProduct=linked_warehouse.searchProduct(pr.getName());
        if(searchProduct!=null){
            if(searchProduct.getQuantity()>=eoq){
                searchProduct.reduceQuantity(eoq);
                pr.incQuantity(eoq);
                flag=true;
            }
        }
        if(!flag){
            SuperStore.optimal_warehouse(pr,eoq);
            pr.incQuantity(eoq);
        }
    }



}
