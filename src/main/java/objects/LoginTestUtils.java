package objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTestUtils extends GlobalTestUtils {
    public LoginTestUtils(ChromeDriver driver) {
        super(driver);
    }

    public void loginUser(String user, String pass){
        driver.get(BASE_URL);
        WebElement username = waitForElementName("username");
        username.sendKeys(user);
        WebElement password =waitForElementName("password");
        password.sendKeys(pass);
        WebElement loginButton = waitForElementClassName("btn-info");
        loginButton.click();
    }

    public void logoutUser() {
        waitForElementXPath("//li[@class='dropdown user']//i[@class='fa fa-angle-down']").click();
        waitForElementXPath("//ul[@class='dropdown-menu']//i[@class='fa fa-sign-out']").click();
    }
}
