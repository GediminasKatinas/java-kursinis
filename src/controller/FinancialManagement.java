package controller;

import model.*;

import java.time.LocalDate;
import java.util.Scanner;

public class FinancialManagement {
    public static void catFinances(Scanner scanner, User user, FinanceManagementSystem fmis ,Category category){
        String catCmd = "";
        System.out.println("Choose category you would like to manage the finances of");
        String pavadinimas = scanner.next();
        int index = 0;

        for (int i = 0; i < fmis.getCategories().size(); i++) {

            if (fmis.getCategories().get(i).getName().equals(pavadinimas)  ) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            System.out.println("the category doesnt exist or your account does not have the permissions to manage this category");
            CategoryManagement.manageCategories(scanner,fmis,user,category);
        }
int pavad=index;
        while(!catCmd.equals("exit")){
            System.out.println("you are currently working with : "+pavadinimas+" category");
            System.out.println("Choose an action\n"+
                    "\t addexpense - adds expenses to a subcat\n"
                    +"\t addprofit - adds profit to a subcat \n"
                    + "\t removeexpense - removes expense from a subcat\n"
                    + "\t removeprofit - removes profit from subcat\n"
                    + "\t displayinfo - display the financial situation of a category\n"
                    +"\t exit \n");
            catCmd = scanner.next();

            switch(catCmd){
                case "addprofit":
                    addProfit(scanner,user,fmis,category,pavad);

                    break;
                case "addexpense":
                    addExpenses(scanner,user,fmis,category,pavad);

                    break;
                case "removeexpense":
                    removeExpenses(scanner,user,fmis,category,pavad);

                    break;
                case "removeprofit":
                    removeProfit(scanner,user,fmis,category,pavad);

                    break;

                case"displayinfo":
                    displayInfo(scanner,user,fmis,category,pavad);

    }}
}
    public static void addProfit(Scanner scanner, User user,FinanceManagementSystem fmis,Category category,int pavad) {
        System.out.println("Choose the subcategory");
        String toAddSubComp = scanner.next();
        int index = 0;

        for (int i = 0; i < fmis.getCategories().get(pavad).getSubCategories().size(); i++) {
            if (fmis.getCategories().get(pavad).getSubCategories().get(i).getName().equals(toAddSubComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            System.out.println("the subcategory doesnt exist");
            CategoryManagement.manageCategories(scanner,fmis,user,category);
        }

        System.out.println("Please provide the required data:\n{description};{amount};");
        String[] categoryprofit = scanner.next().split(";");
       fmis.getCategories().get(pavad).getSubCategories().get(index).getIncome().add(new Income(categoryprofit[0], Integer.parseInt(categoryprofit[1])));

        System.out.println("sucesfully added");
    }
    public static void displayInfo(Scanner scanner, User user,FinanceManagementSystem fmis,Category category,int pavad) {
        System.out.println("Choose the subcategory");
        String toAddSubComp = scanner.next();
        int index = 0;
        for (int i = 0; i < fmis.getCategories().get(pavad).getSubCategories().size(); i++) {
            if (fmis.getCategories().get(pavad).getSubCategories().get(i).getName().equals(toAddSubComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            System.out.println("the subcategory doesnt exist");
            CategoryManagement.manageCategories(scanner,fmis,user,category);
        }
System.out.println(fmis.getCategories().get(pavad).getSubCategories().get(index).getIncome());
        System.out.println(fmis.getCategories().get(pavad).getSubCategories().get(index).getExpenses());

    }
    public static void addExpenses(Scanner scanner, User user,FinanceManagementSystem fmis,Category category,int pavad) {
        System.out.println("Choose the subcategory");
        String toAddSubComp = scanner.next();
        int index = 0;

        for (int i = 0; i < fmis.getCategories().get(pavad).getSubCategories().size(); i++) {
            if (fmis.getCategories().get(pavad).getSubCategories().get(i).getName().equals(toAddSubComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            System.out.println("the subcategory doesnt exist");
            CategoryManagement.manageCategories(scanner,fmis,user,category);
        }

        System.out.println("Please provide the required data:\n{description};{amount};");
        String[] categoryexpense = scanner.next().split(";");
        fmis.getCategories().get(pavad).getSubCategories().get(index).getExpenses().add(new Expenses(categoryexpense[0], Integer.parseInt(categoryexpense[1])));

        System.out.println("sucesfully added");
    }
    public static void removeExpenses(Scanner scanner, User user,FinanceManagementSystem fmis,Category category,int pavad) {
        System.out.println("Choose the subcategory");
        String toAddSubComp = scanner.next();
        int index = 0;

        for (int i = 0; i < fmis.getCategories().get(pavad).getSubCategories().size(); i++) {
            if (fmis.getCategories().get(pavad).getSubCategories().get(i).getName().equals(toAddSubComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            System.out.println("the subcategory doesnt exist");
            CategoryManagement.manageCategories(scanner,fmis,user,category);
        }
        System.out.println("Enter expense description info to remove that expense:\n");
        String removeexp = scanner.next();
        fmis.getCategories().get(pavad).getSubCategories().get(index).getExpenses().removeIf(Expenses -> Expenses.getExpensesDescription().equals(removeexp));
        System.out.println("expense removed");
    }
    public static void removeProfit(Scanner scanner, User user,FinanceManagementSystem fmis,Category category,int pavad) {
        System.out.println("Choose the subcategory");
        String toAddSubComp = scanner.next();
        int index = 0;

        for (int i = 0; i < fmis.getCategories().get(pavad).getSubCategories().size(); i++) {
            if (fmis.getCategories().get(pavad).getSubCategories().get(i).getName().equals(toAddSubComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            System.out.println("the subcategory doesnt exist");
            CategoryManagement.manageCategories(scanner,fmis,user,category);
        }
        System.out.println("Enter expense description info to remove that expense:\n");
        String removeinc = scanner.next();
        fmis.getCategories().get(pavad).getSubCategories().get(index).getIncome().removeIf(Expenses -> Expenses.getIncomeDescription().equals(removeinc));
        System.out.println("expense removed");
    }


}
