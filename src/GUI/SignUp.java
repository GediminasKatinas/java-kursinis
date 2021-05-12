package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import utils.databaseUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;

public class SignUp {
    @FXML
    public Button SignupBtn;
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    Connection con = Main.con;
    public void CreateUser(ActionEvent actionEvent) throws IOException, ClassNotFoundException, SQLException {

        User.users.add(new User(username.getText(), password.getText()));
        jdbc.userJDBC.create(username.getText(), password.getText());
        FXMLLoader load = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Parent root = load.load();
        Stage stage = (Stage) SignupBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
