import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MenuNavigateTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @BeforeMethod
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void menuNavigate() {
        driver.get("http://localhost/litecart/admin/login.php");

        //login with app
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Assert.assertTrue(areElementsPresent(driver, By.cssSelector("ul#box-apps-menu")));


        //menu navigation
        for (int i = 0; i < driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).size(); i++) {
            driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).get(i).click();
            if (driver.findElements(By.cssSelector("ul.docs li")).size() > 0) {
                for (int j = 0; j < driver.findElements(By.cssSelector("ul.docs a")).size(); j++) {
                    driver.findElements(By.cssSelector("ul.docs a")).get(j).click();
                    Assert.assertTrue(areElementsPresent(driver, By.cssSelector("td#content h1")));
                    System.out.println(driver.findElement(By.cssSelector("td#content h1")).getText());
                }

            } else {
                Assert.assertTrue(areElementsPresent(driver, By.cssSelector("td#content h1")));
                System.out.println(driver.findElement(By.cssSelector("td#content h1")).getText());

            }
        }




    }

    @AfterMethod
    public void stop() {
        driver.quit();
        driver = null;
    }
}
