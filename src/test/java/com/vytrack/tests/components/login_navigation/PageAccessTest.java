package com.vytrack.tests.components.login_navigation;

import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VyTrackUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PageAccessTest  extends TestBase {
//    WebDriver driver;
//    @BeforeMethod
//    public void setup(){
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.get("http://qa3.vytrack.com/user/login");
//        driver.manage().window().maximize();
//    }
//
//    @AfterMethod
//    public void tearDown(){
//        driver.quit();
//    }
//    @Test
//    public void contractsStoresMan() throws InterruptedException {
//        //1.Login to Vytrack as a store manager
//        VyTrackUtilities.login(driver, "storemanager89", "UserUser123");
//
//        //2.Verify that you can access Vehicle contracts page
//        VyTrackUtilities.selectMenuOption(driver, "Fleet", "Vehicle Contracts");
//        String pageTitle = "All - Vehicle Contract - Entities - System - Car - Entities - System";
//        Assert.assertEquals(driver.getTitle(), pageTitle);
//
//    }

    @Test
    public void contractsSalesMan() throws InterruptedException {
        //1.Login to Vytrack as a sales manager
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.get("url"));
        String username = ConfigurationReader.get("salesmanager_username");
        String password = ConfigurationReader.get("salesmanager_password");

        VyTrackUtilities.login(driver, username, password);
        driver.manage().window().maximize();
        //VyTrackUtilities.waitForUIOverlay();
        //2.Verify that you can access Vehicle contracts page

        VyTrackUtilities.selectMenuOption(driver, "Fleet", "Vehicle Contracts");
        //VyTrackUtilities.waitForUIOverlay();
        String pageTitle = "All - Vehicle Contract - Entities - System - Car - Entities - System";
        //VyTrackUtilities.waitForUIOverlay();
        Assert.assertEquals(driver.getTitle(), pageTitle);
    }

    @Test
    public void contractDriver() throws InterruptedException {
        //1.Login to Vytrack as a driver
        VyTrackUtilities.login(driver, "user156", "UserUser123");

        //2.Verify that you cannot access Vehicle contracts page
        VyTrackUtilities.selectMenuOption(driver, "Fleet", "Vehicle Contracts");

        String expMessage = "You do not have permission to perform this action.";
        String actMessage = driver.findElement(By.xpath("//div[.='You do not have permission to perform this action.']")).getText();
        Assert.assertEquals(expMessage, actMessage);
  }

}
