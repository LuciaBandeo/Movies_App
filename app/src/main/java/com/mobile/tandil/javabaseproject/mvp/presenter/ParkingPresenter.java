package com.mobile.tandil.javabaseproject.mvp.presenter;

import com.mobile.tandil.javabaseproject.listener.ListenerDialogFragment;
import com.mobile.tandil.javabaseproject.mvp.contract.ParkingContract;
import com.mobile.tandil.javabaseproject.utils.Constants;

public class ParkingPresenter implements ParkingContract.Presenter {

    private final ParkingContract.Model model;
    private final ParkingContract.View view;

    public ParkingPresenter(ParkingContract.Model model, ParkingContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onShowButtonPressed(ListenerDialogFragment dialogFragmentListener) {
        view.showDialogFragment(dialogFragmentListener);
    }

    @Override
    public void listenParkingAvailable(String parkingAvailable) {
        if (parkingAvailable.isEmpty()) {
            view.showSetParkingFirstMessage();
        } else {
            model.setParkingLots(parkingAvailable);
            view.showNumberOfParkingAvailable(String.valueOf(model.getParkingLots()));
        }
    }

    @Override
    public void onReservationButtonPressed() {
        if (Integer.parseInt(model.getParkingLots()) > Constants.DEFAULT_PARKING) {
            view.showReservationActivity();
        } else {
            view.showSetParkingFirstMessage();
        }
    }
}
