package com.mobile.tandil.javabaseproject.mvp.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.widget.Toast;
import androidx.annotation.StringRes;
import com.mobile.tandil.javabaseproject.R;
import com.mobile.tandil.javabaseproject.databinding.ActivityReservationBinding;
import com.mobile.tandil.javabaseproject.fragment.ReservationDialogFragment;
import com.mobile.tandil.javabaseproject.listener.ListenerReservation;
import com.mobile.tandil.javabaseproject.mvp.contract.ReservationContract;
import com.mobile.tandil.javabaseproject.mvp.view.base.ActivityView;
import com.mobile.tandil.javabaseproject.utils.Constants;

public class ReservationView extends ActivityView implements ReservationContract.ReservationView {

    private final ActivityReservationBinding binding;

    public ReservationView(Activity activity, ActivityReservationBinding binding) {
        super(activity);
        this.binding = binding;
    }

    @Override
    public void showPickers(ListenerReservation listenerReservation, Boolean selectDate) {
        ReservationDialogFragment reservationDialogFragment = ReservationDialogFragment.newInstance(listenerReservation, selectDate);
        FragmentManager fragmentManager = getFragmentManager();
        reservationDialogFragment.show(fragmentManager, Constants.DATE_PICKER_DIALOG_FRAGMENT);
    }

    @Override
    public void showCheckIn(String date) {
        binding.textViewCheckIn.setText(date);
    }

    @Override
    public void showCheckOut(String date) {
        binding.textViewCheckOut.setText(date);
    }

    @Override
    public void showValidationsMessage(@StringRes int message) {

        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showEmptyFields() {
        showValidationsMessage(R.string.message_validations_empty_fields);
    }

    @Override
    public void showStartFromNowMessage() {
        showValidationsMessage(R.string.message_validations_start_from_now);
    }

    @Override
    public void showOutBeforeInMessage() {
        showValidationsMessage(R.string.message_validations_out_before_in);
    }

    @Override
    public void showNonExistentParkingMessage() {
        showValidationsMessage(R.string.message_validations_non_existent_parking);
    }

    @Override
    public void showReservationExistsMessage() {
        showValidationsMessage(R.string.message_validations_existent_reservation);
    }

    @Override
    public void showEverythingOkMessage() {
        showValidationsMessage(R.string.message_validations_confirm_reservation);
    }

    @Override
    public void showReservationSuccessful() {
        showValidationsMessage(R.string.message_reservation_successful);
        finishActivity();
    }

    @Override
    public void showExceptionInputStringMessage() {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, R.string.text_exception_parse_empty_string, Toast.LENGTH_SHORT).show();
        }
    }

    private void finishActivity() {

        Activity activity = (Activity) this.getActivity();
        if (activity != null)
            activity.finish();
    }
}
