package database;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {

    private String name;
    private ArrayList<SubCategory> subCategories;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addSubCategory(SubCategory p) throws SubCategoryException {
        if(subCategories == null) {
            subCategories = new ArrayList<>();
        }
        boolean success = false;
        try {
            searchSubCat(p.getName());
        } catch (SubCategoryException e) {
            success = true;
            subCategories.add(p);
        }
        if(!success) {
            throw new SubCategoryException("SubCategory already exists");
        }
    }

    public SubCategory searchSubCat(String name) throws SubCategoryException {
        for (SubCategory sub : subCategories) {
            if(sub.getName().equals(name)) {
                return sub;
            }
        }
        throw new SubCategoryException("SubCategory not found");
    }

    public boolean deleteSubCat(String name) throws SubCategoryException {
        for(int i = 0; i< subCategories.size(); i++) {
            if(subCategories.get(i).getName().equals(name)) {
                subCategories.remove(i);
                return true;
            }
        }
        throw new SubCategoryException("SubCategory not found");
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SubCategory> getSubCategories() {
        return subCategories;
    }
}
