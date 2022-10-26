package com.mobile.tandil.javabaseproject.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mobile.tandil.javabaseproject.databinding.ReservationPickersDialogFragmentBinding;
import com.mobile.tandil.javabaseproject.listener.ListenerReservation;
import com.mobile.tandil.javabaseproject.mvp.contract.ReservationPickersContract;
import com.mobile.tandil.javabaseproject.mvp.presenter.ReservationPickersPresenter;
import com.mobile.tandil.javabaseproject.mvp.view.ReservationPickersView;
import com.mobile.tandil.javabaseproject.utils.Constants;

public class ReservationDialogFragment extends DialogFragment {

    private ReservationPickersDialogFragmentBinding binding;
    private ReservationPickersContract.ReservationPickersPresenter presenter;
    private ListenerReservation listenerReservation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = ReservationPickersDialogFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new ReservationPickersPresenter(new ReservationPickersView(this, binding), getArguments().getBoolean(Constants.DATE_SELECTOR_BOOLEAN));
        listenerReservation = (ListenerReservation) getArguments().getSerializable(Constants.LISTENER_DATE_PICKER_KEY);
        setListeners();
    }

    public static ReservationDialogFragment newInstance(ListenerReservation listenerReservation, Boolean selectDate) {

        ReservationDialogFragment reservationDialogFragment = new ReservationDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.DATE_SELECTOR_BOOLEAN, selectDate);
        bundle.putSerializable(Constants.LISTENER_DATE_PICKER_KEY, listenerReservation);
        reservationDialogFragment.setArguments(bundle);
        return reservationDialogFragment;
    }

    private void setListeners() {

        binding.buttonOkPickers.setOnClickListener(view -> presenter.onButtonOkPickersPressed(listenerReservation));
        binding.buttonCancelPickers.setOnClickListener(view -> presenter.onButtonCancelPickersPressed());

    }
}
