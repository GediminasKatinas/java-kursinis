package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import utils.databaseUtils;

import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dataManagement {
    public Button exportData;
    public Button ImportData;
    public Button backBtn;
    public ListView listview;
    public Button listUserBtn;
    public Button deleteUserBtn;
    public Button editUserBtn;
    public static FinanceManagementSystem fmis = new FinanceManagementSystem();
    public static boolean exporting;
    Connection con = Main.con;
    public dataManagement()  {
    }


    public void DataOut(ActionEvent actionEvent) {
        exporting = true;
        Category category = new Category(fmis.getCategories());
        try {

            FileOutputStream out = new FileOutputStream("ST.ser");
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(category);
            objOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing ST file!");
        }
    }

    public void DataIn(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        String queryy = "select LOGIN, PSW from USER";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(queryy);
            while (rs.next()) {
                String user = rs.getString("LOGIN");
                String psw = rs.getString("PSW");
                User.users.add(new User(user,psw));

            }
        }
        String query = "select EMAIL, NAME, USER from CATEGORY";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("NAME");
                String owner = rs.getString("USER");
                String email = rs.getString("EMAIL");
                fmis.getCategories().add(new Category(name, owner, email));
            }
        }
        String query1 = "select NAME, EMAIL, PARENTCAT from SUBCATEGORY";
        try (Statement stmt = con.createStatement()) {
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
        try (Statement stmt = con.createStatement()) {
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
        try (Statement stmt = con.createStatement()) {
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

    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        TreeView treeView = new TreeView();

        VBox vbox = new VBox(treeView);

        Scene scene = new Scene(vbox);
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
