package database;

public class WarehouseAdmin extends Admin  {

    private Warehouse my_warehouse;

    public WarehouseAdmin(String name, long mobile,String id, String password,Warehouse my_warehouse) {

        super(name, mobile,id,password,"check",my_warehouse);
        this.my_warehouse=my_warehouse;
    }

    public Warehouse getMy_warehouse() {
        return my_warehouse;
    }

    public void setMy_warehouse(Warehouse my_warehouse) {
        this.my_warehouse = my_warehouse;
    }


}
