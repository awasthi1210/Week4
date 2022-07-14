package day2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForce {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.addArguments("--disable-notifications");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
	
		driver.get("https://login.salesforce.com/ ");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password@123");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(10000);
		
		// Click on the toggle menu button from the left corner
		driver.findElement(By.cssSelector(".slds-icon-waffle")).click();
         Thread.sleep(5000);
         
         //Click View All and click Dashboards from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@type='search'])[3]")).sendKeys("Dashboard");
		driver.findElement(By.xpath("//mark[text()='Dashboard']")).click();
		Thread.sleep(5000);
		
		//Click on the New Dashboard option 
		driver.findElement(By.xpath("//div[@title='New Dashboard']")).click();
		Thread.sleep(2000);
		
		//Handle the frame
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='dashboard']")));
		
		//Enter Name as 'Salesforce Automation by Your Name ' and Click on Create.
		driver.findElement(By.id("dashboardNameInput")).sendKeys("Salesforce Automation by Mona");
		driver.findElement(By.id("submitBtn")).click();		
		new WebDriverWait(driver, Duration.ofMillis(15000)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe")));
	    driver.findElement(By.xpath("//button[text()='Save']")).click();
		
        driver.close();
	}

}

