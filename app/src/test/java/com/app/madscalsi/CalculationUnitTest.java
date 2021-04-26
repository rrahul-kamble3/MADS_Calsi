package com.app.madscalsi;

import android.content.Context;

import com.app.madscalsi.Activity.LoginActivity;
import com.app.madscalsi.Activity.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculationUnitTest {
    private static final String FAKE_STRING = "Enter correct input for calculation";

    @Mock
    Context mMockContext;

    @Test
    public void readStringFromContext_LocalizedString() {

        MainActivity myObjectUnderTest = new MainActivity();

        // ...when the string is returned from the object under test...
        boolean result = myObjectUnderTest.validateInputs("10+56");

        // ...then the result should be the expected one.
        assertEquals(true,result);
    }
}