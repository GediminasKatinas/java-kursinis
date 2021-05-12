package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {

    private String name;
    private String owner;
    private String email;
    private ArrayList<Income> income = new ArrayList<Income>();
    private ArrayList<Expenses> expenses = new ArrayList<Expenses>();
    private ArrayList<Category> subCategories = new ArrayList();
    private ArrayList<User> members = new ArrayList();

    public Category(String name) {
        this.name = name;
    }
    public Category() {
    }
    public Category(String name, String owner, String email) {
        this.name = name;
        this.owner = owner;
        this.email = email;
    }

    public ArrayList<Income> getIncome() {
        return income;
    }

    public void setIncome(ArrayList<Income> income) {
        this.income = income;
    }

    public ArrayList<Expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(ArrayList<Expenses> expenses) {
        this.expenses = expenses;
    }

    public Category(ArrayList<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public ArrayList<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(ArrayList<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }


    public void updateCategory(Category category) {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }








}
