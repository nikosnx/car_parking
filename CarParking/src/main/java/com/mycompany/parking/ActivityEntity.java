package com.mycompany.parking;

import javafx.beans.property.SimpleStringProperty;

public class ActivityEntity { //class for activity entries using objects with related data (date & time eisodou, eksodou, ar. taftotitas etc)
    private SimpleStringProperty in = new SimpleStringProperty();
    private SimpleStringProperty out = new SimpleStringProperty();
    private SimpleStringProperty ak = new SimpleStringProperty();
    private SimpleStringProperty id = new SimpleStringProperty();

    public ActivityEntity(String in, String out, String ak, String id) {
        setAk(ak);
        setIn(in);
        setOut(out);
        setId(id);
    }

    private void setId(String id) {
        this.id.set(id);
    }

    public void setIn(String in) {
        this.in.set(in);
    }
    
    public void setOut(String out) {
        this.out.set(out);
    }
    public void setAk(String ak) {
        this.ak.set(ak);
    }         
 
    public String getIn() {
        return in.get();
    }

    public String getId() {
        return id.get();
    }

    public String getOut() {
        return out.get();
    }  
    public String getAk() {
        return ak.get();
    }      
}
