package com.mycompany.parking;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import static com.mycompany.parking.Utilities.getConnection;


public class NewCustController {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private void goToState() throws IOException {
        App.setRoot("state");
    }

    @FXML
    private TextField name;
    @FXML
    private TextField at;
    @FXML
    private TextField phone;
    @FXML
    private TextField ak;
    @FXML
    private TextField park;
    @FXML
    private TextField ix;
    @FXML
    private TextField email;

    @FXML
    private void insertNewCustomer() { //insert new customer to db
        String query = "insert into analytiki(ak, ochima, odigos, phone, email, at) values('"+ak.getText()+"' , '"+ix.getText()+"' , '"+name.getText()+"' , '"+phone.getText()+"' , '"+email.getText()+"' , '"+at.getText()+"')";
        executeQuery(query);
        alert.setContentText("Ο πελάτης Εισήχθη με επιτυχία!");
        alert.show();
    }


    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

