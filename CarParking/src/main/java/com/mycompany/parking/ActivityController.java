package com.mycompany.parking;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import static com.mycompany.parking.StateController.remaining; //for disabling entrance button for the user if there is no space


public class ActivityController implements Initializable {

    @FXML
    private ComboBox akbox;

    @FXML
    private TextField akEntry;
    @FXML
    private TableColumn<ActivityEntity, String> akCol;
    @FXML
    private TableColumn<ActivityEntity, String> entranceCol;
    @FXML
    private TableColumn<ActivityEntity, String> exitCol;

    @FXML
    private TableColumn<ActivityEntity, String> idCol;
    @FXML
    private Button returnBtn3;
    @FXML
    private Button parkedButton;
    @FXML
    private Button entranceBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Button clearAll;
    @FXML
    private Button deleteEntry;
    @FXML
    private Button allButton;

    @FXML
    TableView<ActivityEntity> actTableView;

    Integer index = -1;
    @FXML
    private void showRowDataInTextFields() { //for showing id in combobox after selecting an entry & hovering over the combobox
        index = actTableView.getSelectionModel().getSelectedIndex();
        if (index <= -1)
        {
            return;
        }
      akbox.getSelectionModel().select(idCol.getCellData(index));

        String selectedItem = akbox.getSelectionModel().getSelectedItem().toString();
        akbox.setValue(selectedItem);
    }

    @FXML
    private void deleteEntry() throws IOException { //delete entry
        showActivityList("DELETE FROM activity WHERE `activity`.`id` = '"+akbox.getValue().toString()+"'", false);
    }

    @FXML
    private void markEntrance() throws IOException { //new car entrance - entrance date & time is current and is set at sql server side.
        //code
        showActivityList("insert into activity(ak) values('"+akEntry.getText().toString()+"')", false);
        //showActivityList("SELECT * FROM activity", true);


    }
    @FXML
    private void markExit() throws IOException { //car exit - exit date & time is current and is set at sql server side. Before this is called, the default exit value is the earliest possible timestamp (1970..)
        //code

        showActivityList("UPDATE `activity` SET `out` = (SELECT NOW()) WHERE `activity`.`id` = '"+akbox.getValue().toString()+"'", false);
    }

    @FXML
    private void showParked() throws IOException { //show only currently parked cars
        //code
        String tmp = "1970-01-01 00:00:01";
        showActivityList("SELECT * FROM activity WHERE `activity`.`out` = '"+tmp+"'", true);
    }

     @FXML
    private void goToState() throws IOException { //return to landing view
        App.setRoot("state");
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showActivityList("SELECT * FROM activity", true);

        if (remaining <= 0) {
            entranceBtn.setDisable(true); //disable entrance button if no space left
        }
        akbox.setPromptText("Επιλογή id");



    }

    @FXML
    private void showAllActivity() throws IOException { //show all
        showActivityList("SELECT * FROM activity", true);
    }

    @FXML
    private void clearAllActivity() throws IOException { //clear all
        showActivityList("TRUNCATE TABLE activity", false);
    }


    // follows code for connecting to db, getting entries and pass them to observable list via objects in order for them to be shown to the user

    @FXML
    public ObservableList<ActivityEntity> getActivityEntityList(String request, Boolean flag) { //flag needed to define per scenario needed code
        ObservableList<ActivityEntity> activityEntitiesList = FXCollections.observableArrayList();


        Connection connection = Utilities.getConnection();
        String query = request;
        Statement st;
        ResultSet rs;

        try {
            assert connection != null;
            st = connection.createStatement();


            if (flag) {
                rs = st.executeQuery(query);
                ActivityEntity activity;
                while (rs.next()) {
                    activity = new ActivityEntity(rs.getTimestamp("in").toString(), rs.getTimestamp("out").toString().equals("1970-01-01 00:00:01.0")  ? ("Παρκαρισμένο τώρα"):(rs.getTimestamp("out").toString())
                            , rs.getString("ak"), rs.getString("id"));
                    activityEntitiesList.add(activity);
                    akbox.getItems().add(rs.getString("id")); //combobox dropdown options
                }
                Utilities.writeActivityToFile(activityEntitiesList);
            } else {
                st.executeUpdate(query);
                showActivityList("SELECT * FROM activity", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return activityEntitiesList;
    }

        public List showActivityList(String data, Boolean flag) {

            ObservableList<ActivityEntity> list = getActivityEntityList(data, flag);


            if (flag) {
                akCol.setCellValueFactory(new PropertyValueFactory<>("ak"));
                entranceCol.setCellValueFactory(new PropertyValueFactory<>("in"));
                exitCol.setCellValueFactory(new PropertyValueFactory<>("out"));
                idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

                actTableView.setItems(list);
            }
            return list;
    }
}