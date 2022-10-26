package com.mobile.tandil.javabaseproject.mvp.presenter;

import com.mobile.tandil.javabaseproject.entity.Reservation;
import com.mobile.tandil.javabaseproject.listener.ListenerReservation;
import com.mobile.tandil.javabaseproject.mvp.contract.ReservationContract;
import com.mobile.tandil.javabaseproject.utils.DateUtils;
import com.mobile.tandil.javabaseproject.utils.ValidationEnums;
import java.util.Calendar;

public class ReservationPresenter implements ReservationContract.ReservationPresenter {

    private final ReservationContract.ReservationView view;
    private final ReservationContract.ReservationModel model;

    public ReservationPresenter(ReservationContract.ReservationModel model, ReservationContract.ReservationView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void showPickers(ListenerReservation listenerReservation, Boolean selectDate) {
        view.showPickers(listenerReservation, selectDate);
    }

    @Override
    public void setCheckIn(Calendar checkInDate) {

        String date = DateUtils.dateAsString(checkInDate);
        view.showCheckIn(date);
    }

    @Override
    public void setCheckOut(Calendar checkOutDate) {

        String date = DateUtils.dateAsString(checkOutDate);
        view.showCheckOut(date);
    }

    private boolean validateReservation(String checkIn, String checkOut, String code, String parkingSpace) {
        Calendar startDate = DateUtils.convertToCalendar(checkIn);
        Calendar endDate = DateUtils.convertToCalendar(checkOut);
        boolean valid = false;
        if (!checkIn.isEmpty() && !checkOut.isEmpty() && !code.isEmpty() && !parkingSpace.isEmpty()) {
            ValidationEnums validation_option = model.validateReservation(startDate, endDate, code, parkingSpace);
            switch (validation_option) {
                case START_FROM_NOW:
                    view.showStartFromNowMessage();
                    break;
                case OUT_BEFORE_IN:
                    view.showOutBeforeInMessage();
                    break;
                case NON_EXISTENT_PARKING:
                    view.showNonExistentParkingMessage();
                    break;
                case RESERVATION_EXISTS:
                    view.showReservationExistsMessage();
                    break;
                case EVERYTHING_OK:
                    valid = true;
                    view.showEverythingOkMessage();
                    break;
            }
        } else {
            view.showEmptyFields();
        }
        return valid;
    }

    @Override
    public void saveReservation(String checkIn, String checkOut, String code, String parkingSpace) {
        int parking;
        try {
            parking = Integer.parseInt(parkingSpace);
            if (validateReservation(checkIn, checkOut, code, String.valueOf(parking))) {
                model.addReservation(new Reservation(checkIn, checkOut, code, parkingSpace));
                view.showReservationSuccessful();
            }
        } catch (NumberFormatException e) {
            view.showExceptionInputStringMessage();
        }
    }
}
