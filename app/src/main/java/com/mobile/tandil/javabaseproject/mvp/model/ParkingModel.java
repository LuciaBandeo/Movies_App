package com.mobile.tandil.javabaseproject.mvp.model;

import com.mobile.tandil.javabaseproject.database.ReservationDatabase;
import com.mobile.tandil.javabaseproject.mvp.contract.ParkingContract;
import com.mobile.tandil.javabaseproject.utils.Constants;


public class ParkingModel implements ParkingContract.Model {

    private ReservationDatabase database;

    public ParkingModel(ReservationDatabase database){
        this.database=database;
    }

    @Override
    public String getParkingLots() {

        return database.getParkingLots();
    }

    @Override
    public void setParkingLots(String parkingAvailable) {
        database.setParkingLots(parkingAvailable);
    }
}
