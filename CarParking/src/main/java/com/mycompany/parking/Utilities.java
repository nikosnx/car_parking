package com.mycompany.parking;

import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Utilities {

    public static Connection getConnection() { //connect to db
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking","root","");
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void writeActivityToFile(ObservableList<ActivityEntity> list) { //for writing activity entries to csv file
        try (FileWriter fw = new FileWriter("activity.csv");
             BufferedWriter bw = new BufferedWriter(fw);
        ) {
            bw.write("id" + " , " + "Ημερομηνία και Ώρα Εισόδου"  + " , " + "Ημερομηνία και ώρα Εξόδου"  + " , " + "Αριθμός Κυκλοφορίας");
            bw.newLine();
            for (ActivityEntity activityEnt : list) {
                bw.write(activityEnt.getId() + " , " + activityEnt.getIn()  + " , " + activityEnt.getOut()  + " , " + activityEnt.getAk());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAnalytikiToFile(ObservableList<CarEntity> list) { //for writing analytiki drastiriotita entries to csv file
        try (FileWriter fw = new FileWriter("analytiki_katastasi.csv");
             BufferedWriter bw = new BufferedWriter(fw);
        ) {
            bw.write("Αριθμός Κυκλοφορίας" + " , " + "Όχημα"  + " , " + "Οδηγός"  + " , " + "Τηλέφωνο" + " , " + "Email" + " , " + "Αριθμός Ταυτότητας");
            bw.newLine();
            for (CarEntity carEntity : list) {
                bw.write(carEntity.getAk() + " , " + carEntity.getOch()  + " , " + carEntity.getOd()  + " , " + carEntity.getPhone() + " , " + carEntity.getEmail() + " , " + carEntity.getAt());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


