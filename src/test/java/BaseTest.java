import org.junit.Test;

public class BaseTest extends SimpleTest {
    @Test
    public void checkAndTest() throws InterruptedException
    {

        controlTitle();
        controlLoginSite();
        controlSearchComp();
        controlSecPage();
        controlRandomPrdct();
        controlDetailPrice();
        controlAddCart();
        controlGoToCart();
        controlDropDownValue();
        controlDeleteCart();

    }
}
