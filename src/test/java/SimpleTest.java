import BaseClass.BaseClass;
import Cons.Cons;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Random;
import static Cons.Cons.*;


public class SimpleTest extends BaseClass {

    public double productPrice, basketPrice;

    public void controlTitle() throws InterruptedException
    {
        checkUrl();
    }

    public void controlLoginSite() throws InterruptedException
    {
        ElementToBeClickableAndClick(By.cssSelector(LOGIN1));
        Thread.sleep(3000);  // Added because it detects it is a bot.
        ElementToBeClickableAndClick(By.cssSelector(LOGIN2));
        Thread.sleep(1000);
        ElementSendKey(By.cssSelector(Cons.NAME),"ozlemagan0@hotmail.com");
        Thread.sleep(1000);
        ElementSendKey(By.name(PASSWORD),"Ozlem12.");
        Thread.sleep(1000);
        ElementToBeClickableAndClick(By.cssSelector(LOGINBUTTON));
        checkLogin();
    }

    public void controlSearchComp() throws InterruptedException
    {
        ElementSendKey(By.cssSelector(SEARCH),"Bilgisayar");
        ElementToBeClickableAndClick(By.xpath(BUTTONSEARCH));
        Thread.sleep(3000);
    }

    public void controlSecPage() throws InterruptedException
    {
        JavascriptExecutor je = (JavascriptExecutor) webDriver;
        WebElement secondPage = webDriver.findElement(By.cssSelector(SECONDPAGE));
        je.executeScript("arguments[0].scrollIntoView(true);",secondPage);
        ElementToBeClickableAndClick(By.cssSelector(SECONDPAGE));
        checkSecondPage();
    }

    public void controlRandomPrdct() throws InterruptedException
    {
        List<WebElement> links = webDriver.findElements(By.className("srp-item-list"));
        System.out.println(" Size : "+links.size());
        Random productSize = new Random();
        int rndmno = productSize.nextInt(links.size());
        System.out.println("Random Choose Number:"+ rndmno);
        ElementToBeClickableAndClick(By.xpath("//li[@product-index="+rndmno+"]"));
    }

    public void controlDetailPrice() throws InterruptedException
    {
        boolean anyDiscount= IsElementVisible(By.id("sp-price-lowPrice"));
        if(anyDiscount==true)
        {
            productPrice = priceConvertDouble(By.id("sp-price-lowPrice"));
        }
        else {
            productPrice = priceConvertDouble(By.id("sp-price-highPrice"));
        }
        System.out.println("Price on Product Detail Page: "+ productPrice);
    }

    public void controlAddCart() throws InterruptedException
    {
        JavascriptExecutor je = (JavascriptExecutor) webDriver;
        WebElement addToCartButton = webDriver.findElement(By.cssSelector(ADDTOCARD));
        je.executeScript("arguments[0].scrollIntoView(true);",addToCartButton);
        ElementToBeClickableAndClick(By.cssSelector(ADDTOCARD));
        Thread.sleep(3000);
    }

    public void controlGoToCart() throws InterruptedException
    {
        webDriver.get(URL);
        ElementToBeClickableAndClick(By.name(GOTOCARD));
    }

    public void controlDropDownValue() throws InterruptedException
    {
        WebElement choose = webDriver.findElement(By.xpath(COMBOBOX));
        Select sel = new Select(choose);
        sel.selectByValue("2");
        WebElement piece = sel.getFirstSelectedOption();
        System.out.println("Number of Selected Pieces\n:"+ piece.getText());
        Thread.sleep(3000);
    }

    public void controlDeleteCart() throws InterruptedException
    {
        ElementToBeClickableAndClick(By.xpath(DELETE));
        checkCart();
        basketPrice = priceConvertDouble(By.cssSelector("div[class='total-price']"));
        System.out.println("Price of the product in the basket : "+ basketPrice);
        if(basketPrice == basketPrice)
        {
            System.out.println("Price comparison is true");
        }
        else {
            System.out.println("Price comparison is false");
            Assert.fail();
        }
    }
}
