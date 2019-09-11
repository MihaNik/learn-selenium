import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginExampleTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private String pageUrl = "http://localhost/litecart/admin/";

    @BeforeMethod
    public void start (){
        driver = new ChromeDriver();
    }
    @Test
    public void myFirstTest(){
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        String url = driver.findElement(By.xpath("//div[@class='logotype']/a")).getAttribute("href");
        System.out.println(url);
        Assert.assertEquals(url, pageUrl);
    }

    @AfterMethod
    public void stop(){
       driver.quit();
       driver = null;
    }
}
