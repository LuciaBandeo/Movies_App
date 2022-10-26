package com.mobile.tandil.javabaseproject.mvp.view;

import android.app.Fragment;
import com.mobile.tandil.javabaseproject.databinding.ReservationPickersDialogFragmentBinding;
import com.mobile.tandil.javabaseproject.fragment.ReservationDialogFragment;
import com.mobile.tandil.javabaseproject.listener.ListenerReservation;
import com.mobile.tandil.javabaseproject.mvp.contract.ReservationPickersContract;
import com.mobile.tandil.javabaseproject.mvp.view.base.FragmentView;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReservationPickersView extends FragmentView implements ReservationPickersContract.ReservationPickersView {

    private final ReservationPickersDialogFragmentBinding binding;

    public ReservationPickersView(Fragment fragment, ReservationPickersDialogFragmentBinding binding) {
        super(fragment);
        this.binding = binding;
    }

    private Calendar getCalendar() {
        ReservationDialogFragment fragment = (ReservationDialogFragment) getFragment();
        Calendar calendar = new GregorianCalendar();
        calendar.set(binding.datePickerReservationDialogFragment.getYear(),
                binding.datePickerReservationDialogFragment.getMonth(),
                binding.datePickerReservationDialogFragment.getDayOfMonth(),
                binding.timePickerReservationDialogFragment.getHour(),
                binding.timePickerReservationDialogFragment.getMinute());
        if (fragment != null) {
            fragment.dismiss();
        }
        return calendar;
    }

    @Override
    public void showCheckInReservation(ListenerReservation listenerReservation) {

        listenerReservation.setCheckIn(getCalendar());
    }

    @Override
    public void showCheckOutReservation(ListenerReservation listenerReservation) {

        listenerReservation.setCheckOut(getCalendar());
    }

    @Override
    public void dismissFragment() {
        ReservationDialogFragment reservationDialogFragment = (ReservationDialogFragment) this.getFragment();
        if (reservationDialogFragment != null) {
            reservationDialogFragment.dismiss();
        }
    }
}
