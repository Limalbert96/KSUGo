package com.seniorproject.patrick.ksugo;

public class Lalo {
    public double latitude;
    public double longitude;

    Lalo(double lat, double longi) {
        latitude = lat;
        longitude = longi;
    }

    Lalo(){

    }

    public double returnLat () {
        return latitude;
    }

    public double returnLong () {
        return longitude;
    }

    public void equal (Lalo temp) {
        this.latitude = temp.latitude;
        this.longitude = temp.longitude;
    }
}
