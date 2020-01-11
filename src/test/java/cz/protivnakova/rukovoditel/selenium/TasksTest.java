package cz.protivnakova.rukovoditel.selenium;

import objects.TaskStatus;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@FixMethodOrder(MethodSorters.JVM)
public class TasksTest extends RukovoditelTest{
    @Test
    public void createTask(){
        //Given
        loginTestUtils.loginUser("rukovoditel", "vse456ru");
        if (!tasksTestUtils.checkProjectExists(PROJECT_NAME)){
            tasksTestUtils.createNewProject(PROJECT_NAME);
        }

        //When
        tasksTestUtils.removePreviousSearchIfPresent();
        tasksTestUtils.searchAndClickOnProject(PROJECT_NAME);
        //Delete all tasks
        tasksTestUtils.deleteAllTasks();
        //Create task
        tasksTestUtils.createTaskJob(TASK_NAME, TaskStatus.NEW);

        //Then
        verifyAllAttributesAreSet();
        //Delete task and project
        tasksTestUtils.deleteTaskAfterTest(TASK_NAME);
        globalTestUtils.goToProjectsPage();
        createProjectTestUtils.deleteCreatedProject(PROJECT_NAME);
        Assert.assertFalse(createProjectTestUtils.checkForProjectExistence(PROJECT_NAME));
    }

    @Test
    public void create7TasksWithDifferentStatuses(){
        //Given
        loginTestUtils.loginUser("rukovoditel", "vse456ru");
        if (!tasksTestUtils.checkProjectExists(PROJECT_NAME)){
            tasksTestUtils.createNewProject(PROJECT_NAME);
        }

        //When
        tasksTestUtils.removePreviousSearchIfPresent();
        tasksTestUtils.searchAndClickOnProject(PROJECT_NAME);
        //Delete all tasks
        tasksTestUtils.deleteAllTasks();
        List<TaskStatus> statuses = Arrays.asList(TaskStatus.values());
        for (int i = 0; i < 7; i++){
            tasksTestUtils.createTaskJob(TASK_NAMES.get(i), statuses.get(i));
        }
        //Go back to tasks page
        globalTestUtils.waitForElementXPath("//ul[@class='page-breadcrumb breadcrumb noprint']//li/a[contains(text(), 'Tasks')]").click();
        //Check that filter is New, Open, Waiting
        if (driver.findElementsByXPath("//span[@class='filters-preview-box-heading']//span[contains(text(), 'New, Open, Waiting')]").isEmpty()){
            //Set filter
            globalTestUtils.waitForElementXPath("//button[@class='btn dropdown-toggle btn-users-filters']//i").click();
            globalTestUtils.waitForElementXPath("//ul[@class='dropdown-menu']//i[@class='fa fa-angle-right']").click();
        }
        //Check there are 3 rows filtered
        Assert.assertEquals(3, globalTestUtils.waitForElementsXPath("//table[@class='table table-striped table-bordered table-hover']//tbody//tr").size());
        //Click on filter
        globalTestUtils.waitForElementXPath("//span[@class='filters-preview-box-heading']//span[contains(text(), 'New, Open, Waiting')]").click();
        //Set filter to New and Waiting
        globalTestUtils.waitForElementXPath("//div[@class='modal-body']//li[@class='search-choice']//span[contains(text(), 'Open')]//..//a").click();
        //Save filters
        globalTestUtils.waitForElementXPath("//div[@class='modal-footer']//button[@type='submit']").click();
        //Check there are 3 rows filtered
        Assert.assertEquals(2, globalTestUtils.waitForElementsXPath("//table[@class='table table-striped table-bordered table-hover']//tbody//tr").size());
        //Remove filter
        globalTestUtils.waitForElementXPath("//div[@class='filters-preview-box is-active-1']//i[@class='fa fa-trash-o']").click();
        //Check there are all tasks
        Assert.assertEquals(7, globalTestUtils.waitForElementsXPath("//table[@class='table table-striped table-bordered table-hover']//tbody//tr").size());
        //Delete all tasks and project
        tasksTestUtils.deleteAllTasks();
        globalTestUtils.goToProjectsPage();
        createProjectTestUtils.deleteCreatedProject(PROJECT_NAME);
        Assert.assertFalse(createProjectTestUtils.checkForProjectExistence(PROJECT_NAME));
    }

    private void verifyAllAttributesAreSet() {
        //Go to info page
        globalTestUtils.waitForElementXPath("//i[@class='fa fa-info']").click();
        Assert.assertTrue(globalTestUtils.waitForElementXPath("//div[@class='form-group-172']").isDisplayed());
        Assert.assertEquals("Task", globalTestUtils.waitForElementClassName("form-group-167")
                .findElement(By.tagName("div"))
                .getText());
        Assert.assertEquals("New", globalTestUtils.waitForElementClassName("form-group-169")
                .findElement(By.tagName("div"))
                .getText());
        Assert.assertEquals("Medium", globalTestUtils.waitForElementClassName("form-group-170")
                .findElement(By.tagName("div"))
                .getText());
    }
}
