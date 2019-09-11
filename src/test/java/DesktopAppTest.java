import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class DesktopAppTest {

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeMethod
    public void start (){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    @Test

    public void debugger1(){
        //driver.get("http://www.websocket.org/echo.html/");
        //driver.findElement(By.name("q")).sendKeys("webdriver");
        //driver.findElement(By.linkText(("websocket.org Echo Test - Powered by Kaazing"))).click();
        //driver.findElement(By.xpath("//a[@href='/book.html']")).click();
        //driver.findElement(By.id("navigation"));

        System.out.println("Test");


       // String addr = "http://localhost:9222/devtools/inspector.html?ws=localhost:9222/devtools/page/C73B2F5FCD10DAB9E119800C38FD21B0";


       driver.get("http://localhost:9223");
       driver.findElement(By.linkText(("websocket.org Echo Test - Powered by Kaazing"))).click();



        driver.findElement(By.id("navigation"));



        driver.findElement(By.xpath("//a[@href='/book.html']")).click();



        //wait.until(titleIs("webdriver - Поиск в Google"));
    }



/*    @Test

    public void debugger(){
        driver.get("http://localhost:9222/");
        //driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.linkText(("websocket.org Echo Test - Powered by Kaazing"))).click();
        driver.findElement(By.xpath("//a[@href='/book.html']")).click();

        //wait.until(titleIs("webdriver - Поиск в Google"));
    }*/

    @AfterMethod
    public void stop(){
        //driver.quit();
        // driver = null;
    }


}

