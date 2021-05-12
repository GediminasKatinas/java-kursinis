package GUI;

import controller.CategoryManagement;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.PopupBuilder;
import javafx.stage.Stage;
import jdbc.catJDBC;
import model.Category;
import model.FinanceManagementSystem;
import model.User;
import utils.databaseUtils;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class manageCat {
    @FXML
    public Button createCat;
    @FXML
    public Button remCat;
    @FXML
    public Button createSubCat;
    @FXML
    public Button removeSubCat;
    @FXML
    public Button enterCat;
    @FXML
    public Button backBtn;
    public MenuItem back;
    public MenuItem editCat;
    public MenuItem informationCat;
    @FXML
    TreeView selectionTreeView;
    FinanceManagementSystem fmis = dataManagement.fmis;
    public static int skaicius;
    Connection con = Main.con;



    @FXML
    private void handleButtonAction(ActionEvent event) {
        createTree();
    }

    public void createTree() {

        selectionTreeView.setRoot(new TreeItem<String>("neveikia tree:("));
        fmis.getCategories().forEach(Category -> addTreeItems(Category, selectionTreeView.getRoot()));
        selectionTreeView.setShowRoot(false);


    }

    private void addTreeItems(Category section, TreeItem parentItem) {
        TreeItem<String> sectionTreeItem = new TreeItem<String>(section.getName());
        parentItem.getChildren().add(sectionTreeItem);
        section.getSubCategories().forEach(subsection -> addTreeItems(subsection, sectionTreeItem));
    }


    public void initialize(URL location, ResourceBundle resources) {
    }

    public void goBack(Event actionEvent) throws IOException {

        FXMLLoader load = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = load.load();
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void createCat(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        JFrame frame = new JFrame();
        User user = new User("laikinius", "vartotojus"); // kol nezinau kaip pakviest user kuris pasidaro prisijungiant
        Object result = JOptionPane.showInputDialog(frame, "Enter categories name:");
        Object reisult = JOptionPane.showInputDialog(frame, "Enter ur email:");
        fmis.getCategories().add(new Category(result.toString(), user.getLogin(), reisult.toString()));
        catJDBC.createCat(result.toString(),reisult.toString(),user.getLogin());

    }

    public void remCat(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        JFrame frame = new JFrame();
        String todeleteComp = JOptionPane.showInputDialog(frame, "Enter categories name:");
        fmis.getCategories().removeIf(Category -> Category.getName().equals(todeleteComp));
       catJDBC.removeCat(todeleteComp);
    }

    public void createSubCat(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        JFrame frame = new JFrame();
        String toAddSubComp = JOptionPane.showInputDialog(frame, "Enter categories name:");
        int index = 0;
        for (int i = 0; i < fmis.getCategories().size(); i++) {
            if (fmis.getCategories().get(i).getName().equals(toAddSubComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("category doesnt exist");
            alert.showAndWait();

        } else {
            JFrame frames = new JFrame();
            User user = new User("laikinius", "vartotojus"); // kol nezinau kaip pakviest user kuris pasidaro prisijungiant
            Object result = JOptionPane.showInputDialog(frame, "Enter subcategories name:");
            Object reisult = JOptionPane.showInputDialog(frame, "Enter ur email:");
            fmis.getCategories().get(index).getSubCategories().add(new Category(result.toString(), user.getLogin(), reisult.toString()));
            catJDBC.createsubCat(result.toString(),reisult.toString(),toAddSubComp);
        }
    }

    public void removeSubCat(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        JFrame frame = new JFrame();
        String toRemoveSubComp = JOptionPane.showInputDialog(frame, "Choose category you would like to remove the subCategory from");
        int index = 0;
        for (int i = 0; i < fmis.getCategories().size(); i++) {
            if (fmis.getCategories().get(i).getName().equals(toRemoveSubComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("category subcat doesnt exist");
            alert.showAndWait();
        } else {
            Object reisult = JOptionPane.showInputDialog(frame, "Enter the subcats name:");

            fmis.getCategories().get(index).getSubCategories().removeIf(Category -> Category.getName().equals(reisult));
            catJDBC.removesubCat(reisult.toString());
        }
    }


    public void enterCat(ActionEvent actionEvent) throws IOException {

        JFrame frame = new JFrame();
        String pavadinimas = JOptionPane.showInputDialog(frame, "Choose the category you would like to manage the finances of");
        int index = 0;

        for (int i = 0; i < fmis.getCategories().size(); i++) {

            if (fmis.getCategories().get(i).getName().equals(pavadinimas)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("category  doesnt exist");
            alert.showAndWait();
        } else {
            skaicius = index;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("financialMenu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) enterCat.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();


        }
    }

    public void editCat(ActionEvent actionEvent) {
        JFrame frame = new JFrame();
        String toEditComp = JOptionPane.showInputDialog(frame, "Choose the category you want to edit");
        int index = 0;
        for (int i = 0; i < fmis.getCategories().size(); i++) {
            if (fmis.getCategories().get(i).getName().equals(toEditComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("category  doesnt exist");
            alert.showAndWait();
        } else {
            //nepabaigta da
        }
    }

    public void infoCat(ActionEvent actionEvent) {
        JFrame frame = new JFrame();
        Object toCheckComp = JOptionPane.showInputDialog(frame, "enter the categories name of which u want to get info");
        int index = 0;
        for (int i = 0; i < fmis.getCategories().size(); i++) {
            if (fmis.getCategories().get(i).getName().equals(toCheckComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("category doesnt exist");
            alert.showAndWait();
        } else {
            Category specat = fmis.getCategories().get(index);
            Stage newStage = new Stage();
            VBox comp = new VBox();
            TextField nameField = new TextField("Name: " + specat.getName());
            TextField phoneNumber = new TextField("Email: " + specat.getEmail());
            TextField phoneNumber1 = new TextField("owner: " + specat.getOwner());
            comp.getChildren().add(nameField);
            comp.getChildren().add(phoneNumber);
            comp.getChildren().add(phoneNumber1);

            Scene stageScene = new Scene(comp, 300, 300);
            newStage.setScene(stageScene);
            newStage.show();
        }
    }
}
