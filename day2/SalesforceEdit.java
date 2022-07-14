package day2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesforceEdit {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
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
		driver.findElement(By.xpath("//input[@title='Search...']")).sendKeys("Salesforce Automation by Mona");
		driver.findElement(By.xpath("//ul[@class='lookup__list  visible']/li")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//table/tbody)[2]/tr/td[5]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@title='Edit']")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//span[text()='Edit Dashboard Properties']")).click();
		
		
	}

}
