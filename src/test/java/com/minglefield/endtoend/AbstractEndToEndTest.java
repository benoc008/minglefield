package com.minglefield.endtoend;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractEndToEndTest {

    @Before
    public void setUpTest() {

    }

    @After
    public void tearDownTest() {
        // TODO clear database
    }
}
