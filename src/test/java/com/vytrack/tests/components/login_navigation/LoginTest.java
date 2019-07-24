package com.vytrack.tests.components.login_navigation;

import com.vytrack.utilities.Library;
import com.vytrack.utilities.VerificationUtils;
import com.vytrack.utilities.VyTrackUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;
    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://qa3.vytrack.com/user/login");
        driver.manage().window().maximize();
    }
    @Test
    public void loginPositive(){
        VyTrackUtilities.login(driver, "storemanager89", "UserUser123");
       String name = "dropdown-toggle";
       // System.out.println(name);
        String storeManagerName = driver.findElement(By.className(name)).getText();
        Assert.assertTrue(VerificationUtils.isElementDisplayed(driver, By.className(name)));
       //3. Verify Dashboard page is open
        String pageTitle = "oro-subtitle";
        Assert.assertTrue(VerificationUtils.isElementDisplayed(driver, By.className(pageTitle)));

        //4. Log out
        logout(name);

        //5. Login to Vytrack as a sales manager
        VyTrackUtilities.login(driver, "salesmanager256", "UserUser123");
        String salesManagerName = driver.findElement(By.className(name)).getText();

        //6. Verify Dashboard page is open
         pageTitle = "oro-subtitle";
        Assert.assertTrue(VerificationUtils.isElementDisplayed(driver, By.className(pageTitle)));
        Library.sleep(2);

        //7. A different name should be displayed on top right
        Assert.assertTrue(VerificationUtils.isElementDisplayed(driver,By.className(name)),salesManagerName);

        //8. Log out
        logout(name);

        //9.Login to Vytrack as a driver
        VyTrackUtilities.login(driver, "user156", "UserUser123");

        //10.  Verify Dashboad/Quick Launchpad page is open
        String QuickLanchPad = "oro-subtitle";
        Assert.assertTrue(VerificationUtils.isElementDisplayed(driver, By.className(QuickLanchPad)));

        //11.A different name should be displayed on top right
        Assert.assertTrue(VerificationUtils.isElementDisplayed(driver,By.className(name)),salesManagerName);


    }
    @Test
    public void Negative(){

        VyTrackUtilities.login(driver, "storemanager89", "UserUser12");
        String message = driver.findElement(By.xpath("//div[.='Invalid user name or password.']")).getText();
        System.out.println(message);
        // 4. Message Invalid user name or password. should be displayed
        Assert.assertEquals(message, "Invalid user name or password.");

        //5. Page title and url should be same
        Assert.assertEquals(driver.getTitle(), "Login");
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "http://qa3.vytrack.com/user/login");

    }


    public void logout(String name) {
        driver.findElement(By.className(name)).click();
        Library.sleep(2);
        driver.findElement(By.xpath("//a[@class='no-hash']")).click();
    }

}
