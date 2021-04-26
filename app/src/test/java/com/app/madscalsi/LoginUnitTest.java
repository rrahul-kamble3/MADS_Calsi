package com.app.madscalsi;

import android.content.Context;

import com.app.madscalsi.Activity.LoginActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginUnitTest {
    private static final String FAKE_STRING = "Login is successful";

    @Mock
    Context mMockContext;

    @Test
    public void readStringFromContext_LocalizedString() {

        LoginActivity myObjectUnderTest = new LoginActivity();

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.validate("admin","pass@123");

        // ...then the result should be the expected one.
        assertEquals(result, (FAKE_STRING));
    }
}