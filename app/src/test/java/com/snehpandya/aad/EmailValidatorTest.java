package com.snehpandya.aad;

import android.content.Context;

import com.snehpandya.aad.activity.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by sneh.pandya on 26/09/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class EmailValidatorTest {

    private static final String FAKE_STRING = "Hello world";

    @Mock
    Context mMockContext;

    @Test
    public void readStringFromContext() {
        when(mMockContext.getString(R.string.hello_world))
                .thenReturn(FAKE_STRING);
        MainActivity mainActivity = new MainActivity();
        String result = mainActivity.getHelloWorld();
        assertThat(result, is(FAKE_STRING));
    }
}
