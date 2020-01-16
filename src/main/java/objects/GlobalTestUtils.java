package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;
import java.util.List;

public class GlobalTestUtils extends RukovoditelTestUtils{

    public GlobalTestUtils(ChromeDriver driver) {
        super(driver);
    }

    public WebElement waitForElementXPath(String xPath){
        return  new WebDriverWait(driver, 3)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath(xPath)));
    }

    public WebElement waitForElementName(String name){
        return  new WebDriverWait(driver, 3)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.name(name)));
    }

    public WebElement waitForElementClassName(String className){
        return  new WebDriverWait(driver, 3)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.className(className)));
    }

    public WebElement waitForElementId(String id){
        return  new WebDriverWait(driver, 3)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.id(id)));
    }

    public WebElement waitForElementTagName(String tag){
        return  new WebDriverWait(driver, 3)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.tagName(tag)));
    }

    public List<WebElement> waitForElementsXPath(String xPath){
        return new WebDriverWait(driver, 3)
                .until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(By.xpath(xPath)));
    }

    public void goToProjectsPage(){
        //Find menu and click on projects
        waitForElementXPath("//div[@class='page-sidebar navbar-collapse collapse']//span[contains(text(),'Projects')]").click();
        removePreviousSearchIfPresent();
    }

    public void goToNewProjectsPage(){
        goToProjectsPage();
        //Find New project button and click on it
        waitForElementXPath("//div[@class='entitly-listing-buttons-left']//button[contains(text(),'Add Project')]").click();
    }

    public void removePreviousSearchIfPresent() {
        WebElement search = waitForElementId("entity_items_listing66_21_search_keywords");
        if (!search.getAttribute("value").isEmpty()){
            search.clear();
            search.sendKeys(Keys.ENTER);
        }
    }

    public void createNewProject(String projectName) {
        //Find New project button and click on it
        waitForElementXPath("//div[@class='entitly-listing-buttons-left']//button[contains(text(),'Add Project')]").click();
        //Select priority high
        Select prioritySelector = new Select(waitForElementId("fields_156"));
        prioritySelector.selectByValue("35");
        //Select status new
        Select statusSelector = new Select(waitForElementId("fields_157"));
        statusSelector.selectByValue("37");
        //Set project name
        waitForElementId("fields_158").sendKeys(projectName);
        //Set date to today
        waitForElementId("fields_159").sendKeys(Instant.now().toString());
        waitForElementXPath("//button[@type='submit']").click();
        goToProjectsPage();
    }
}
