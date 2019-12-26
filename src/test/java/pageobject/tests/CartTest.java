package pageobject.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.pages.MainPage;

public class CartTest extends TestBase {


    @Test
    public void workWithCart()  {

app.addGoods(3);
app.clearCart();
    }

}
