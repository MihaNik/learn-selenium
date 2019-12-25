import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class СreateCustomer {

    private WebDriver driver;
    private WebDriverWait wait;


    private boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    private void login() {

    }

    @BeforeMethod
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        // driver  = new OperaDriver(options);
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void createCustomer() {
        Customer testCustomer = new Customer();
        driver.get("http://litecart.stqa.ru");

        //go to customer form
        driver.findElement(By.cssSelector("form[name=login_form] a")).click();
        Assert.assertTrue(areElementsPresent(driver, By.cssSelector("div#create-account")));

        //без этого не заполняется firstname в chrome 79, не понятно почему...
        //заглушка с которой в хроме работает стабильно
        driver.findElement(By.cssSelector("input[name=tax_id]")).sendKeys("123");
        Assert.assertTrue(areElementsPresent(driver, By.cssSelector("input[name=firstname]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name=firstname]")));
        driver.findElement(By.cssSelector("input[name=firstname]")).click();
        driver.findElement(By.cssSelector("input[name=firstname]")).clear();
        driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys("TestFirstName");
//fill data
        driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys("TestLastName");
        driver.findElement(By.cssSelector("input[name=address1]")).sendKeys("Address1");
        driver.findElement(By.cssSelector("input[name=postcode]")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[name=city]")).sendKeys("SinSity");
        Select country = new Select(driver.findElement(By.cssSelector("select[name=country_code]")));
        country.selectByVisibleText("United States");
        Select state = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        state.selectByVisibleText("California");

        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(testCustomer.getEmail());
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("+79522848333");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(testCustomer.getPassword());
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(testCustomer.getPassword());

        driver.findElement(By.cssSelector("button[name=create_account]")).click();


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id, 'box-account')]//a[contains(text(),'Logout')]")));

        driver.findElement(By.xpath("//div[contains(@id, 'box-account')]//a[contains(text(),'Logout')]")).click();

        System.out.println("Login");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name=login]")));
        Assert.assertTrue(areElementsPresent(driver, By.cssSelector("input[name=email]")));
        driver.findElement(By.cssSelector("input[name=email]")).clear();
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(testCustomer.getEmail());
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(testCustomer.getPassword());
        driver.findElement(By.cssSelector("button[name=login]")).click();


        driver.findElement(By.xpath("//div[contains(@id, 'box-account')]//a[contains(text(),'Logout')]")).click();


    }

    @AfterMethod
    public void stop() {
        //driver.quit();
        //driver = null;
    }


    public class Customer {
        private String email;
        private String password = "123456";

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        private Customer() {
            this.email = getRandomEmail();
        }


        private String getRandomEmail() {
            Random random = new Random();
            int number = random.nextInt(50000);
            return number + "test@gmail.com";
        }
    }
}

