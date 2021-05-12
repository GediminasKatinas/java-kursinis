package controller;

import model.*;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class CategoryManagement implements Serializable {


    public static void manageCategories(Scanner scanner, FinanceManagementSystem fmis,User user,Category category){
 String catCmd = "";
  while(!catCmd.equals("exit")){
      System.out.println("Choose an action\n"+
              "\t createCat - create category\n"
      +"\t remCat - remove category \n"
              + "\t createSub - Create subCategory\n"
              + "\t removeSub - Remove subCategory\n"
              + "\t editCat - Edit Category\n"
              +"\t listCat - list all categories \n"
              +"\t checkCat- check a specific category \n"
              +"\t catFinances- enter financial management of a category \n"
      +"\t exit \n");
      catCmd = scanner.next();

      switch(catCmd){
          case "createCat":
              createCategory(scanner,user,fmis,category);
              break;
          case "remCat":
              remCategory(scanner,user,fmis,category);
              break;
          case "createSub":
              createSubCategory(scanner, user,fmis, category);
              break;
          case "removeSub":
              removeSubCategory(scanner,user,fmis,category);
              break;
          case "editCat":
              editCategory(scanner, user,fmis,category);
              break;

          case "listCat":
              checkingCategory(scanner,user,fmis,category);
              break;
          case "checkCat":
                checkingSpecificCat(scanner,user,fmis,category);
              break;
          case "catFinances":
              FinancialManagement.catFinances(scanner,user,fmis,category);
              break;
          case "exit":
              break;
          default:
              System.out.println("enter something\n");

      }
  }
    }
    public static void createCategory(Scanner scanner, User user,FinanceManagementSystem fmis,Category category){

        System.out.println("Enter category info:{name};{email};\n");
        String[] values = scanner.next().split(";");
        fmis.getCategories().add(new Category(values[0],user.getLogin(), values[1]));

    }

    public static void remCategory(Scanner scanner, User user,FinanceManagementSystem fmis,Category category){
        System.out.println("If you would like to delete category, type categoryname");
        String todeleteComp = scanner.next();
        fmis.getCategories().removeIf(Category -> Category.getName().equals(todeleteComp));
        System.out.println("Deleted successfully");

    }
    public static void createSubCategory(Scanner scanner, User user,FinanceManagementSystem fmis,Category category) {

        System.out.println("Choose category you would like to add the subCategory to");
        String toAddSubComp = scanner.next();
        int index = 0;
        for (int i = 0; i < fmis.getCategories().size(); i++) {
            if (fmis.getCategories().get(i).getName().equals(toAddSubComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            System.out.println("the category doesnt exist");
            CategoryManagement.manageCategories(scanner,fmis,user,category);

        }
        System.out.println("Enter category info:{name};{email};\n");
        String[] values = scanner.next().split(";");
        fmis.getCategories().get(index).getSubCategories().add(new Category( values[0],user.getLogin(), values[1]));
        System.out.println("subCategory added");
    }
    public static void removeSubCategory(Scanner scanner, User user,FinanceManagementSystem fmis,Category category) {

        System.out.println("Choose category you would like to remove the subCategory from");
        String toRemoveSubComp = scanner.next();
        int index = 0;
        for (int i = 0; i < fmis.getCategories().size(); i++) {
            if (fmis.getCategories().get(i).getName().equals(toRemoveSubComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            System.out.println("subCategory doesnt exist");
            CategoryManagement.manageCategories(scanner,fmis,user,category);
        }
        System.out.println("Enter subcategory info to remove:{name};\n");
        String[] values = scanner.next().split(";");
        fmis.getCategories().get(index).getSubCategories().removeIf(Category -> Category.getName().equals(values[0]));
        System.out.println("subCategory removed");
    }
    public static void editCategory(Scanner scanner, User user,FinanceManagementSystem fmis,Category category) {

        System.out.println("Choose the cateogry you would like to edit");
        String toEditComp = scanner.next();
        int index = 0;
        for (int i = 0; i < fmis.getCategories().size(); i++) {
            if (fmis.getCategories().get(i).getName().equals(toEditComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            System.out.println("Category doesnt exist");
            CategoryManagement.manageCategories(scanner,fmis,user,category);
        }
        String chooseOne = "";
        while (!chooseOne.equals("exit")) {
            System.out.println("Choose what you want to edit in this category :\n"
                    + "\t Name - the name of the category\n"
                    + "\t Email - the email that the category is registered to\n"
                    + "\t exit\n");
            chooseOne = scanner.next();


            switch (chooseOne) {
                case "Name":
                    System.out.println("Enter new Name: ");
                    String Name = "";
                    Name = scanner.next();
                    fmis.getCategories().get(index).setName(Name);
                    break;
                case "Email":
                    System.out.println("Enter new email: ");
                    String Email = "";
                    Email = scanner.next();
                    fmis.getCategories().get(index).setEmail(Email);
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("choose");
            }
        }
        System.out.println("the changes are in full effect");
    }





    public static void checkingCategory(Scanner scanner, User user,FinanceManagementSystem fmis,Category category) {
        fmis.getCategories().forEach(Category -> {
            System.out.println(Category.getName() + " created by user " + Category.getOwner());
            for (int i = 0; i < Category.getSubCategories().size(); i++) {
                System.out.println("   " + Category.getSubCategories().get(i).getName());
            }
        });
    }
    private static void checkingSpecificCat(Scanner scanner, User user,FinanceManagementSystem fmis,Category category) {
        System.out.println("enter the category name of which u want to get info ");
        String toCheckComp = scanner.next();
        int index = 0;
        for (int i = 0; i < fmis.getCategories().size(); i++) {
            if (fmis.getCategories().get(i).getName().equals(toCheckComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            System.out.println("the category doesnt exist");
        }
        System.out.println("category info:\n");
        Category specat = fmis.getCategories().get(index);
        System.out.println("Name: " + specat.getName() + ". Email: " + specat.getEmail() + ". owner: " + specat.getOwner() );

    }
}

