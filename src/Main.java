import controller.AdminManagement;
import controller.CategoryManagement;
import controller.UserManagement;
import model.*;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
       int loggedin=0;
FinanceManagementSystem fmis = new FinanceManagementSystem();
Category category= new Category("test");
        User user = new User("laikinius", "vartotojus");

        String path = System.getProperty("user.dir") + "\\users.ser".replace("\\", "\\\\");
        System.out.println(path);
        ObjectInputStream input = null;
        try {
            System.out.println("Importing existing users");
            FileInputStream readFile = new FileInputStream(path);
            input = new ObjectInputStream(readFile);
            ArrayList<User> usersIn = (ArrayList<User>) input.readObject();
            User.users.addAll(usersIn);

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
        Scanner scanner = new Scanner(System.in);

        String cmd = "";
loop1:
            while(!cmd.equals("exit")){
            System.out.println("choose an option:\n"
                    +"\t reg - register \n"
                    +"\t log - login \n"
            +"\t exit. \n");
            cmd = scanner.next();

            switch(cmd){

                case "reg":

                    UserManagement.registerUser(scanner,fmis);


                case "log":
                {
                    System.out.println("Enter your username");
                    String login = scanner.next();
                    System.out.println("Enter your password");
                    String password = scanner.next();
                    for(User u: User.users) {
                        if (u.getLogin().equals(login) && u.getPsw().equals(password)) {
                            System.out.println("log in sucessful");
                            user = new User(u.getLogin(),u.getPsw());

                            break loop1;


                        }

                    }
                    System.out.println("incorrect, you will be redirected to register page" );
                    UserManagement.registerUser(scanner,fmis);


            }


                default:

            }

        }


            while(!cmd.equals("exit")){
            System.out.println("choose an option:\n"
                    +"\t manageData - manage the data  \n"
                    +"\t manageCat - manage the categories \n"
                    +"\t adminMeniu-admin meniu \n"
                    +"\t exit. \n");
            cmd = scanner.next();

            switch(cmd){

                case "manageData":

                    UserManagement.manageData(scanner,fmis);
                    break;

                case "manageCat":

                    CategoryManagement.manageCategories(scanner,fmis,user,category);
                case "adminMeniu":

                    AdminManagement.meniu(scanner,user,fmis,category);
                default:


            }



    }}
}

