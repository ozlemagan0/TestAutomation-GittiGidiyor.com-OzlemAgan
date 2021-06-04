package BaseClass;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static Cons.Cons.*;


public class BaseClass {
    public WebDriver webDriver;

    @Before
    public void main()
    {

        WebDriverWait webDriverWait;
        String baseUrl = "https://www.gittigidiyor.com";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        String Path = "drivers/chromedriver";

        if (Platform.getCurrent().toString().contains("WIN")) {
            Path = "drivers/chromedriver.exe";
        }
        System.setProperty("webdriver.chrome.driver", Path);
        options.setExperimentalOption("w3c", false);
        options.addArguments("--disable-notifications");
        options.addArguments("disable-popup-blocking");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        webDriver = new ChromeDriver(capabilities);
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 45, 150);
        webDriver.manage().window().maximize();
        webDriver.get(baseUrl);


    }

    public void checkUrl()
    {

        Assert.assertTrue(URL.contains("https://www.gittigidiyor.com/"));
        System.out.println("Homepage Checked."+ URL);

    }
    public void checkLogin()
    {

        Assert.assertNotNull(LOGINCONTROL);
        System.out.println("Access Control Done."+ LOGINCONTROL);

    }

    public void checkSecondPage()
    {

        Assert.assertTrue(SEARCHPAGE.contains("https://www.gittigidiyor.com/arama/?k=Bilgisayar&sf=2"));
        System.out.println("Access to the Second Page is Controlled."+ SEARCHPAGE);

    }


    public void checkCart()
    {

        Assert.assertTrue(EMPTYBASKET.contains("Sepetinizde ürün bulunmamaktadır."));
        System.out.println("No Product Available. Text Checked"+ EMPTYBASKET);

    }

    public void ElementToBeClickableAndClick(By element)
    {

        WebDriverWait wait = new WebDriverWait(webDriver,30);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        System.out.println(element+" ");

    }

    public void ElementSendKey(By element,String sendkey)
    {

        WebDriverWait wait = new WebDriverWait(webDriver,30);
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(sendkey);
        System.out.println(element+"The page was expected to be clickable and clicked.");

    }
    public boolean IsElementVisible(By element)
    {

        try
        {
            WebDriverWait wait = new WebDriverWait(webDriver,1);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
            return false;
        }
        catch (Exception e)
        {
            return true;
        }

    }
    private WebElement findElement(By element)
    {

        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);
        WebElement webElement = webDriverWait.
                until(ExpectedConditions.presenceOfElementLocated(element));
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;

    }
    public double priceConvertDouble(By priceText)
    {

        String[] elementStringList = findElement(priceText).getText().trim().split(" ");
        String elementString = elementStringList[0].replaceAll(",", "");
        return Double.parseDouble(elementString);

    }

    @After
  public void quit() throws InterruptedException
    {
     Thread.sleep(1000);
     webDriver.quit();
   }
}
