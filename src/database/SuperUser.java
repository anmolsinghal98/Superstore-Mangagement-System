package database;

public class SuperUser extends User {

    private SuperStore my_store;


    public SuperUser(String name, long mobile, SuperStore my_store) {
        super(name, mobile, "admin", "password","check");
        this.my_store=my_store;
    }

    public void createWarehouse(String name,String location) {

        Warehouse w=new Warehouse(name,location);
        my_store.addWarehouse(w);

    }

    public void createStore(String name,String location) {

        Store s=new Store(name,location);
        my_store.addStore(s);

    }

    public void linkWarehouse(Store s,Warehouse w){
        s.setLinked_warehouse(w);

    }

    public void createWarehouseAdmin(String name,long mobile,String id,String password,Warehouse w) {

        WarehouseAdmin wAdmin=new WarehouseAdmin(name,mobile,id,password,w);
        my_store.addWarehouseAdmin(wAdmin);

    }

    public void createStoreAdmin(String name,long mobile,String id,String password,Store s) {

        StoreAdmin sAdmin=new StoreAdmin(name,mobile,id,password,s);
        my_store.addStoreAdmin(sAdmin);
    }

}
