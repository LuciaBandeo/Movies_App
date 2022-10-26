package com.mobile.tandil.javabaseproject.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobile.tandil.javabaseproject.databinding.ParkingDialogFragmentBinding;
import com.mobile.tandil.javabaseproject.listener.ListenerDialogFragment;
import com.mobile.tandil.javabaseproject.mvp.contract.DialogFragmentContract;
import com.mobile.tandil.javabaseproject.mvp.presenter.DialogFragmentPresenter;
import com.mobile.tandil.javabaseproject.mvp.view.DialogView;
import com.mobile.tandil.javabaseproject.utils.Constants;

public class ParkingDialogFragment extends DialogFragment {

    private DialogFragmentContract.Presenter presenter;
    private ListenerDialogFragment dialogFragmentListener;
    private ParkingDialogFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = ParkingDialogFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new DialogFragmentPresenter(new DialogView(this));
        dialogFragmentListener = (ListenerDialogFragment) getArguments().getSerializable(Constants.LISTENER_SET_PARKING_KEY);
        setListeners();
    }

    public void setListeners() {
        binding.buttonOkDialogFragment.setOnClickListener(view -> presenter.onPressedInputButton(binding.editTextNumber.getText().toString(), dialogFragmentListener));
        binding.buttonCancelDialogFragment.setOnClickListener(view -> dismiss());
    }


    public static ParkingDialogFragment newInstance(ListenerDialogFragment dialogFragmentListener) {
        ParkingDialogFragment dialogFragment = new ParkingDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.LISTENER_SET_PARKING_KEY, dialogFragmentListener);
        dialogFragment.setArguments(bundle);
        dialogFragment.setCancelable(false);
        return dialogFragment;
    }

}
