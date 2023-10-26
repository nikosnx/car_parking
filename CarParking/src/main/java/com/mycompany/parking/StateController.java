package com.mycompany.parking;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class StateController implements Initializable {


    @FXML
    private TableColumn<ActivityEntity, String> akCol;
    @FXML
    private TableColumn<ActivityEntity, String> entranceCol;
    @FXML
    private TableColumn<ActivityEntity, String> exitCol;
    @FXML
    TableView<ActivityEntity> actTableVieww;
    @FXML
    private Text totalNum;

    @FXML
    private Circle greenFlag;
    @FXML
    private Circle redFlag;
    @FXML
    private int total;
    //@FXML
    public static final int cap = 200; //set parking capacity

    @FXML
    public static int remaining;

    @FXML
    private void switchToNewCustomer() throws IOException {
        App.setRoot("NewCustomer");
    }
    @FXML
    public void switchToAnalyt() throws IOException {
        App.setRoot("Analytiki");
    }
    @FXML
    public void switchToActivity() throws IOException {
        App.setRoot("Activity");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String tmp = "1970-01-01 00:00:01"; //default date & time for cars that are parked
        //calculate free parking space units based on capacity & parked cards
        createTv("SELECT * FROM activity WHERE `activity`.`out` = '"+tmp+"'");
        for (ActivityEntity item : actTableVieww.getItems()) { //count tableview objects
            total +=1;
        }
        remaining = cap - total;
        //configure colorful sign & labels based on free space
        totalNum.setText(String.valueOf(remaining));
        if (remaining <= 0) {
            totalNum.setText("ΚΑΜΙΑ");

            greenFlag.setVisible(false);
        } else {
            totalNum.setText(String.valueOf(remaining));
            redFlag.setVisible(false);
        }



    }

    @FXML
    public ObservableList<ActivityEntity> getActivityEntityList(String request) { //connect to db and insert activity entries as objects to list
        ObservableList<ActivityEntity> activityEntitiesListt = FXCollections.observableArrayList();


        Connection connection = Utilities.getConnection();
        String query = request;
        Statement st;
        ResultSet rs;

        try {
            assert connection != null;
            st = connection.createStatement();
            rs = st.executeQuery(query);
            ActivityEntity activit;
            while (rs.next()) {
                activit = new ActivityEntity(rs.getTimestamp("in").toString(), rs.getTimestamp("out").toString(), rs.getString("ak"), rs.getString("id"));
                activityEntitiesListt.add(activit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activityEntitiesListt;
    }

    public void createTv(String data) { //create tableview using the above list (not necessarily needed for counting activity entries but functional)

        ObservableList<ActivityEntity> list = getActivityEntityList(data);
        akCol.setCellValueFactory(new PropertyValueFactory<>("ak"));
        entranceCol.setCellValueFactory(new PropertyValueFactory<>("in"));
        exitCol.setCellValueFactory(new PropertyValueFactory<>("out"));

        actTableVieww.setItems(list);

    }
}