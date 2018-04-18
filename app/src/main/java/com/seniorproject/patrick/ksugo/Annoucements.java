package com.seniorproject.patrick.ksugo;


import java.util.Date;

/**
 * Created by patri on 2/14/2018.
 */

public class Annoucements {
    private String annoucementName;
    private Date date;

    public Annoucements(String annoucementName, Date date) {
        this.annoucementName = annoucementName;
        this.date = date;
    }

    public Annoucements(String annoucementName){
        this.annoucementName=annoucementName;
    }
    public Annoucements(){}
    public String getAnnoucementName() {
        return annoucementName;
    }

    public void setAnnoucementName(String annoucementName) {
        this.annoucementName = annoucementName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
