package utils;

import GUI.Main;
import javafx.event.ActionEvent;
import model.*;

import java.sql.*;


public class databaseUtils {
    public static FinanceManagementSystem fmis = new FinanceManagementSystem();

    public static Connection connectDB () throws ClassNotFoundException {
        Connection con= null;
                Class.forName("com.mysql.cj.jdbc.Driver");
        try {
             con = DriverManager.getConnection("jdbc:mysql://localhost/kursinis_1", "root", "root");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;


    }
    public static void disconnectFromDb(Connection con){
        try{
            if(con!=null)
                con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
    public static void DataIn() throws ClassNotFoundException, SQLException {
        String queryy = "select LOGIN, PSW from USER";
        try (Statement stmt = connectDB().createStatement()) {
            ResultSet rs = stmt.executeQuery(queryy);
            while (rs.next()) {
                String user = rs.getString("LOGIN");
                String psw = rs.getString("PSW");
                User.users.add(new User(user,psw));

            }
        }
        String query = "select EMAIL, NAME, USER from CATEGORY";
        try (Statement stmt = connectDB().createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("NAME");
                String owner = rs.getString("USER");
                String email = rs.getString("EMAIL");
                fmis.getCategories().add(new Category(name, owner, email));
            }
        }
        String query1 = "select NAME, EMAIL, PARENTCAT from SUBCATEGORY";
        try (Statement stmt = connectDB().createStatement()) {
            ResultSet rs = stmt.executeQuery(query1);
            while (rs.next()) {
                int index = 0;
                String name = rs.getString("NAME");
                String email = rs.getString("EMAIL");
                String parentcat = rs.getString("PARENTCAT");
                for (int i = 0; i < fmis.getCategories().size(); i++) {
                    if (fmis.getCategories().get(i).getName().equals(parentcat)) {
                        index = i;
                        break;
                    }
                }
                User user = new User("laikinius", "vartotojus"); // kol nezinau kaip pakviest user kuris pasidaro prisijungiant
                fmis.getCategories().get(index).getSubCategories().add(new Category(name, user.getLogin(), email));
            }
        }

        String query2 = "select AMOUNT, DESCRIPTION, SUBCATEGORY, CATEGORY from EXPENSES";
        try (Statement stmt = connectDB().createStatement()) {
            ResultSet rs = stmt.executeQuery(query2);
            while (rs.next()) {
                int index = 0;
                int pavad = 0;
                int amount = rs.getInt("AMOUNT");
                String description = rs.getString("DESCRIPTION");
                String subcategory = rs.getString("SUBCATEGORY");
                String category = rs.getString("CATEGORY");
                for (int i = 0; i < fmis.getCategories().size(); i++) {
                    if (fmis.getCategories().get(i).getName().equals(category)) {
                        pavad = i;
                        break;
                    }
                }
                for (int i = 0; i < fmis.getCategories().get(pavad).getSubCategories().size(); i++) {
                    if (fmis.getCategories().get(pavad).getSubCategories().get(i).getName().equals(subcategory)) {
                        index = i;
                        break;
                    }
                }
                fmis.getCategories().get(pavad).getSubCategories().get(index).getExpenses().add(new Expenses(description, amount));


            }
        }
        String query3 = "select AMOUNT, DESCRIPTION, SUBCATEGORY, CATEGORY from INCOME";
        try (Statement stmt = connectDB().createStatement()) {
            ResultSet rs = stmt.executeQuery(query3);
            while (rs.next()) {
                int index = 0;
                int pavad = 0;
                int amount = rs.getInt("AMOUNT");
                String description = rs.getString("DESCRIPTION");
                String subcategory = rs.getString("SUBCATEGORY");
                String category = rs.getString("CATEGORY");
                for (int i = 0; i < fmis.getCategories().size(); i++) {

                    if (fmis.getCategories().get(i).getName().equals(category)) {
                        pavad = i;
                        break;
                    }
                }
                for (int i = 0; i < fmis.getCategories().get(pavad).getSubCategories().size(); i++) {
                    if (fmis.getCategories().get(pavad).getSubCategories().get(i).getName().equals(subcategory)) {
                        index = i;
                        break;
                    }
                }
                fmis.getCategories().get(pavad).getSubCategories().get(index).getIncome().add(new Income(description, amount));
            }
        }
    }
}
