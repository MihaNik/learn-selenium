import org.beryx.awt.color.ColorFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.support.Color;

public class CheckGood {

    private WebDriver driver;
    private WebDriverWait wait;
    By pagePrice = By.cssSelector("div#box-campaigns li.product s");
    By pageDiscPrice = By.cssSelector("div#box-campaigns li.product strong");
    By descPrice = By.cssSelector("div#box-product s");
    By descDiscPrice = By.cssSelector("div#box-product strong.campaign-price");


    private boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }


    public boolean isColorGrey(String color) {
        Color rgbColor = ColorFactory.valueOf(color);
        boolean grey = rgbColor.getBlue() == rgbColor.getGreen() & rgbColor.getGreen() == rgbColor.getRed();
        return grey;

    }


    public boolean isColorRed(String color) {
        Color rgbColor = ColorFactory.valueOf(color);
        boolean red = rgbColor.getBlue() == 0 & rgbColor.getGreen() == 0;
        return red;

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
    public void checkStickers() {
        driver.get("http://localhost/litecart/");
// main page
        String name = driver.findElement(By.cssSelector("div#box-campaigns li.product div.name")).getText();

        String price = driver.findElement(pagePrice).getText();
        String priceColor = driver.findElement(pagePrice).getCssValue("color");

        Assert.assertTrue(isColorGrey(priceColor)); //grey


        String priceSize = driver.findElement(pagePrice).getCssValue("font-size").replace("px", "");
        String priceLine = driver.findElement(pagePrice).getCssValue("text-decoration-line");
        Assert.assertEquals(priceLine, "line-through"); //line


        String priceDisc = driver.findElement(pageDiscPrice).getText();
        String priceDiscColor = driver.findElement(pageDiscPrice).getCssValue("color");
        Assert.assertTrue(isColorRed(priceDiscColor)); //red

        String priceDiscSize = driver.findElement(pageDiscPrice).getCssValue("font-size").replace("px", "");
        String priceDiscBold = driver.findElement(pageDiscPrice).getCssValue("font-weight");

        Assert.assertTrue(Integer.parseInt(priceDiscBold) > 400); //bold
        Assert.assertTrue(Double.parseDouble(priceDiscSize) > Double.parseDouble(priceSize)); //size


        driver.findElement(By.cssSelector("div#box-campaigns li.product a")).click();


//product page
        String name2 = driver.findElement(By.cssSelector("div#box-product h1.title")).getText();

        String price2 = driver.findElement(descPrice).getText();
        String priceColor2 = driver.findElement(descPrice).getCssValue("color");
        Assert.assertTrue(isColorGrey(priceColor2)); //grey
        String priceSize2 = driver.findElement(descPrice).getCssValue("font-size").replace("px", "");
        String priceLine2 = driver.findElement(descPrice).getCssValue("text-decoration-line");
        Assert.assertEquals(priceLine2, "line-through"); //line


        String priceDisc2 = driver.findElement(descDiscPrice).getText();
        String priceDiscColor2 = driver.findElement(descDiscPrice).getCssValue("color");
        Assert.assertTrue(isColorRed(priceDiscColor2)); //red
        String priceDiscSize2 = driver.findElement(descDiscPrice).getCssValue("font-size").replace("px", "");
        String priceDiscBold2 = driver.findElement(descDiscPrice).getCssValue("font-weight");

        Assert.assertTrue(Integer.parseInt(priceDiscBold2) > 400); //bold
        Assert.assertTrue(Double.parseDouble(priceDiscSize2) > Double.parseDouble(priceSize2)); //size

// equals
        Assert.assertEquals(name, name2);
        Assert.assertEquals(price, price2);
        Assert.assertEquals(priceDisc, priceDisc2);
    }

    @AfterMethod
    public void stop() {
        driver.quit();
        driver = null;
    }
}

