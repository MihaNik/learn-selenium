package pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Application {

    private WebDriver driver;
    private WebDriverWait wait;


    private MainPage mainpage;
    private GoodPage goodpage;
    private CartPage cartpage;


    public Application() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
       // driver = new ChromeDriver(options);
        driver= new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        mainpage = new MainPage(driver);
        goodpage = new GoodPage(driver);
        cartpage = new CartPage(driver);

    }

    public void quit() {
        driver.quit();
    }


    public void addGoodToCart() {

        mainpage.open();
        mainpage.selectGood();
        int quantity = mainpage.getGoodsCount();
        goodpage.addGood();
        wait.until((WebDriver d) -> mainpage.getGoodsCount() == quantity + 1);
        System.out.println("Good add to cart");


    }


    public void addGoods(int goodsNumber) {
        for (int i = 0; i < goodsNumber; i++) {
            addGoodToCart();
        }
    }

    public void clearCart(){
        mainpage.gotToCart();
        cartpage.clearCart();
        cartpage.checkClearCart();

    }


}