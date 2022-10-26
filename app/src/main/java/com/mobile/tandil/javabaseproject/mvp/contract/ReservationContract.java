package com.mobile.tandil.javabaseproject.mvp.contract;

import com.mobile.tandil.javabaseproject.entity.Reservation;
import com.mobile.tandil.javabaseproject.listener.ListenerReservation;
import com.mobile.tandil.javabaseproject.utils.ValidationEnums;
import java.util.Calendar;

public interface ReservationContract {

    interface ReservationPresenter {
        void setCheckIn(Calendar entryDate);

        void setCheckOut(Calendar exitDate);

        void showPickers(ListenerReservation listenerReservation, Boolean selectDate);

        void saveReservation(String checkIn, String checkOut, String code, String parkingSpace);
    }

    interface ReservationView {

        void showPickers(ListenerReservation listenerReservation, Boolean selectDate);

        void showCheckIn(String date);

        void showCheckOut(String date);

        void showValidationsMessage(int message);

        void showEmptyFields();

        void showStartFromNowMessage();

        void showOutBeforeInMessage();

        void showNonExistentParkingMessage();

        void showReservationExistsMessage();

        void showEverythingOkMessage();

        void showReservationSuccessful();

        void showExceptionInputStringMessage();
    }

    interface ReservationModel {

        void addReservation(Reservation reservation);

        ValidationEnums validateReservation(Calendar startDate, Calendar endDate, String code, String parkingSpace);
    }
}
