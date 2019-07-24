package com.vytrack.tests.smoke_tests;


import com.vytrack.utilities.VerificationUtils;
import com.vytrack.utilities.VyTrackUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MenuOptionsTest {
    WebDriver driver;
    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://qa3.vytrack.com/user/login");
        driver.manage().window().maximize();
    }
    @Test
    public void MenuOptionsDriver() throws InterruptedException{
        //Login as TruckDriver
        VyTrackUtilities.login(driver,"user156", "UserUser123");
        //navigate to fleet, click vehicle
        VyTrackUtilities.selectMenuOption(driver, "Fleet", "Vehicles");

        //verify title
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(),"Car - Entities - System - Car - Entities - System");

        Thread.sleep(2000);
        String expected = driver.findElement(By.className("oro-subtitle")).getText();
        String actual = "Cars";
        Thread.sleep(2000);
        Assert.assertEquals(expected, actual);

        //Navigate to Customers, Accounts
        VyTrackUtilities.selectMenuOption(driver, "Customers", "Account");

        //verify title
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(),"Accounts - Customers");

        String expectedAcc = driver.findElement(By.className("oro-subtitle")).getText();
        String actualAcc = "Accounts";
        Thread.sleep(2000);
        Assert.assertEquals(expectedAcc,actualAcc);

        //Navigate to Customers, click Contacts
        VyTrackUtilities.selectMenuOption(driver, "Customers", "Contacts");

        //verify title
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(),"Contacts - Customers");

        String expectedCusto = driver.findElement(By.className("oro-subtitle")).getText();
        String actualCusto = "Contacts";
        Thread.sleep(2000);
        Assert.assertEquals(expectedCusto,actualCusto);

        //Navigate to Activities, Calendar Events
        VyTrackUtilities.selectMenuOption(driver, "Activities", "Calendar Events");

        //verify title
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(),"Calendar Events - Activities");

        String expectedCal = driver.findElement(By.className("oro-subtitle")).getText();
        String actualCal = "Calendar Events";
        Thread.sleep(2000);
        Assert.assertEquals(expectedCusto,actualCusto);

    }

    @Test
    public void MenuOptionsStoreManager() throws InterruptedException{

        Thread.sleep(2000);
        WebElement userId = driver.findElement(By.id("prependedInput"));
        userId.sendKeys("storemanager89");
        Thread.sleep(2000);
        WebElement password = driver.findElement(By.id("prependedInput2"));
        password.sendKeys("UserUser123");
        Thread.sleep(2000);
        //login
        WebElement signInButton = driver.findElement(By.id("_submit"));
        signInButton.click();

        //navigate to DashBoard verify
        //verify page title Dashboard - Dashboards, verify page name Dashboard
        Assert.assertTrue(driver.getTitle().contains("Dashboard"));
        String expectedDashBoard =driver.findElement(By.className("oro-subtitle")).getText();
        String actualDashBoard = "Dashboard";
        Thread.sleep(2000);
        Assert.assertEquals(actualDashBoard,expectedDashBoard);

        //navigate to fleet vehicles , click vehicles  ,
        // verify page title All - Car - Entities - System - Car - Entities - System, page name All Cars
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//span[@class='title title-level-1'])[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//span[@class='title title-level-2'])[3]")).click();

        Thread.sleep(2000);
        Assert.assertTrue(driver.getTitle().contains("All - Car - Entities - System - Car - Entities - System"));
        Thread.sleep(2000);
        String expectedpageName = driver.findElement(By.className("oro-subtitle")).getText();
        String actulaPageName = "All Cars";
        Assert.assertEquals(expectedpageName, actulaPageName);

        //navigate to customers  , click accounts ,
        // verify page title All - Accounts – Customers, verify page name All Accounts
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//span[@class='title title-level-1'])[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//span[@class='title title-level-2'])[10]")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.getTitle().contains("All - Accounts - Customers"));
        Thread.sleep(2000);
        String expAccouName = driver.findElement(By.className("oro-subtitle")).getText();
        String actuaAccouNam = "All Accounts";
        Assert.assertEquals(expAccouName,actuaAccouNam);

        ////navigate to customers  , click contacts ,
        // verify page title All - Contacts - Customers, verify page name All Contacts
        Thread.sleep(2000);
        driver.findElement(By.linkText("Customers")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[.='Contacts']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.getTitle().contains("All - Contacts - Customers"));
        Thread.sleep(2000);
        String expCusName = driver.findElement(By.className("oro-subtitle")).getText();
        String actuaCusNam = "All Contacts";
        Assert.assertEquals(expCusName,actuaCusNam);

        //navigate to sales , click Open Opportunit
        //verify page title Open Opportunities - Opportunities - Sales, verify page name Open Opportunities
        Thread.sleep(2000);
        driver.findElement(By.linkText("Sales")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[.='Opportunities']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.getTitle().contains("Open Opportunities - Opportunities - Sales"));
        Thread.sleep(2000);
        String expOppName = driver.findElement(By.className("oro-subtitle")).getText();
        String actuaOppNam = "Open Opportunities";
        Assert.assertEquals(expOppName,actuaOppNam);

        //navigate Activities , click call
        //verify page title All - Calls - Activities, page name All Calls
        Thread.sleep(2000);
        driver.findElement(By.linkText("Activities")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[.='Calls']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(), "All - Calls - Activities");
        Thread.sleep(2000);
        String expActName = driver.findElement(By.className("oro-subtitle")).getText();
        String actuaActNam = "All Calls";
        Assert.assertEquals(expActName,actuaActNam);

        //Navigate to Activities , click Calendar Events
        //verify page title Calendar Events - Activities, page name All Calendar Events
        Thread.sleep(2000);
        driver.findElement(By.linkText("Activities")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[.='Calendar Events']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(), "All - Calendar Events - Activities");
        Thread.sleep(2000);
        String expActCalName = driver.findElement(By.className("oro-subtitle")).getText();
        String actuaActCalNam = "All Calendar Events";
        Assert.assertEquals(expActCalName,actuaActCalNam);
    }
//     @AfterMethod
//      public void close(){
//       driver.quit();
//    }

}
