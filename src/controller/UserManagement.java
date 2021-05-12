package controller;

import model.Category;
import model.FinanceManagementSystem;
import model.User;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import java.util.Scanner;


public class UserManagement implements Serializable {

public static void registerUser(Scanner scanner, FinanceManagementSystem fmis) {
    System.out.println("register an user by providing the required data:\n"
            + "{login};{password};");
    String[] userData = scanner.next().split(";");
    User.users.add(new User(userData[0],userData[1]));


    try {
        FileOutputStream out = new FileOutputStream("users.ser");
        ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(User.users);
        objOut.close();
        out.close();
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}
    public static void manageData(Scanner scanner, FinanceManagementSystem fmis) {
        String Cmd = "";

        while (!Cmd.equals("exit")) {
            System.out.println("Choose an action:\n"
                    + "\t import - import data\n"
                    + "\t export - export data\n"
                    + "\t exit - exit the menu.\n");
            Cmd = scanner.next();

            switch (Cmd) {
                case "import":
                    importData(scanner,fmis);
                    break;
                case "export":
                   exportData(scanner,fmis);
                    break;
                case "exit":
                    System.out.println("DONE\n");
                    break;
                default:
                    System.out.println("error picking\n");
            }
        }}




    private static void importData(Scanner scanner, FinanceManagementSystem fmis) {
        String path = System.getProperty("user.dir") + "\\ST.ser".replace("\\", "\\\\");
        System.out.println(path);
        ObjectInputStream input = null;
        try {
            System.out.println("Importing ST...");
            FileInputStream readFile = new FileInputStream(path);
            input = new ObjectInputStream(readFile);
            Category importedcat = (Category) input.readObject();
            fmis.overWriteSystem(importedcat);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException a) {
                a.printStackTrace();
            }
        }
    }
    private static void exportData(Scanner scanner, FinanceManagementSystem fmis) {
        Category category= new Category(fmis.getCategories());
        System.out.println(fmis.getCategories());
        try {
            System.out.println("Exporting");
            FileOutputStream out = new FileOutputStream("ST.ser");
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(category);
            objOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing ST file!");
        }
    }

}

