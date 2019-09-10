import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class SimpleTest {

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeMethod
    public void start (){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test

    public void myFirstTest(){
        driver.get("http://google.com/");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    @AfterMethod
    public void stop(){
        driver.quit();
        driver = null;
    }


}
