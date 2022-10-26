package com.mobile.tandil.javabaseproject.mvp.model;

import com.mobile.tandil.javabaseproject.database.ReservationDatabase;
import com.mobile.tandil.javabaseproject.entity.Reservation;
import com.mobile.tandil.javabaseproject.mvp.contract.ReservationContract;
import com.mobile.tandil.javabaseproject.utils.Constants;
import com.mobile.tandil.javabaseproject.utils.ValidationEnums;
import java.util.Calendar;
import java.util.List;

public class ReservationModel implements ReservationContract.ReservationModel {

    private ReservationDatabase database;

    public ReservationModel(ReservationDatabase database) {
        this.database = database;
    }

    @Override
    public void addReservation(Reservation reservation) {
        database.addReservation(reservation);
    }

    private boolean reservationAlreadyExists(Calendar checkIn, Calendar checkOut, int parking) {

        List<Reservation> reservations = database.getReservations(parking);
        boolean reservationExists = false;
        int reservation = Constants.EMPTY_PARKING;
        if (reservations != null && reservations.size() > Constants.EMPTY_PARKING) {
            while (!reservationExists && reservation < reservations.size()) {
                if ((parking == Integer.parseInt(reservations.get(reservation).getParkingSpace())) && (checkIn.before(reservations.get(reservation).getCheckOutDate()) || checkOut.after(reservations.get(reservation).getCheckInDate()))) {
                    reservationExists = true;
                    reservation++;
                }
            }
        }
        return reservationExists;
    }

    @Override
    public ValidationEnums validateReservation(Calendar startDate, Calendar endDate, String code, String parkingSpace) {

        int parking = Integer.parseInt(parkingSpace);

        if (startDate.before(Calendar.getInstance())) {
            return ValidationEnums.START_FROM_NOW;
        } else if (endDate.before(startDate)) {
            return ValidationEnums.OUT_BEFORE_IN;
        } else if (parking < Constants.EMPTY_PARKING || parking > Integer.parseInt(database.getParkingLots())) {
            return ValidationEnums.NON_EXISTENT_PARKING;
        } else if (reservationAlreadyExists(startDate, endDate, parking)) {
            return ValidationEnums.RESERVATION_EXISTS;
        } else
            return ValidationEnums.EVERYTHING_OK;
    }

}
