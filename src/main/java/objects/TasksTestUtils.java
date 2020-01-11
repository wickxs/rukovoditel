package objects;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TasksTestUtils extends GlobalTestUtils {
    public TasksTestUtils(ChromeDriver driver) {
        super(driver);
    }

    public void deleteAllTasks() {
        //If there are any tasks delete them
        if (!driver.findElementsByXPath("//table[@class='table table-striped table-bordered table-hover']/tbody//tr//td[contains(text(), 'No records found')]").isEmpty()){
            //Click select all
            waitForElementXPath("//div[@id='uniform-select_all_items']//input[@id='select_all_items']").click();
            //Delete all
            waitForElementXPath("//button[@class='btn btn-default dropdown-toggle']//i[@class='fa fa-angle-down']").click();
            waitForElementXPath("//ul[@class='dropdown-menu']//li//a//i[@class='fa fa-trash-o']").click();
            //Click delete
            waitForElementXPath("//div[@class='modal-footer']//button[@type='submit']").click();
        }
    }

    public void createTaskJob(String taskName, TaskStatus taskStatus) {
        //Click on New task button
        waitForElementXPath("//div[@class='entitly-listing-buttons-left']//button[@type='button']").click();
        //Select type task
        new Select(waitForElementId("fields_167")).selectByValue("42");
        //Set name
        waitForElementId("fields_168").sendKeys(taskName);
        //Select status
        new Select(waitForElementId("fields_169")).selectByValue(taskStatus.getValueId());
        //Select priority
        new Select(waitForElementId("fields_170")).selectByValue("55");
        //Set description
        driver.switchTo().frame(waitForElementXPath("//*[@class='cke_wysiwyg_frame cke_reset']"));
        waitForElementTagName("body").sendKeys("this is awesome description");
        driver.switchTo().parentFrame();
        //Click save
        waitForElementXPath("//button[@type='submit']").click();
    }

    public boolean checkProjectExists(String projectName) {
        goToProjectsPage();
        return !driver.findElementsByXPath("//table[@class='table table-striped table-bordered table-hover']/tbody//tr//td[@class='fieldtype_input  field-158-td item_heading_td']//a[contains(text(), '" + projectName + "')]").isEmpty();
    }

    public void deleteTaskAfterTest(String taskName){
        waitForElementXPath("//ul[@class='page-breadcrumb breadcrumb noprint']//a[contains(text(), 'Tasks')]").click();
        waitForElementXPath("//table[@class='table table-striped table-bordered table-hover']/tbody//tr//td[@class='fieldtype_input  field-168-td item_heading_td']//a[contains(text(),'" + taskName +"')]//..//..//i[@class='fa fa-trash-o']")
                .click();
        waitForElementXPath("//form[@id='delete_item_form']//button[@type='submit']").click();
    }

    public void searchAndClickOnProject(String projectName) {
        waitForElementXPath("//table[@class='table table-striped table-bordered table-hover']/tbody//tr//td[@class='fieldtype_input  field-158-td item_heading_td']//a[contains(text(), '" + projectName + "')]").click();
    }
}
