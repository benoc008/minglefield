package com.minglefield.endtoend;

import com.minglefield.controller.UserRestController;
import com.minglefield.driver.UserRegistrationDriver;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRegistrationEndToEndTest extends AbstractEndToEndTest {

    @Autowired
    private UserRestController controller;

    private UserRegistrationDriver driver;

    @Before
    public void setUp() {
        driver = new UserRegistrationDriver(controller);
    }

    @Ignore("Database clearance is necessary after each test!")
    @Test
    public void registerANewUser() {

        driver
                .clickTheRegisterANewAccountButton()
                .fillInFirstName("John")
                .fillInLastName("Doe")
                .fillInEmailAddress("john.doe@minglefield.com")
                .fillInPassword("pass123")
                .clickTheRegisterButton()
                .thereAreNoErrors();
    }
}
