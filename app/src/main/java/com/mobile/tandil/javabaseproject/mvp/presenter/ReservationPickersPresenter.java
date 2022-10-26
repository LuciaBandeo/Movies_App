package com.mobile.tandil.javabaseproject.mvp.presenter;

import com.mobile.tandil.javabaseproject.listener.ListenerReservation;
import com.mobile.tandil.javabaseproject.mvp.contract.ReservationPickersContract;

public class ReservationPickersPresenter implements ReservationPickersContract.ReservationPickersPresenter {

    private final ReservationPickersContract.ReservationPickersView view;
    private final Boolean selectDate;

    public ReservationPickersPresenter(ReservationPickersContract.ReservationPickersView reservationPickersView, Boolean selectDate) {

        this.view = reservationPickersView;
        this.selectDate = selectDate;
    }

    @Override
    public void onButtonOkPickersPressed(ListenerReservation listenerReservation) {
        if (selectDate) {
            view.showCheckInReservation(listenerReservation);
        } else {
            view.showCheckOutReservation(listenerReservation);
        }
    }

    @Override
    public void onButtonCancelPickersPressed() {
        view.dismissFragment();
    }
}
