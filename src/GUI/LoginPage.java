package GUI;

import controller.UserManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.FinanceManagementSystem;
import model.User;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {
    @FXML
    public Button signUpBtn;
    @FXML
    public Button signInBtn;
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField pswField;
    private final FinanceManagementSystem fmis = new FinanceManagementSystem();
    private User user = new User("laikinius", "vartotojus");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void createUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("SignupPage.fxml"));
        Parent root = load.load();
        Stage stage = (Stage) signUpBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void validateUser(ActionEvent actionEvent) throws IOException {
        boolean correct = false;
        for (User u : User.users) {
            if (u.getLogin().equals(loginField.getText()) && u.getPsw().equals(pswField.getText())) {
                user = new User(u.getLogin(), u.getPsw());
                loadMainWindow();
                correct = true;
                User user = new User(u.getLogin(), u.getPsw());

            }


        }
        if (!correct) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("blogai suvesti duomenys");
            alert.showAndWait();
        }
    }

    private void loadMainWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();


        Stage stage = (Stage) signInBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
