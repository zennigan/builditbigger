package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Zennigan on 1/12/2016.
 */
public class FunctionalAndroidTest extends AndroidTestCase implements EndpointsAsyncTaskListener{

    EndpointsAsyncTask asyncTask;
    String joke;
    CountDownLatch latch;

    protected void setUp() throws Exception {
        super.setUp();

        latch = new CountDownLatch(1);
        asyncTask = new EndpointsAsyncTask(this);
    }

    @Override
    public void taskCompleted(String joke) {
        this.joke = joke;
        latch.countDown();
    }

    public void testVerifyEndpointRetrieval() throws InterruptedException {
        asyncTask.execute();
        latch.await(20, TimeUnit.SECONDS);

        assertTrue(joke != null);
    }
}
