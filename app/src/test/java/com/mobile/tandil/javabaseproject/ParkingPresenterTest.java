package com.mobile.tandil.javabaseproject;

import com.mobile.tandil.javabaseproject.database.ReservationDatabase;
import com.mobile.tandil.javabaseproject.listener.ListenerDialogFragment;
import com.mobile.tandil.javabaseproject.mvp.contract.ParkingContract;
import com.mobile.tandil.javabaseproject.mvp.model.ParkingModel;
import com.mobile.tandil.javabaseproject.mvp.presenter.ParkingPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingPresenterTest {

    private ParkingContract.Presenter presenter;
    private static final String VALID_PARKING = "241";
    private static final String INVALID_PARKING = "-34";
    private static final String EMPTY_STRING = "";


    @Mock
    private ParkingContract.View view;

    @Mock
    private ListenerDialogFragment dialogFragmentListener;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        presenter = new ParkingPresenter(new ParkingModel(ReservationDatabase.getInstance()), view);
    }

    @Test
    public void testOnShowButtonPressed() {
        presenter.onShowButtonPressed(dialogFragmentListener);
        Mockito.verify(view).showDialogFragment(dialogFragmentListener);
    }

    @Test
    public void testListenParkingAvailableEmptyStringParking() {
        presenter.listenParkingAvailable(EMPTY_STRING);
        Mockito.verify(view).showSetParkingFirstMessage();
    }

    @Test
    public void testListenParkingAvailableValidStringParking() {
        presenter.listenParkingAvailable(VALID_PARKING);
        Mockito.verify(view).showNumberOfParkingAvailable(VALID_PARKING);
    }

    @Test
    public void testOnReservationButtonPressedValidParking() {
        presenter.listenParkingAvailable(VALID_PARKING);
        presenter.onReservationButtonPressed();
        Mockito.verify(view).showReservationActivity();
    }

    @Test
    public void testOnReservationButtonPressedInvalidParking() {
        presenter.listenParkingAvailable(INVALID_PARKING);
        presenter.onReservationButtonPressed();
        Mockito.verify(view).showSetParkingFirstMessage();
    }
}
