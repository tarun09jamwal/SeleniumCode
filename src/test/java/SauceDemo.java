import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SauceDemo
{
    static WebDriver driver;

    public static void SetUp()
    {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

    }
    public static void Login()
    {
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
    }
    public static void Verification(String locator,String expected)
    {
        String actual = driver.findElement(By.xpath(locator)).getText();
        Assert.isTrue(actual.contains(expected),"Expected no match");
    }
    public static void Product()
    {
        driver.findElement(By.partialLinkText("Sauce Labs Backpack")).click();
        driver.findElement(By.name("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_badge")).click();
    }
    public static void Verification2(String locator,String expected)
    {
        String actual = driver.findElement(By.xpath(locator)).getText();
        Assert.isTrue(actual.contains(expected),"Expected not match");
    }
    public static void Cart()
    {
        driver.findElement(By.name("checkout")).click();
    }
    public static void yourInformation()
    {
        driver.findElement(By.name("firstName")).sendKeys("Tarun");
        driver.findElement(By.name("lastName")).sendKeys("Jamwal");
        driver.findElement(By.name("postalCode")).sendKeys("175001");
        driver.findElement(By.name("continue")).click();
    }
    public static void Overview()
    {
        driver.findElement(By.name("finish")).click();
    }

    public static void main(String[] args)
    {
        SetUp();
        Login();
        Verification("//span[@class='title']","PRODUCTS");
        Product();
        Verification2("//span[@class='title']","YOUR CART");
        Cart();
        yourInformation();
        Overview();
        driver.close();
    }
}
