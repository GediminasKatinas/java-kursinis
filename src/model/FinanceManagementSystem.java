package model;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class FinanceManagementSystem implements Serializable {
    private String company;
    private LocalDate dateCreated;
    private String systemversion;
    private ArrayList<Category> categories = new ArrayList<Category>();



    public FinanceManagementSystem()  {

    }



    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getSystemversion() {
        return systemversion;
    }

    public void setSystemversion(String systemversion) {
        this.systemversion = systemversion;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public void overWriteSystem(Category importedcat) {
        this.categories = importedcat.getSubCategories();
    }
}
