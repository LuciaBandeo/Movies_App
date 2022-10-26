package com.mobile.tandil.javabaseproject.mvp.contract;

import com.mobile.tandil.javabaseproject.listener.ListenerReservation;

public interface ReservationPickersContract {

    interface ReservationPickersPresenter {

        void onButtonOkPickersPressed(ListenerReservation listenerReservation);

        void onButtonCancelPickersPressed();
    }

    interface ReservationPickersView {

        void showCheckInReservation(ListenerReservation listenerReservation);

        void showCheckOutReservation(ListenerReservation listenerReservation);

        void dismissFragment();
    }
}
