package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.FinanceManagementSystem;
import utils.databaseUtils;

import java.io.IOException;
import java.sql.Connection;

public class MainMenu {
    @FXML
    public Button signUpBtn;
    @FXML
    public Button manageCat;
    public Button adminMeniu;
    public Button ManageData;
    public Button exitBtn;
    Connection con = Main.con;

    public void loadDataManagement(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dataManagement.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ManageData.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void manageCat(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("manageCat.fxml"));
        Parent root = loader.load();
        TreeView treeView = new TreeView();

        VBox vbox = new VBox(treeView);

        Scene scene = new Scene(vbox);
        Stage stage = (Stage) manageCat.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void enterAdmin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminMenu.fxml"));
        Parent root = loader.load();
        TreeView treeView = new TreeView();

        VBox vbox = new VBox(treeView);

        Scene scene = new Scene(vbox);
        Stage stage = (Stage) adminMeniu.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void exitProgram(ActionEvent actionEvent) {
      databaseUtils.disconnectFromDb(con);
            System.exit(0);


    }
}
