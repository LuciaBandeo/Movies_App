package com.mobile.tandil.javabaseproject.mvp.contract;

import com.mobile.tandil.javabaseproject.listener.ListenerDialogFragment;

public interface DialogFragmentContract {

    interface View {
        void showParkingAvailable(String parkingAvailable, ListenerDialogFragment dialogFragmentListener);

        void showInvalidMessage();

        void showMinSpacesMessage();
    }

    interface Presenter {
        void onPressedInputButton(String parkingAvailable, ListenerDialogFragment dialogFragmentListener);
    }
}
