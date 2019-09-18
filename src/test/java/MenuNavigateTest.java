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
        System.out.println("Test");
        int size = driver.findElements(locator).size();
        System.out.println(size);
        return size > 0;
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
    public void myFirstTest() {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Assert.assertTrue(areElementsPresent(driver, By.cssSelector("ul#box-apps-menu")));

        List<String> menuNames = driver.findElements(By.cssSelector("ul#box-apps-menu span.name"))
                .stream()
                .map(WebElement -> WebElement.getText())
                .collect(Collectors.toList());
        System.out.println(menuNames);


        driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).get(1).click();
        long startTime = System.currentTimeMillis();
        boolean count2 = driver.findElements(By.cssSelector("ul.docs li")).size()>0;
        driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).get(0).click();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);



        driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).get(2).click();
        long startTime1 = System.currentTimeMillis();
        boolean count1= driver.findElements(By.cssSelector("ul.docs li")).size()>0;
        driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).get(0).click();
        long stopTime1 = System.currentTimeMillis();
        long elapsedTime1 = stopTime - startTime;
        System.out.println(elapsedTime1);


/*        String id = driver.findElements(By.cssSelector("ul.docs li")).get(0).getAttribute("id");
        System.out.println(id);*/


/*        for (WebElement el : driver.findElements(By.cssSelector("ul.docs a"))
        ) {
            el.click();
            Assert.assertTrue(areElementsPresent(driver, By.cssSelector("td#content h1")));
        }*/

        // driver.findElements(By.cssSelector("ul.docs a")).forEach(WebElement::click);


/*        for (int i = 0; i < driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).size(); i++) {
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
        }*/


    }

    @AfterMethod
    public void stop() {
        //driver.quit();
        //driver = null;
    }
}
