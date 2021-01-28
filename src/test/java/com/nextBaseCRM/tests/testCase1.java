package com.nextBaseCRM.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class testCase1 {
    WebDriver driver;

    @BeforeClass
    public void setUps() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://login.nextbasecrm.com/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        WebElement loginUsername = driver.findElement(By.xpath("//input[@name='USER_LOGIN']"));
        loginUsername.sendKeys("helpdesk25@cybertekschool.com");
        WebElement loginPassword= driver.findElement(By.xpath("//input[@name='USER_PASSWORD']"));
        loginPassword.sendKeys("UserUser");
        WebElement loginButton = driver.findElement(By.xpath("//input[@class='login-btn']"));
        loginButton.click();


    }

    @Test
    public void AccessVerification() throws InterruptedException {
        Thread.sleep(3000);
        //put all expected titles in expected ArrayList
        ArrayList<String> expectedTitleList = new ArrayList(Arrays.asList("(35) Portal","(35) My tasks","(35) Chat and Calls","(35) Workgroups and projects","(35) My Drive","(35) hr25@cybertekschool.com: Calendar",
                "(35) Mailbox Integration", "(35) Contact Center","(35) Absence Chart","(35) Company Structure", "(35) Meeting Rooms","(35) Company","(35) All applications", "(35) Assignments"));
        ArrayList<String> actualTitleList = new ArrayList();

        for (int i=1; i<=14; i++) {
            WebElement moreButton = driver.findElement(By.xpath("//span[@class='menu-favorites-more-text']"));
            moreButton.click();
            WebElement modules = driver.findElement(By.xpath("(//span[@class='menu-item-link-text'])[" +i+  "]"));
            modules.click();
            String actualTitle = driver.getTitle();
            //after getting actual title, add in actualTitleList ArrayList
            actualTitleList.add(actualTitle);
            driver.navigate().back();
        }
        System.out.println(actualTitleList);
        //Assert two ArrayList
        Assert.assertEquals(actualTitleList, expectedTitleList);
    }



    @AfterClass
    public void tearDownDriver() {
        driver.close();
    }
}
//ArrayList<String> emails = new ArrayList<String>();
//emails.addAll(Arrays.asList("marketing25@cybertekschool.com", "hr25@cybertekschool.com", "helpdesk25@cybertekschool.com"));