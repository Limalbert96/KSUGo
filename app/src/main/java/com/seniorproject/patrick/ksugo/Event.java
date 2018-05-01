package com.seniorproject.patrick.ksugo;

import java.sql.Time;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Albert on 4/14/18.
 */

public class Event {
    private String eventName;
    private String eventDescription;
    private String eventBuilding;
    private String eventRoom;
    private Date eventDate;
    private String eventTime;

    public Event(){}
    public Event(
            String eventName,
            String eventDescription,
            String eventBuilding,
            String eventRoom,
            Date eventDate,
            String eventTime) {

        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventBuilding = eventBuilding;
        this.eventRoom = eventRoom;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventBuilding() {
        return eventBuilding;
    }

    public void setEventBuilding(String eventBuilding) {
        this.eventBuilding = eventBuilding;
    }

    public String getEventRoom() {
        return eventRoom;
    }

    public void setEventRoom(String eventRoom) {
        this.eventRoom = eventRoom;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String dateToString(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("E, MMMM dd");
        return  dateFormat.format(eventDate);
    }

    public String timeToString(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
        return  dateFormat.format(eventDate);
    }
}
