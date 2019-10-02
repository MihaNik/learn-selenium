import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SortCountries {

    private WebDriver driver;
    private WebDriverWait wait;

    private boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public boolean isCollectionSorted(List<String> list) {
        List<String> countries = new ArrayList<>(list);
        Collections.sort(list);
        return countries.equals(list);
    }

    @BeforeMethod
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
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
    public void checkSortCountries() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        Assert.assertEquals(driver.findElement(By.cssSelector("td#content h1")).getText(), "Countries");

        //check countries sort
        List<String> countries = driver
                .findElements(By.cssSelector("table.dataTable tr.row td:nth-child(5)"))
                .stream()
                .map(WebElement -> WebElement.getText())
                .collect(Collectors.toList());
        Assert.assertTrue(isCollectionSorted(countries));

        //check zones sort
        for (int i = 0; i < driver.findElements(By.cssSelector("table.dataTable tr.row")).size(); i++) {
            String zoneCount = driver.findElements(By.cssSelector("table.dataTable tr.row td:nth-child(6)")).get(i).getText();
            if (Integer.parseInt(zoneCount) != 0) {
                driver.findElements(By.cssSelector("table.dataTable tr.row td:nth-child(5) a")).get(i).click();

                List<String> zones = driver
                        .findElements(By.cssSelector("table#table-zones tr:not(.header) td:nth-child(3)"))
                        .stream()
                        .map(WebElement -> WebElement.getText())
                        .collect(Collectors.toList());
                zones.remove(zones.size() - 1);
                Assert.assertTrue(isCollectionSorted(zones));

                driver.findElement(By.cssSelector("button[name=cancel]")).click();

            }

        }

    }

    @Test
    public void checkSortZones() {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        Assert.assertEquals(driver.findElement(By.cssSelector("td#content h1")).getText(), "Geo Zones");
        //check zones sort
        for (int i = 0; i < driver.findElements(By.cssSelector("table.dataTable tr.row td:nth-child(3) a")).size(); i++) {
            driver.findElements(By.cssSelector("table.dataTable tr.row td:nth-child(3) a")).get(i).click();
            List<String> zones = driver
                    .findElements(By.cssSelector("table#table-zones  td:nth-child(3) option[selected= selected]"))
                    .stream()
                    .map(WebElement -> WebElement.getText())
                    .collect(Collectors.toList());

            Assert.assertTrue(isCollectionSorted(zones));
            driver.findElement(By.cssSelector("button[name=cancel]")).click();


        }

    }


    @AfterMethod
    public void stop() {
        driver.quit();
        driver = null;
    }
}

