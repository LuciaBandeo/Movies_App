package com.mobile.tandil.javabaseproject.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.tandil.javabaseproject.database.ReservationDatabase;
import com.mobile.tandil.javabaseproject.databinding.ActivityReservationBinding;
import com.mobile.tandil.javabaseproject.listener.ListenerReservation;
import com.mobile.tandil.javabaseproject.mvp.contract.ReservationContract;
import com.mobile.tandil.javabaseproject.mvp.model.ReservationModel;
import com.mobile.tandil.javabaseproject.mvp.presenter.ReservationPresenter;
import com.mobile.tandil.javabaseproject.mvp.view.ReservationView;

import java.util.Calendar;

public class ReservationActivity extends AppCompatActivity implements ListenerReservation {

    private ActivityReservationBinding binding;
    private ReservationContract.ReservationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReservationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new ReservationPresenter(new ReservationModel(ReservationDatabase.getInstance()), new ReservationView(this, binding));
        setListeners();
    }

    public void setListeners() {

        binding.buttonCheckIn.setOnClickListener(view -> presenter.showPickers(this, true));
        binding.buttonCheckOut.setOnClickListener(view -> presenter.showPickers(this, false));
        binding.buttonReservationSave.setOnClickListener(view -> presenter.saveReservation(binding.textViewCheckIn.getText().toString(), binding.textViewCheckOut.getText().toString(), binding.editTextSecurityCode.getText().toString(), binding.editTextParkingLot.getText().toString()));
    }

    public static Intent newInstance(Context context) {
        return new Intent(context, ReservationActivity.class);
    }

    @Override
    public void setCheckIn(Calendar checkInDate) {
        presenter.setCheckIn(checkInDate);
    }

    @Override
    public void setCheckOut(Calendar checkOutDate) {
        presenter.setCheckOut(checkOutDate);
    }

}
