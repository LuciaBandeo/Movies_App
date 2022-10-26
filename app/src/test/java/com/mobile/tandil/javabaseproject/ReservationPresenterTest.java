package com.mobile.tandil.javabaseproject;

import com.mobile.tandil.javabaseproject.database.ReservationDatabase;
import com.mobile.tandil.javabaseproject.listener.ListenerReservation;
import com.mobile.tandil.javabaseproject.mvp.contract.ReservationContract;
import com.mobile.tandil.javabaseproject.mvp.model.ReservationModel;
import com.mobile.tandil.javabaseproject.mvp.presenter.ReservationPresenter;
import com.mobile.tandil.javabaseproject.utils.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Calendar;

@RunWith(MockitoJUnitRunner.class)
public class ReservationPresenterTest {

    private static final String VALID_CHECK_IN = "17-10-2022 12:41";
    private static final String INVALID_CHECK_IN = "17-08-2022 12:41";
    private static final String VALID_CHECK_OUT = "18-10-2022 19:24";
    private static final String INVALID_CHECK_OUT = "18-08-2022 19:24";
    private static final String VALID_PARKING = "2401";
    private static final String INVALID_PARKING = "57683";
    private static final String NEGATIVE_PARKING = "-1";
    private static final String KEY_CODE = "1234";
    private static final String EMPTY_STRING = "";
    private ReservationDatabase database;
    private ReservationContract.ReservationPresenter presenter;

    @Mock
    private ReservationContract.ReservationView view;

    @Mock
    private ListenerReservation listenerReservation;

    @Mock
    private Calendar checkInDate;

    @Mock
    private Calendar checkOutDate;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        database = ReservationDatabase.getInstance();
        database.setParkingLots(VALID_PARKING);
        presenter = new ReservationPresenter(new ReservationModel(database), view);
    }

    @After
    public void clearDatabase() {
        database.clearDatabase();
    }

    @Test
    public void testShowPickersTrue() {
        presenter.showPickers(listenerReservation, true);
        Mockito.verify(view).showPickers(listenerReservation, true);
    }

    @Test
    public void testShowPickersFalse() {
        presenter.showPickers(listenerReservation, false);
        Mockito.verify(view).showPickers(listenerReservation, false);
    }

    @Test
    public void testSetCheckIn() {
        presenter.setCheckIn(checkInDate);
        Mockito.verify(view).showCheckIn(DateUtils.dateAsString(checkInDate));
    }

    @Test
    public void testSetCheckOut() {
        presenter.setCheckOut(checkOutDate);
        Mockito.verify(view).showCheckOut(DateUtils.dateAsString(checkOutDate));
    }

    @Test
    public void testSaveReservationEmptyStringParking() {
        presenter.saveReservation(VALID_CHECK_IN, VALID_CHECK_OUT, KEY_CODE, EMPTY_STRING);
        Mockito.verify(view).showExceptionInputStringMessage();
    }

    @Test
    public void testSaveReservationValidStringParking() {
        presenter.saveReservation(VALID_CHECK_IN, VALID_CHECK_OUT, KEY_CODE, VALID_PARKING);
        Mockito.verify(view).showReservationSuccessful();
    }

    @Test
    public void testSaveReservationEmptyCheckIn() {
        presenter.saveReservation(EMPTY_STRING, VALID_CHECK_OUT, KEY_CODE, VALID_PARKING);
        Mockito.verify(view).showEmptyFields();
    }

    @Test
    public void testSaveReservationEmptyCheckOut() {
        presenter.saveReservation(VALID_CHECK_IN, EMPTY_STRING, KEY_CODE, VALID_PARKING);
        Mockito.verify(view).showEmptyFields();
    }

    @Test
    public void testValidateReservationEmptyCode() {
        presenter.saveReservation(VALID_CHECK_IN, VALID_CHECK_OUT, EMPTY_STRING, VALID_PARKING);
        Mockito.verify(view).showEmptyFields();
    }

    @Test
    public void testValidateReservationStartFromNow() {
        presenter.saveReservation(INVALID_CHECK_IN, VALID_CHECK_OUT, KEY_CODE, VALID_PARKING);
        Mockito.verify(view).showStartFromNowMessage();
    }

    @Test
    public void testValidateReservationOutBeforeIn() {
        presenter.saveReservation(VALID_CHECK_IN, INVALID_CHECK_OUT, KEY_CODE, VALID_PARKING);
        Mockito.verify(view).showOutBeforeInMessage();
    }

    @Test
    public void testValidateReservationNonExistentParkingBigger() {
        presenter.saveReservation(VALID_CHECK_IN, VALID_CHECK_OUT, KEY_CODE, INVALID_PARKING);
        Mockito.verify(view).showNonExistentParkingMessage();
    }

    @Test
    public void testValidateReservationNonExistentParking() {
        presenter.saveReservation(VALID_CHECK_IN, VALID_CHECK_OUT, KEY_CODE, NEGATIVE_PARKING);
        Mockito.verify(view).showNonExistentParkingMessage();
    }

    @Test
    public void testValidateReservationEverythingOk() {
        presenter.saveReservation(VALID_CHECK_IN, VALID_CHECK_OUT, KEY_CODE, VALID_PARKING);
        Mockito.verify(view).showEverythingOkMessage();
    }

    @Test
    public void testValidateReservationExistentReservation() {
        presenter.saveReservation(VALID_CHECK_IN, VALID_CHECK_OUT, KEY_CODE, VALID_PARKING);
        presenter.saveReservation(VALID_CHECK_IN, VALID_CHECK_OUT, KEY_CODE, VALID_PARKING);
        Mockito.verify(view).showReservationExistsMessage();
    }
}
