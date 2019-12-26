package pageobject.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class CartPage extends PageObject {


    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "div#box-checkout-cart li.shortcut")
    private List<WebElement> icons;

    @FindBy(css = "button[name=remove_cart_item]")
    private List<WebElement> remove;


    public int getGoodsCount() {
        System.out.println(icons.size());
        return icons.size();
    }


    public void clearCart() {
        int count = getGoodsCount();
        for (int j = 0; j < count - 1; j++) {
            icons.get(j).click();
            WebElement dataTable = driver.findElement(By.cssSelector("table.dataTable.rounded-corners"));
            wait.until(ExpectedConditions.elementToBeClickable(remove.get(j)));
            remove.get(j).click();
            wait.until(ExpectedConditions.stalenessOf(dataTable));
            System.out.println("delete good");
        }

        remove.get(0).click();
        System.out.println("delete last good");

    }


    public void checkClearCart() {
        Assert.assertTrue(!areElementsPresent(driver, driver.findElements(By.cssSelector("table.dataTable.rounded-corners"))));
    }


}

