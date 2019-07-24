package com.vytrack.tests.components.activites;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VyTrackUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.vytrack.utilities.VyTrackUtilities.waitForUIOverlay;

public class DateAndTime extends TestBase {

    @Test
    public void AutoAdjust() throws InterruptedException {
        driver.get(ConfigurationReader.get("url"));
        String username = ConfigurationReader.get("salesmanager_username");
        String password = ConfigurationReader.get("salesmanager_password");

        VyTrackUtilities.login(driver, username, password);
        driver.manage().window().maximize();

        Actions actions = new Actions(driver);
        // Hover to Activities
        WebElement firstOne = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[5]"));
        WebElement secondOne = driver.findElement(By.xpath("//span[contains (text(), 'Calendar Events')]"));

        actions.moveToElement(firstOne).perform();

        wait.until(ExpectedConditions.elementToBeClickable(secondOne));
        secondOne.click();
Thread.sleep(5000);
        //      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.main-group.btn-primary.pull-right")));
        driver.findElement(By.cssSelector("a[title='Create Calendar event']")).click();

//       4. Change the start date to future date
       Thread.sleep(5000);
        driver.findElement(By.xpath("//input[contains(@id,'date_selector_oro_calendar_event_form_start-uid')]")).click();

        Thread.sleep(5000);

        driver.findElement(By.xpath("//a[.='30']")).click();

        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[contains(@id,'date_selector_oro_calendar_event_form_end-uid-')]")).click();

       Thread.sleep(5000);
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath("//a[.='30']")).getText(),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());


    }

    @Test
    public void autoAdjust2() throws InterruptedException {
        driver.get(ConfigurationReader.get("url"));
        String username = ConfigurationReader.get("salesmanager_username");
        String password = ConfigurationReader.get("salesmanager_password");

        VyTrackUtilities.login(driver, username, password);
        driver.manage().window().maximize();

        Actions actions = new Actions(driver);
        // Hover to Activities
        WebElement firstOne = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[5]"));
        WebElement secondOne = driver.findElement(By.xpath("//span[contains (text(), 'Calendar Events')]"));

        actions.moveToElement(firstOne).perform();

        waitForUIOverlay();
        secondOne.click();
        Thread.sleep(5000);

        driver.findElement(By.cssSelector("a[title='Create Calendar event']")).click();

//        4. Change the start time to any other time
        waitForUIOverlay();
        //wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[id^=time_selector_oro_calendar_event_form_start-]")))).click();
        driver.findElement(By.cssSelector("input[id^=time_selector_oro_calendar_event_form_start-]")).click();
        waitForUIOverlay();
        WebElement timeElement = driver.findElement(By.xpath("//li[text()='8:00 PM']"));
        //VytrackUtils.waitForUIOverlay();
        BrowserUtils.scrollToElement(timeElement);
        waitForUIOverlay();
        String startTimeValue= timeElement.getText();
        System.out.println("startTimeValue = " + startTimeValue);
        timeElement.click();
        int startTimeNum = Integer.parseInt(startTimeValue.substring(0,1));
//        5. Verify that end time changes exactly 1 hours later
        waitForUIOverlay();
        driver.findElement(By.cssSelector("input[id^='time_selector_oro_calendar_event_form_end-']")).click();
        waitForUIOverlay();
        String endTimeValue = driver.findElement(By.xpath("(//li[text()='9:00 PM'])[2]")).getText();
        System.out.println("endTimeValue = " + endTimeValue);
        int endTimeNum = Integer.parseInt(endTimeValue.substring(0,1));
        int result = startTimeNum - endTimeNum;
        Assert.assertTrue(result < 1);
    }

    @Test
    public void DateTimeEnddat_timeautoadjust() throws InterruptedException {
        // 1.Log in as Valid user
        // 2.Go to Activities -> Calendar Events3.Click on create new calendar event
        driver.get(ConfigurationReader.get("url"));
        String username = ConfigurationReader.get("salesmanager_username");
        String password = ConfigurationReader.get("salesmanager_password");
        VyTrackUtilities.login(driver, username, password);
        driver.manage().window().maximize();
        Actions actions = new Actions(driver);
        // Hover to Activities
        WebElement firstOne = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[5]"));
        WebElement secondOne = driver.findElement(By.xpath("//span[contains (text(), 'Calendar Events')]"));
        actions.moveToElement(firstOne).perform();
        wait.until(ExpectedConditions.elementToBeClickable(secondOne));
        secondOne.click();
        waitForUIOverlay();
        driver.findElement(By.cssSelector(".btn.main-group.btn-primary.pull-right")).click();
        //3
        // click on first time field
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[contains(@id,'time_selector_oro_calendar_event_form_start-uid')]")).click();
        // click on //li[.='11:30 PM']
        driver.findElement(By.xpath("//li[.='11:30 PM']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[contains(@id,'time_selector_oro_calendar_event_form_end-uid-')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//li[.='12:30 AM'])[2]")).click();
        String secondTime= driver.findElement(By.xpath("(//li[.='12:30 AM'])[2]")).getAttribute("innerHTML");
        Thread.sleep(2000);
        //read today
        String today = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[3]/a")).getAttribute("innerHTML");
        Thread.sleep(1000);
        //read tomorrow
        String tomorrow=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[4]/a")).getAttribute("innerHTML");
        Assert.assertTrue(Integer.parseInt(today)+1==Integer.parseInt(tomorrow));
        Assert.assertTrue(secondTime.equals("12:30 AM"));
    }

}