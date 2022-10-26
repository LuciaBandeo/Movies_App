package com.mobile.tandil.javabaseproject.mvp.view;

import android.app.DialogFragment;
import android.content.Context;
import android.widget.Toast;
import com.mobile.tandil.javabaseproject.R;
import com.mobile.tandil.javabaseproject.fragment.ParkingDialogFragment;
import com.mobile.tandil.javabaseproject.listener.ListenerDialogFragment;
import com.mobile.tandil.javabaseproject.mvp.contract.DialogFragmentContract;
import com.mobile.tandil.javabaseproject.mvp.view.base.FragmentView;

public class DialogView extends FragmentView implements DialogFragmentContract.View {

    public DialogView(ParkingDialogFragment fragment) {
        super(fragment);
    }

    @Override
    public void showParkingAvailable(String parkingAvailable, ListenerDialogFragment dialogFragmentListener) {
        DialogFragment fragment = (DialogFragment) getFragment();
        dialogFragmentListener.listenAvailableNumber(parkingAvailable);
        fragment.dismiss();
    }

    public void showInvalidMessage() {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, context.getString(R.string.error_message), Toast.LENGTH_LONG).show();
        }
    }

    public void showMinSpacesMessage() {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, context.getString(R.string.error_message_min_spaces), Toast.LENGTH_LONG).show();
        }
    }
}
