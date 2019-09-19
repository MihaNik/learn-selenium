import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ProductsTest2 {

    private WebDriver driver;
    private WebDriverWait wait;

    private boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
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
    public void menuNavigate() {
        driver.get("http://localhost/litecart/");

        driver.findElements(By.cssSelector("li.product"));
        int productsCount = driver.findElements(By.cssSelector("li.product")).size();
        System.out.println(productsCount);


        for (int i = 0; i < driver.findElements(By.cssSelector("li.product")).size(); i++) {
            int size = driver.findElements(By.cssSelector("li.product"))
                    .get(i)
                    .findElements(By.cssSelector("div.sticker"))
                    .size();
            Assert.assertTrue(size == 1);

        }


        driver.findElements(By.cssSelector("li.product"))
                .forEach(WebElement -> WebElement
                        .findElements(By.cssSelector("div.sticker"))
                        .size());


        List<Integer> sizes = driver.findElements(By.cssSelector("li.product"))
                .stream()
                .map(WebElement -> WebElement
                        .findElements(By.cssSelector("div.sticker"))
                        .size())
                .collect(Collectors.toList());
        System.out.println(sizes);




        Assert.assertTrue(driver.findElements(By.cssSelector("li.product"))
                .stream()
                .map(product -> product.findElements(By.cssSelector("div.sticker")))
                .map(el->el.size())
                .allMatch(i -> i.equals(1)));

        Assert.assertTrue(driver.findElements(By.cssSelector("li.product"))
                .stream()
                .allMatch(product -> product
                        .findElements(By.cssSelector("div.sticker"))
                        .size() ==1)
            );


    }

    @AfterMethod
    public void stop() {
        driver.quit();
        driver = null;
    }
}

