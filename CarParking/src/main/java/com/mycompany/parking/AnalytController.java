package com.mycompany.parking;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class AnalytController implements Initializable {

    @FXML
    private void goToState() throws IOException {
        App.setRoot("state");
    }

    @FXML
    private TableColumn<CarEntity, String> akCol;
    @FXML
    private TableColumn<CarEntity, String> ochCol;
    @FXML
    private TableColumn<CarEntity, String> odCol;
    @FXML
    private TableColumn<CarEntity, String> phoneCol;
    @FXML
    private TableColumn<CarEntity, String> emailCol;
    @FXML
    private TableColumn<CarEntity, String> atCol;
    @FXML
    TableView<CarEntity> analTableView;
    @FXML
    private Button returnBtn2;
    @FXML
    private Button parkedButton;
    @FXML
    private Button perAkButton;
    @FXML
    private Button perCustButton;
    @FXML
    private Button allCarsButton;
    @FXML
    private TextField perAkField;
    @FXML
    private TextField perCustField;
    @FXML
    private Button clearAllButton;
    @FXML
    private Button deleteByCustButton;

    Integer index = -1;
    @FXML
    private void showRowDataInTextFieldsC() { //show arithmos taftotitas from selected entry at relevant text field when clicked inside
        index = analTableView.getSelectionModel().getSelectedIndex();
        if (index <= -1)
        {
            return;
        }
        perCustField.setText(atCol.getCellData(index));
    }

    @FXML
    private void showRowDataInTextFieldsIx() { //show  Arithmos Kykloforias from selected entry at relevant text field when clicked inside
        index = analTableView.getSelectionModel().getSelectedIndex();
        if (index <= -1)
        {
            return;
        }
        perAkField.setText(akCol.getCellData(index));
    }

    @FXML
    private void showByCustomer() throws IOException { //show entries related to specific arithmos taftotitas
        String tmp = perCustField.getText();
        showCarEntities("SELECT * FROM analytiki WHERE `analytiki`.`at` = '"+tmp+"'", true);
    }

    @FXML
    private void showByIx() throws IOException { //show entries related to specific arithmos kikloforias
        //code
        String tmp = perAkField.getText();
        showCarEntities("SELECT * FROM analytiki WHERE `analytiki`.`ak` = '"+tmp+"'", true);
    }

    @FXML
    private void showAllCars() throws IOException { //show all
        showCarEntities("SELECT * FROM analytiki", true);
    }

    @FXML
    private void clearAllCarEntries() throws IOException { //delete all entries from the system
        showCarEntities("TRUNCATE TABLE analytiki", false);
    }

    @FXML
    private void deleteByCustomer() throws IOException { //delete entry related to specific customer
        showCarEntities("DELETE FROM analytiki WHERE `analytiki`.`at` = '"+perCustField.getText()+"'", false);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showCarEntities("SELECT * FROM analytiki", true);
    }


//code for connecting to db, getting entries and pass them to observable list via objects in order for them to be shown to the user
    public ObservableList<CarEntity> getCarEntityList(String request, Boolean flag){ //flag needed to define per scenario needed code
        ObservableList<CarEntity> carEntitiesList = FXCollections.observableArrayList();
        Connection connection = Utilities.getConnection();
        String query = request;
        Statement st;
        ResultSet rs;

        try {
            assert connection != null;
            st = connection.createStatement();

            if (flag) {
                rs = st.executeQuery(query);
                CarEntity car;
                while(rs.next()) {
                    car = new CarEntity(rs.getString("ak"),rs.getString("ochima"),rs.getString("odigos"),rs.getString("phone"), rs.getString("email"), rs.getString("at"));
                    carEntitiesList.add(car);
            }
                Utilities.writeAnalytikiToFile(carEntitiesList);
        } else {
                st.executeUpdate(query);
                showCarEntities("SELECT * FROM analytiki", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carEntitiesList;
    }

    public void showCarEntities(String data, Boolean flag) {
        ObservableList<CarEntity> list = getCarEntityList(data, flag);
        if (flag) {
            akCol.setCellValueFactory(new PropertyValueFactory<>("ak"));
            ochCol.setCellValueFactory(new PropertyValueFactory<>("och"));
            odCol.setCellValueFactory(new PropertyValueFactory<>("od"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            atCol.setCellValueFactory(new PropertyValueFactory<>("at"));

            analTableView.setItems(list);
        }
    }


}