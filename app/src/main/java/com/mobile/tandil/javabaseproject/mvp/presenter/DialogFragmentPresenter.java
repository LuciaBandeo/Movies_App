package com.mobile.tandil.javabaseproject.mvp.presenter;

import com.mobile.tandil.javabaseproject.listener.ListenerDialogFragment;
import com.mobile.tandil.javabaseproject.mvp.contract.DialogFragmentContract;
import com.mobile.tandil.javabaseproject.utils.Constants;

public class DialogFragmentPresenter implements DialogFragmentContract.Presenter {

    private final DialogFragmentContract.View view;

    public DialogFragmentPresenter(DialogFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void onPressedInputButton(String parkingAvailable, ListenerDialogFragment listenerDialogFragment) {
        if (parkingAvailable.isEmpty()) {
            view.showInvalidMessage();
        } else if (Integer.parseInt(parkingAvailable) == Constants.EMPTY_PARKING) {
            view.showMinSpacesMessage();
        } else {
            view.showParkingAvailable(parkingAvailable, listenerDialogFragment);
        }
    }
}
