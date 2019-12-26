import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CheckWindows {

    private WebDriver driver;
    private WebDriverWait wait;
    By links = By.cssSelector("form tr a:not([id])");

    private boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }


    public ExpectedCondition<String> thereIsWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() == 1 ? handles.iterator().next() : null;
            }

        };
    }

    @BeforeMethod
    public void start() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("start-maximized");
        driver = new FirefoxDriver();
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
    public void checkWindows() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElements(By.cssSelector(" table.dataTable tr.row td a")).get(3).click();
        for (int i = 0; i < driver.findElements(links).size(); i++) {
            String mainWindow = driver.getWindowHandle();
            System.out.println(mainWindow);
            Set<String> oldWindows = driver.getWindowHandles();
            driver.findElements(links).get(i).click();

            String newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }

    }

    @AfterMethod
    public void stop() {
        driver.quit();
        driver = null;
    }
}
