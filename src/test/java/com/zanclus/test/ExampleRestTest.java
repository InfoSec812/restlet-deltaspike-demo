package com.zanclus.test;

import com.zanclus.restlet.demo.ExampleRest;
import javax.inject.Inject;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class ExampleRestTest {

    @Inject
    private ExampleRest api;

    @Test
    public void testGetSystemTime() {
        String result = api.getServerTime();
        Assert.assertTrue("The system time in milliseconds MUST be long integer", result.matches("\\d*"));
    }
}
