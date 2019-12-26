package pageobject.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class GoodPage extends PageObject {


    public GoodPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "div.buy_now tr")
    private List<WebElement> options;

    @FindBy(css = "select[name='options[Size]']")
    private WebElement select;

    public boolean checkIsSize() {
        return options.size() == 2;
    }


    public void addGood() {
        if (checkIsSize()) {
            Select size = new Select(select);
            size.selectByValue("Large");
        }
        driver.findElement(By.cssSelector("button[name= add_cart_product]")).click();
    }


}

