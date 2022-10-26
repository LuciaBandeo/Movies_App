package com.mobile.tandil.javabaseproject.entity;

import com.mobile.tandil.javabaseproject.utils.DateUtils;
import java.util.Calendar;

public class Reservation {

    private final Calendar checkInDate;
    private final Calendar checkOutDate;
    private final String parkingSpace;
    private final String keyCode;

    public Reservation(String checkInDate, String checkOutDate, String keyCode, String parkingSpace) {
        this.checkInDate = DateUtils.convertToCalendar(checkInDate);
        this.checkOutDate = DateUtils.convertToCalendar(checkOutDate);
        this.parkingSpace = parkingSpace;
        this.keyCode = keyCode;
    }

    public Calendar getCheckInDate() {

        return checkInDate;
    }

    public Calendar getCheckOutDate() {

        return checkOutDate;
    }

    public String getParkingSpace() {

        return parkingSpace;
    }

    public String getKeyCode() {

        return keyCode;
    }

}
