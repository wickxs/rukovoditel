package cz.protivnakova.rukovoditel.selenium;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class CreateProjectTest extends RukovoditelTest{

    @Test
    public void projectWithoutNameNotCreated(){
        //Given
        loginTestUtils.loginUser(USER, PASS);

        //When
        createProjectTestUtils.goToNewProjectsPage();
        //Click on save button
        globalTestUtils.waitForElementXPath("//div[@id='ajax-modal']//button[@type='submit']").click();

        //Then
        Assert.assertTrue(globalTestUtils.waitForElementClassName("alert-danger").isDisplayed()
                && globalTestUtils.waitForElementId("fields_158-error").isDisplayed());
    }

    @Test
    public void createProjectTest(){
        //Given
        loginTestUtils.loginUser(USER, PASS);

        //When
        createProjectTestUtils.goToProjectsPage();
        createProjectTestUtils.createNewProject(PROJECT_NAME);

        //Then
        createProjectTestUtils.deleteCreatedProject(PROJECT_NAME);
        Assert.assertFalse(createProjectTestUtils.checkForProjectExistence(PROJECT_NAME));
    }
}
