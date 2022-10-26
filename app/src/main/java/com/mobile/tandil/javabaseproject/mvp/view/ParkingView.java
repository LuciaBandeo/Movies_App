package com.mobile.tandil.javabaseproject.mvp.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.mobile.tandil.javabaseproject.R;
import com.mobile.tandil.javabaseproject.activity.ReservationActivity;
import com.mobile.tandil.javabaseproject.databinding.ActivityMainBinding;
import com.mobile.tandil.javabaseproject.fragment.ParkingDialogFragment;
import com.mobile.tandil.javabaseproject.listener.ListenerDialogFragment;
import com.mobile.tandil.javabaseproject.mvp.contract.ParkingContract;
import com.mobile.tandil.javabaseproject.mvp.view.base.ActivityView;
import com.mobile.tandil.javabaseproject.utils.Constants;

public class ParkingView extends ActivityView implements ParkingContract.View {

    private final ActivityMainBinding binding;

    public ParkingView(Activity activity, ActivityMainBinding binding) {
        super(activity);
        this.binding = binding;
    }

    public void showDialogFragment(ListenerDialogFragment dialogFragmentListener) {
        ParkingDialogFragment dialog = ParkingDialogFragment.newInstance(dialogFragmentListener);
        FragmentManager fragmentManager = getFragmentManager();
        dialog.show(fragmentManager, Constants.PARKING_DIALOG_FRAGMENT);
    }

    @Override
    public void showNumberOfParkingAvailable(String numberOfParkingAvailable) {
        binding.textViewMainActivity.setText(numberOfParkingAvailable);
    }

    public void showReservationActivity() {
        Activity activity = getActivity();
        if (activity != null) {
            Intent intent = ReservationActivity.newInstance(activity);
            activity.startActivity(intent);
        }
    }

    public void showSetParkingFirstMessage() {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, context.getString(R.string.error_message_set_before_reservation), Toast.LENGTH_LONG).show();
        }
    }

}
