package controller;

import model.Category;
import model.FinanceManagementSystem;
import model.User;

import java.io.Serializable;
import java.util.Scanner;

public class AdminManagement implements Serializable {
    public static void meniu(Scanner scanner, User user, FinanceManagementSystem fmis , Category category){
        if(user.getLogin().equals("admin") && user.getPsw().equals("admin")) {
            System.out.println("access granted");
        String adminCmd = "";
            while (!adminCmd.equals("exit")) {
                System.out.println("Choose an action:\n"
                        + "\t listUser - lists all users\n"
                        + "\t delUser - deletes an user\n"
                        + "\t editUser - edits users info\n"
                        + "\t exit.\n");
                adminCmd= scanner.next();

                switch (adminCmd) {
                    case "listUser":
System.out.println(User.getUsers());
                        break;
                        case "delUser":
                            delUser(scanner,user,fmis,category);
                            break;
                    case "editUser":
                        editUser(scanner,user,fmis,category);
                        break;
        }}}
     else {
         System.out.println("error you cannot access this meniu");
                 return;
        }}

    public static void delUser(Scanner scanner, User user,FinanceManagementSystem fmis,Category category){
        System.out.println("type the username of the account u want to delete from the database");
        String todeleteComp = scanner.next();
        User.getUsers().removeIf(User-> User.getLogin().equals(todeleteComp));
        System.out.println("Deleted successfully");
        

    }
    public static void editUser(Scanner scanner, User user,FinanceManagementSystem fmis,Category category){
        System.out.println("Choose the username of the account  you would like to edit");
        String toEditComp = scanner.next();
        int index = 0;
        for (int i = 0; i < User.getUsers().size(); i++) {
            if (User.getUsers().get(i).getLogin().equals(toEditComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            System.out.println("login doenst exist");
            AdminManagement.meniu(scanner,user,fmis,category);
        }
        String chooseOne = "";
        while (!chooseOne.equals("exit")) {
            System.out.println("Choose what you want to edit  :\n"
                    + "\t login - the username\n"
                    + "\t pass - password\n"
                    + "\t exit\n");
            chooseOne = scanner.next();


            switch (chooseOne) {
                case "login":
                    System.out.println("Enter new username: ");
                    String Name = "";
                    Name = scanner.next();
                    User.getUsers().get(index).setLogin(Name);
                    break;
                case "pass":
                    System.out.println("Enter new password: ");
                    String pass = "";
                    pass = scanner.next();
                    User.getUsers().get(index).setPsw(pass);
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("pick one");
            }
        }
        System.out.println("the changes are in full effect");
    }
}



