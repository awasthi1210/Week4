package day2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBus {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.redbus.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		// Enter From -Madiwala Bangalore
		Actions action = new Actions(driver);
		WebElement from_location_ele = driver.findElement(By.xpath("//label[text()='FROM']/preceding-sibling::input"));
		from_location_ele.sendKeys("Madiwala");
		Thread.sleep(2000);
		WebElement suggestedOption = driver
				.findElement(By.xpath("//label[text()='FROM']/preceding-sibling::input/following-sibling::ul/li"));
		action.moveToElement(suggestedOption).click().build().perform();

		// Enter To Koyambedu Chennai
		driver.findElement(By.xpath("//section[@id='search']//div//div[2]/div/input")).sendKeys("Koyambedu");
		Thread.sleep(2000);
		WebElement SuggestOption_To = driver.findElement(By.xpath("//section[@id='search']//div//div[2]/div/input/following-sibling::ul/li"));
		action.moveToElement(SuggestOption_To).click().build().perform();

		// Select the Date 10-Jun-2022
		driver.findElement(By.id("onward_cal")).click();
		driver.findElement(By.xpath("//table/tbody/tr[5]/td[7]")).click();

		// Click Search buses
		driver.findElement(By.xpath("//button[text()='Search Buses']")).click();

		// Click After 6pm under Departure time
		Thread.sleep(5000);
         driver.findElement(By.xpath("//div[text()='DEPARTURE TIME']/following-sibling::ul/li[4]")).click();
         
         //Click Sleeper under Bus types
         Thread.sleep(2000);
         WebElement sleeper_checkbox = driver.findElement(By.cssSelector("label[title='SLEEPER']"));
         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("window.scrollBy(0,100)");
         Thread.sleep(2000);
         sleeper_checkbox.click();
         
         //Select the Primo
         driver.findElement(By.xpath("//UL[@CLASS='tileContainer clearfix']/li[1]")).click();
         
         //Get the number of buses found
        String buses_Found= driver.findElement(By.xpath("//span[@class='f-bold busFound']")).getText();
        System.out.println("Total number of buses is : ");
         
        
        //Get the Bus fare and sort them in ascending order
        driver.findElement(By.xpath("//a[text()='Fare']")).click();
       List<WebElement> listOfFare= driver.findElements(By.xpath("//div[@class='fare d-block']"));
       for(WebElement Fare : listOfFare) {
    	String FareText= Fare.getText();
    	if(FareText.contains("INR")) {
    		String finalPrice=FareText.substring(4);
    		System.out.println(finalPrice);
    	}
    	else {
    		System.out.println(FareText);
    	}
    	
       }
       driver.quit();

	}

}
