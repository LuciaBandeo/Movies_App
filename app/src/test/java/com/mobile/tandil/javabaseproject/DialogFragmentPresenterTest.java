package com.mobile.tandil.javabaseproject;

import com.mobile.tandil.javabaseproject.listener.ListenerDialogFragment;
import com.mobile.tandil.javabaseproject.mvp.contract.DialogFragmentContract;
import com.mobile.tandil.javabaseproject.mvp.presenter.DialogFragmentPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DialogFragmentPresenterTest {

    private DialogFragmentContract.Presenter presenter;
    private static final String VALID_PARKING = "2401";
    private static final String EMPTY_STRING = "";
    private static final String ZERO_PARKING = "0";

    @Mock
    private DialogFragmentContract.View view;

    @Mock
    private ListenerDialogFragment dialogFragmentListener;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        presenter = new DialogFragmentPresenter(view);
    }

    @Test
    public void testOnPressedInputButtonValidParking() {
        presenter.onPressedInputButton(VALID_PARKING, dialogFragmentListener);
        Mockito.verify(view).showParkingAvailable(VALID_PARKING, dialogFragmentListener);
    }

    @Test
    public void testOnPressedInputButtonInvalidParking() {
        presenter.onPressedInputButton(ZERO_PARKING, dialogFragmentListener);
        Mockito.verify(view).showMinSpacesMessage();
    }

    @Test
    public void testOnPressedInputButtonEmptyParking() {
        presenter.onPressedInputButton(EMPTY_STRING, dialogFragmentListener);
        Mockito.verify(view).showInvalidMessage();
    }
}
