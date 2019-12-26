package pageobject.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends PageObject {


    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "div#cart span.quantity")
    private WebElement quantity;

    @FindBy(css = "div#box-most-popular li.product a.link")
    private List<WebElement> goodLinks;

    @FindBy(css = "div#cart")
    private WebElement cart;


    public void open() {
        driver.get("http://litecart.stqa.ru/en/");
    }


    public Integer getGoodsCount() {
        String q = quantity.getText();
        return Integer.parseInt(q);
    }

    public void selectGood() {
       goodLinks.get(0).click();
    }



    public void gotToCart(){
       cart.click();
    }

}

