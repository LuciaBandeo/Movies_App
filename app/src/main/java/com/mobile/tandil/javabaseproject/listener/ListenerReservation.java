package com.mobile.tandil.javabaseproject.listener;

import java.io.Serializable;
import java.util.Calendar;

public interface ListenerReservation extends Serializable {

    void setCheckIn(Calendar checkInDate);

    void setCheckOut(Calendar checkOutDate);
}
