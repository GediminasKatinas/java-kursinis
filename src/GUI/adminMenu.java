package GUI;

import javafx.beans.DefaultProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

public class adminMenu {
    public Button listUserBtn;
    public Button deleteUserBtn;
    public Button editUserBtn;
    public ListView listview;
    public Button backBtn;

    private final ObservableList<String> doList = FXCollections.observableArrayList();

    public void ListUsers(ActionEvent actionEvent) {
        Stage nauja = new Stage();
        ListView listView = new ListView();
        listView.getItems().addAll(User.getUsers());
        HBox hbox = new HBox(listView);
        Scene scene = new Scene(hbox, 300, 120);
        nauja.setScene(scene);
        nauja.show();

    }

    public void DelUser(ActionEvent actionEvent) {

    }

    public void EditUserInfo(ActionEvent actionEvent) {
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
