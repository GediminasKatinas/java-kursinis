package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Category;
import model.FinanceManagementSystem;
import model.User;
import utils.databaseUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Main extends Application {
    public static Connection con = null;
    public void start(Stage primaryStage) throws Exception {
         con = databaseUtils.connectDB();
         databaseUtils.DataIn();
        String query = "select LOGIN, PSW from USER";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String user = rs.getString("LOGIN");
                String psw = rs.getString("PSW");
                User.users.add(new User(user,psw));

            }
        }

        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        primaryStage.setTitle("Financial IS");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

