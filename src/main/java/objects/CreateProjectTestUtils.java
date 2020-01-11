package objects;

import org.openqa.selenium.chrome.ChromeDriver;

public class CreateProjectTestUtils extends GlobalTestUtils{
    public CreateProjectTestUtils(ChromeDriver driver) {
        super(driver);
    }

    public void deleteCreatedProject(String projectName){
        removePreviousSearchIfPresent();
        searchForProjectAndDelete(projectName);
        waitForElementXPath("//div[@id='uniform-delete_confirm']//input[@id='delete_confirm']").click();
        waitForElementXPath("//div[@class='modal-footer']//button[@type='submit']").click();
    }

    public void searchForProjectAndDelete(String name){
        waitForElementXPath("//table[@class='table table-striped table-bordered table-hover']/tbody//tr//td[@class='fieldtype_input  field-158-td item_heading_td']//a[contains(text(), '" + name + "')]//..//..//a//i[@class='fa fa-trash-o']").click();
    }

    public boolean checkForProjectExistence(String projectName){
        return !driver.findElementsByXPath("//table[@class='table table-striped table-bordered table-hover']/tbody//tr//td[@class='fieldtype_input  field-158-td item_heading_td']//a[contains(text(), '" + projectName + "')]").isEmpty();
    }
}
