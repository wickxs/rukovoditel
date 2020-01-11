package cz.protivnakova.rukovoditel.selenium;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class LoginTest extends RukovoditelTest{
    public static final String DASHBOARD_URL = "http://digitalnizena.cz/rukovoditel/index.php?module=dashboard/";
    public static final String INVALID_PASSWORD = "invalidPassword";

    @Test
    public void loginWithValidCredentials(){
        //Given
        //TODO come up with given

        //When
        loginTestUtils.loginUser(USER, PASS);

        //Then
        Assert.assertEquals(DASHBOARD_URL, driver.getCurrentUrl());
    }

    @Test
    public void loginWithInvalidCredentials(){
        //Given
        //TODO come up with given

        //When
        loginTestUtils.loginUser(USER, INVALID_PASSWORD);

        //Then
        Assert.assertTrue(globalTestUtils.waitForElementClassName("alert-danger").isDisplayed());
    }

    @Test
    public void logoutLoggedInUser(){
        //Given
        loginWithValidCredentials();

        //When
        loginTestUtils.logoutUser();

        //Then
        Assert.assertTrue(globalTestUtils.waitForElementClassName("login").isDisplayed());
    }

}
