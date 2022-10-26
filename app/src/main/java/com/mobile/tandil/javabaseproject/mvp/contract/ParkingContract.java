package com.mobile.tandil.javabaseproject.mvp.contract;

import com.mobile.tandil.javabaseproject.listener.ListenerDialogFragment;

public interface ParkingContract {

    interface Model {
        String getParkingLots();
        void setParkingLots(String parkingAvailable);
    }

    interface View {
        void showDialogFragment(ListenerDialogFragment dialogFragmentListener);

        void showReservationActivity();

        void showNumberOfParkingAvailable(String numberOfParking);

        void showSetParkingFirstMessage();
    }

    interface Presenter {
        void onShowButtonPressed(ListenerDialogFragment dialogFragmentListener);

        void onReservationButtonPressed();

        void listenParkingAvailable(String parkingAvailable);
    }
}

