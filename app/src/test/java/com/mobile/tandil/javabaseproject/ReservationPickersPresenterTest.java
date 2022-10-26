package com.mobile.tandil.javabaseproject;

import com.mobile.tandil.javabaseproject.listener.ListenerReservation;
import com.mobile.tandil.javabaseproject.mvp.contract.ReservationPickersContract;
import com.mobile.tandil.javabaseproject.mvp.presenter.ReservationPickersPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReservationPickersPresenterTest {

    private ReservationPickersContract.ReservationPickersPresenter presenter;

    @Mock
    private ReservationPickersContract.ReservationPickersView view;

    @Mock
    private ListenerReservation listenerReservation;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        presenter = new ReservationPickersPresenter(view, true);
    }

    @Test
    public void testOnButtonOkPickersPressedCheckIn() {
        presenter.onButtonOkPickersPressed(listenerReservation);
        Mockito.verify(view).showCheckInReservation(listenerReservation);
    }

    @Test
    public void testOnButtonOkPickersPressedCheckOut() {
        presenter = new ReservationPickersPresenter(view, false);
        presenter.onButtonOkPickersPressed(listenerReservation);
        Mockito.verify(view).showCheckOutReservation(listenerReservation);
    }

    @Test
    public void testOnButtonCancelPickersPressed() {
        presenter.onButtonCancelPickersPressed();
        Mockito.verify(view).dismissFragment();
    }
}
