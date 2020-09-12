package database;

import java.io.Serializable;

public class Admin extends User {

    private Place details;

    public Admin(String name, long mobile, String id, String pass,String passHint,Place details){
        super(name,mobile,id,pass,passHint);
        this.details = details;
    }

    public Place getDetails() {
        return details;
    }

    public void setDetails(Place details) {
        this.details = details;
    }

    public void addCategory(Category c) throws CategoryException {
        details.addCategory(c);
        System.out.println(c.getName());
    }


    public void addSubCategory(SubCategory c) throws SubCategoryException {

        for(int i = 0; i< Main.getMySuperStore().getCategories().size(); i++){
            if(c.getParentCat().getName().equals(Main.getMySuperStore().getCategories().get(i).getName())){
                Main.getMySuperStore().getCategories().get(i).addSubCategory(c);
                break;
            }
        }
        System.out.println(c.getName());

    }
    public void addProduct(Product pr,Category cat,SubCategory sub) throws ProductException, CategoryException, SubCategoryException {

        for(int i = 0; i< details.getProducts().size(); i++){
            if(details.getProducts().get(i).getName().equals(pr.getName())){
                throw new ProductException("Product Already Exists");
            }
        }
        details.getProducts().add(pr);
        Category c=searchCategory(cat.getName());
        if(c!=null){
            SubCategory s=c.searchSubCat(sub.getName());
            if(s!=null){
                s.addProduct(pr);
            }
        }
        System.out.println(pr.getName());
    }

    public void modifyCategory(Category cat,String n) throws CategoryException {

        Category c=searchCategory(cat.getName());
        if(c!=null){
            c.setName(n);
        }
    }
    public void modifySubCategory(Category cat,SubCategory sub,String n) throws CategoryException, SubCategoryException {

        Category c=searchCategory(cat.getName());
        if(c!=null){
            SubCategory s=c.searchSubCat(sub.getName());
            if(s!=null){
                s.setName(n);
            }
        }
    }

    public void modifyProduct(String cat,String sub,String pr,Product n) throws CategoryException, SubCategoryException {

        for(int i = 0; i< details.getProducts().size(); i++){
            if(details.getProducts().get(i).getName().equals(pr)){
                details.getProducts().set(i,n);
            }
        }
        Category c=searchCategory(cat);
        if(c!=null){
            SubCategory s=c.searchSubCat(sub);
            for(int j=0;j<s.getProducts().size();j++){
                if(s.getProducts().get(j).getName().equals(pr)){
                    s.getProducts().set(j,n);
                }
            }
        }

    }
    public void deleteCategory(String cat) throws CategoryException {

        for(int i = 0; i< Main.getMySuperStore().getCategories().size(); i++){
            if(Main.getMySuperStore().getCategories().get(i).getName().equals(cat)){
                Main.getMySuperStore().getCategories().remove(i);
                return;
            }
        }
        throw new CategoryException("Category Not Found");

    }
    public void deleteSubCategory(String cat,String sub) throws CategoryException, SubCategoryException {

        Category c=searchCategory(cat);
        if(c!=null){
            for(int i=0;i<c.getSubCategories().size();i++){
                if(c.getSubCategories().get(i).getName().equals(sub)){
                    c.getSubCategories().remove(i);
                    return;
                }
            }
        }
        throw new SubCategoryException("SubCategoryNotFound");
    }
    public void deleteProduct(String cat,String sub,String pr) throws CategoryException, SubCategoryException, ProductException {

//        Category c=searchCategory(cat);
//        if(c!=null){
//            SubCategory s=c.searchSubCat(sub);
//            if(s!=null){
//                for(int i=0;i<s.getProducts().size();i++){
//                    if(s.getProducts().get(i).getName().equals(pr)){
//                        s.getProducts().remove(i);
//                        break;
//                    }
//                }
//            }
//        }
        for(int j = 0; j< details.getProducts().size(); j++){
            if(details.getProducts().get(j).getName().equals(pr)){
                details.getProducts().remove(j);
                return;
            }
        }
        throw new ProductException("Product Does Not Exist");

    }
    public Category searchCategory(String c) throws CategoryException {
        for(int i = 0; i< Main.getMySuperStore().getCategories().size(); i++){
            if(Main.getMySuperStore().getCategories().get(i).getName().equals(c)){
                return Main.getMySuperStore().getCategories().get(i);
            }
        }
        throw new CategoryException("Category Not Found");
    }



}
