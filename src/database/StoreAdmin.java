package database;

import java.io.Serializable;

public class StoreAdmin extends Admin {

    private Store my_store;

    public StoreAdmin(String name, long mobile,String id,String password,Store my_store) {

        super(name, mobile,id,password,"check",my_store);
        this.my_store=my_store;
    }

    public Store getMy_store() {
        return my_store;
    }

    public void setMy_store(Store my_store) {
        this.my_store = my_store;
    }
}
