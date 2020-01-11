package cz.protivnakova.rukovoditel.selenium;

import objects.CreateProjectTestUtils;
import objects.GlobalTestUtils;
import objects.LoginTestUtils;
import objects.TasksTestUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class RukovoditelTest {
    public static final String USER = "rukovoditel";
    public static final String PASS = "vse456ru";
    public static final String PROJECT_NAME = "proz01_" + UUID.randomUUID();
    public static final String TASK_NAME = "task_proz01_" + UUID.randomUUID();
    public static final List<String> TASK_NAMES = Arrays.asList(
                                                        "task1_proz01_" + UUID.randomUUID(),
                                                        "task2_proz01_" + UUID.randomUUID(),
                                                        "task3_proz01_" + UUID.randomUUID(),
                                                        "task4_proz01_" + UUID.randomUUID(),
                                                        "task5_proz01_" + UUID.randomUUID(),
                                                        "task6_proz01_" + UUID.randomUUID(),
                                                        "task7_proz01_" + UUID.randomUUID());
    public ChromeDriver driver;
    public LoginTestUtils loginTestUtils;
    public GlobalTestUtils globalTestUtils;
    public TasksTestUtils tasksTestUtils;
    public CreateProjectTestUtils createProjectTestUtils;

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/mac/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginTestUtils = new LoginTestUtils(driver);
        globalTestUtils = new GlobalTestUtils(driver);
        createProjectTestUtils = new CreateProjectTestUtils(driver);
        tasksTestUtils = new TasksTestUtils(driver);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
