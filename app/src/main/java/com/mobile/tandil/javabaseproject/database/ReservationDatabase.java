package com.mobile.tandil.javabaseproject.database;

import com.mobile.tandil.javabaseproject.entity.Reservation;
import com.mobile.tandil.javabaseproject.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReservationDatabase {

    private static ReservationDatabase instance = null;
    private final HashMap<Integer, List<Reservation>> reservations = new HashMap<>();
    private int parkingLots = Constants.DEFAULT_PARKING;

    public static ReservationDatabase getInstance() {
        if (instance == null) {
            instance = new ReservationDatabase();
        }
        return instance;
    }

    public void addReservation(Reservation reservation) {
        if (reservations.get(reservation.getParkingSpace()) == null) {
            List<Reservation> reservationsList = new ArrayList<>();
            reservationsList.add(reservation);
            reservations.put(Integer.parseInt(reservation.getParkingSpace()), reservationsList);
        } else {
            reservations.get(reservation.getParkingSpace()).add(reservation);
        }
    }

    public List<Reservation> getReservations(int parkingLot) {
        return reservations.get(parkingLot);
    }

    public String getParkingLots() {

        return String.valueOf(parkingLots);
    }

    public void setParkingLots(String parkingLots) {

        this.parkingLots = Integer.parseInt(parkingLots);
    }

    public void clearDatabase() {
        reservations.clear();
    }

}
