package database;

import java.io.Serializable;
import java.util.ArrayList;

public class SuperStore implements Serializable {

    private final SuperUser superUser;
    private ArrayList<EndUser> enduser_list;
    private ArrayList<WarehouseAdmin> WarehouseAdmin_list;
    private ArrayList<StoreAdmin> StoreAdmin_list;
    private ArrayList<Store> store_list;
    private ArrayList<Warehouse> Warehouse_list;
    private ArrayList<Category> categories;


    public SuperStore(String name, long mobile) {

        superUser = new SuperUser(name, mobile,this);
        enduser_list = new ArrayList<EndUser>();
        WarehouseAdmin_list=new ArrayList<WarehouseAdmin>();
        StoreAdmin_list=new ArrayList<StoreAdmin>();
        store_list =new ArrayList<Store>();
        Warehouse_list=new ArrayList<Warehouse>();
        categories=new ArrayList<Category>();
    }

    public SuperUser getSuperUser() {
        return superUser;
    }

    public ArrayList<StoreAdmin> getStoreAdmin_list() {
        return StoreAdmin_list;
    }

    public void addStoreAdmin(StoreAdmin s) {
        if(StoreAdmin_list == null) {
            StoreAdmin_list = new ArrayList<>();
        }
        StoreAdmin_list.add(s);
    }

    public ArrayList<EndUser> getEnduser_list() {
        return enduser_list;
    }

    public void addEnduser(EndUser e) {
        if(enduser_list == null) {
            enduser_list = new ArrayList<>();
        }
        enduser_list.add(e);
    }

    public ArrayList<WarehouseAdmin> getWarehouseAdmin_list() {
        return WarehouseAdmin_list;
    }

    public void addWarehouseAdmin(WarehouseAdmin w) {
        if(WarehouseAdmin_list == null) {
            WarehouseAdmin_list = new ArrayList<>();
        }
        WarehouseAdmin_list.add(w);
    }

    public void addWarehouse(Warehouse w){
        if(Warehouse_list==null){
            Warehouse_list=new ArrayList<>();
        }
        Warehouse_list.add(w);
    }

    public void addStore(Store s){
        if(store_list ==null){
            store_list =new ArrayList<>();
        }
        store_list.add(s);
    }

    public WarehouseAdmin searchWarehouseAdmin(String username) {
        for (int i=0; i<WarehouseAdmin_list.size(); i++) {
            if(WarehouseAdmin_list.get(i).getCredentials().getId().equals(username)) {
                return WarehouseAdmin_list.get(i);
            }
        }
        return null;
    }

    public StoreAdmin searchStoreAdmin(String username) {
        for (int i=0; i<StoreAdmin_list.size(); i++) {
            if(StoreAdmin_list.get(i).getCredentials().getId().equals(username)) {
                return StoreAdmin_list.get(i);
            }
        }
        return null;
    }

    public EndUser searchEndUser(String username) {
        for (int i=0; i<enduser_list.size(); i++) {
            if(enduser_list.get(i).getCredentials().getId().equals(username)) {
                return enduser_list.get(i);
            }
        }
        return null;
    }

    public Store searchStore(String name) throws DatabaseException {
        for (int i=0; i<store_list.size(); i++) {
            if(store_list.get(i).getDetails().getName().equals(name)) {
                return store_list.get(i);
            }
        }
        throw new DatabaseException("Store not found");
    }

    public Warehouse searchWarehouse(String name) throws DatabaseException {
        for (int i=0; i<getWarehouse_list().size(); i++) {
            if( Warehouse_list.get(i).getDetails().getName().equals(name)) {
                return Warehouse_list.get(i);
            }
        }
        throw new DatabaseException("Store not found");
    }

    public ArrayList<Store> getStore_list() {
        return store_list;
    }

    public ArrayList<Warehouse> getWarehouse_list() {
        return Warehouse_list;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> category) {
        categories = category;
    }

    public Category searchCategory(String c) throws CategoryException {
        for(int i = 0; i< getCategories().size(); i++){
            if(getCategories().get(i).getName().equals(c)){
                return getCategories().get(i);
            }
        }
        throw new CategoryException("Category Not Found");
    }

    public static void optimal_warehouse(Product pr,int eoq) throws ProductException, DatabaseException {

        int min=Integer.MAX_VALUE;
        int index=0;
        for(int i=0;i<Main.getMySuperStore().getWarehouse_list().size();i++){
            Product p=Main.getMySuperStore().getWarehouse_list().get(i).searchProduct(pr.getName());
            int val=(int)p.gethPrice()*eoq;
            if(val<=min){
                index=i;
                min=val;
            }
        }
        Product p=Main.getMySuperStore().getWarehouse_list().get(index).searchProduct(pr.getName());
        p.reduceQuantity(eoq);
    }
}