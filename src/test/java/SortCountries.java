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
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SortCountries {

    private WebDriver driver;
    private WebDriverWait wait;
    private By countriesList = By.cssSelector("table.dataTable tr.row");

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

    }

    @Test
    public void checkSort() {
        driver.get("http://localhost/litecart/admin/login.php");
        //login with app
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Assert.assertTrue(areElementsPresent(driver, By.cssSelector("ul#box-apps-menu")));
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        Assert.assertEquals(driver.findElement(By.cssSelector("td#content h1")).getText(), "Countries");

        List<String> countries = driver
                .findElements(countriesList)
                .stream()
                .map(WebElement -> WebElement.findElement(By.cssSelector("td a")).getText())
                .collect(Collectors.toList());

        Assert.assertTrue(isCollectionSorted(countries));


        for (int i = 0; i < driver.findElements(countriesList).size(); i++) {
            String zoneCount = driver.findElements(countriesList).get(i).findElements(By.cssSelector("td")).get(5).getText();
            if(!Objects.equals(Integer.parseInt(zoneCount), 0)){
                System.out.println("count i = " +i );

            }





        }


    }

    @AfterMethod
    public void stop() {
        driver.quit();
        driver = null;
    }
}

