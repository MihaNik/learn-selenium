import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CheckLogs {

    private WebDriver driver;
    private WebDriverWait wait;
    By goodsTabs = By.cssSelector("div.tabs li");

    private boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @BeforeMethod
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setExperimentalOption("w3c", false);
        driver = new ChromeDriver(options);
        // driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("http://localhost/litecart/admin/login.php");
        //login with app
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Assert.assertTrue(areElementsPresent(driver, By.cssSelector("ul#box-apps-menu")));
    }

    @Test
    public void logs() {
        System.out.println(driver.manage().logs().getAvailableLogTypes());
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        for (int i = 3; i < driver.findElements(By.cssSelector("table.dataTable td:nth-child(3) a")).size(); i++) {
            driver.findElements(By.cssSelector("table.dataTable td:nth-child(3) a")).get(i).click();
            Assert.assertTrue(areElementsPresent(driver, goodsTabs));
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        }

        if (driver.manage().logs().get("browser").getAll().size() == 0) {
            System.out.println("No logs available");
        } else {
            driver.manage().logs().get("browser").getAll().forEach(l -> System.out.println(l));
        }

    }

    @AfterMethod
    public void stop() {
        driver.quit();
        driver = null;
    }
}
