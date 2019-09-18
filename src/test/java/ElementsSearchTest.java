import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ElementsSearchTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private By form = By.id("box-account-login");


    boolean areElementsPresent(WebDriver driver, By locator) {
            return driver.findElements(locator).size() > 0;
        }



    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


    @BeforeMethod
    public void start (){

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

    }

    @Test

    public void loginTest(){
        driver.get("http://localhost/litecart/");
        Assert.assertTrue(areElementsPresent(driver,form));
        WebElement formLogin = driver.findElement(form);
        formLogin.findElement(By.name("email")).sendKeys("email");
        formLogin.findElement(By.name("password")).sendKeys("password");


    }




    @AfterMethod
    public void stop(){
        //driver.quit();
        //driver = null;
    }


}

