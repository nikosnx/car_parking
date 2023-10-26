package com.mycompany.parking;

import javafx.beans.property.SimpleStringProperty;

public class CarEntity { //class for analytiki katastasi entries using objects with related data (email, arithmos kikloforias etc
    private SimpleStringProperty ak = new SimpleStringProperty();
    private SimpleStringProperty och = new SimpleStringProperty();
    private SimpleStringProperty od = new SimpleStringProperty();
    private SimpleStringProperty phone = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleStringProperty at = new SimpleStringProperty();

        public CarEntity(String ak, String och, String od, String phone, String email, String at) {
            setAk(ak);
            setOch(och);
            setOd(od);
            setPhone(phone);
            setEmail(email);
            setAt(at);
        }
    public String getEmail() {
        return email.get();
    }

    public String getAk() {
        return ak.get();
    }

    public String getOch() {
        return och.get();
    }

    public String getOd() {
        return od.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getAt() {
        return at.get();
    }


    public void setAk(String ak) {
            this.ak.set(ak);
    }

    public void setEmail(String email) {
            this.email.set(email);
    }
    public void setOch(String och) {
        this.och.set(och);
    }
    public void setOd(String od) {
        this.od.set(od);
    }
    public void setPhone(String phone) {
        this.phone.set(phone);
    }
    public void setAt(String at) {
        this.at.set(at);
    }
}
