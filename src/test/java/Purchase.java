import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.support.Color;

public class Purchase {

    private WebDriver driver;
    private WebDriverWait wait;
    By pagePrice = By.cssSelector("div#box-campaigns li.product s");
    By pageDiscPrice = By.cssSelector("div#box-campaigns li.product strong");
    By descPrice = By.cssSelector("div#box-product s");
    By descDiscPrice = By.cssSelector("div#box-product strong.campaign-price");

    By select = By.cssSelector("td.options");


    private boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }


    private void addGood(int num) {
        driver.get("http://litecart.stqa.ru");
        String quant = driver.findElement(By.cssSelector("div#cart span.quantity")).getText();
        driver.findElements(By.cssSelector("div#box-most-popular li.product a.link")).get(num).click();
        if (areElementsPresent(driver, select)) {
            driver.get("http://litecart.stqa.ru");
            num = num + 1;
            driver.findElements(By.cssSelector("div#box-most-popular li.product a.link")).get(num).click();
        }
        driver.findElement(By.cssSelector("button[name= add_cart_product]")).click();
        wait.until((WebDriver d) -> Integer.parseInt(d.findElement(By.cssSelector("div#cart span.quantity")).getText()) == Integer.parseInt(quant) + 1);
        System.out.println("Good add to cart");

    }


    @BeforeMethod
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new FirefoxDriver();
        //driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void purchaseGood() {
        driver.get("http://litecart.stqa.ru");
        for (int i = 0; i < 3; i++) {
            addGood(0);
        }
        driver.findElement(By.cssSelector("div#cart")).click();
        By shortcuts = By.cssSelector("div#box-checkout-cart li.shortcut");
        int goodsCount = driver.findElements(shortcuts).size();
        for (int j = 0; j < goodsCount - 1; j++) {
            driver.findElements(shortcuts).get(j).click();
            WebElement dataTable = driver.findElement(By.cssSelector("table.dataTable.rounded-corners"));
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.cssSelector("button[name=remove_cart_item")).get(j)));
            driver.findElements(By.cssSelector("button[name=remove_cart_item")).get(j).click();
            wait.until(ExpectedConditions.stalenessOf(dataTable));
            System.out.println("delete good");
        }

        driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
        Assert.assertTrue(!areElementsPresent(driver, By.cssSelector("table.dataTable.rounded-corners")));
        System.out.println("delete last good");


    }

    @AfterMethod
    public void stop() {
        // driver.quit();
        //driver = null;
    }
}

