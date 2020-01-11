package objects;

import org.openqa.selenium.chrome.ChromeDriver;

public abstract class RukovoditelTestUtils {
    public ChromeDriver driver;
    public static final String BASE_URL = "http://digitalnizena.cz/rukovoditel";

    public RukovoditelTestUtils(ChromeDriver driver) {
        this.driver = driver;
    }
}
