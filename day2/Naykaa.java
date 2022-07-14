package day2;

import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Naykaa {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
		driver.get("https://www.nykaa.com/");
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//a[text()='brands']"));

		action.moveToElement(element).build().perform();
		Thread.sleep(2000);
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris", Keys.ENTER);
		driver.findElement(By.xpath("//div[@class='css-ov2o3v']/a")).click();
		boolean isContains = driver.getTitle().contains("L'Oreal Paris");
		System.out.println(isContains);
		Thread.sleep(3000);
		// Click sort By and select customer top rated
		driver.findElement(By.id("filter-sort")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();

		// Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();

		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();

		// Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();

		// check whether the Filter is applied with Shampoo
		List<WebElement> findElements = driver.findElements(By.xpath("//div[@class='css-19j3ean']"));
		for (WebElement containShampoo : findElements) {
			String text = containShampoo.getText();

			if (text.contains("Shampoo")) {
				System.out.println("Filter is applied with Shampoo");
			} else {
				System.out.println("Filter isn't applied with Shampoo");
			}

		}

		// Click on L'Oreal Paris Colour Protect Shampoo
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='css-qlopj4']")).click();

		// GO to the new window and select size as 175ml
		String ParentHandle = driver.getWindowHandle();
		Set<String> AllHandles = driver.getWindowHandles();
		for (String windowHandle : AllHandles) {
			if (!windowHandle.equals(ParentHandle)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		//Select price as 175ml
		Thread.sleep(2000);
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@class='css-2t5nwu']")));
		dropdown.selectByIndex(1);
		// Print the MRP of the product
		String priceOfShampoo = driver.findElement(By.xpath("//span[text()='MRP:']/following-sibling::span")).getText();
		priceOfShampoo.substring(1);
		System.out.println("price of the shampoo is Rs"+priceOfShampoo.substring(1));

		// Click on ADD to BAG
		driver.findElement(By.xpath("//span[text()='Add to Bag']")).click();

		// Go to Shopping Bag
		driver.findElement(By.xpath("//span[@class='cart-count']")).click();

		// Print the Grand Total amount
		Thread.sleep(2000);
		WebElement frame= driver.findElement(By.xpath("//iframe[contains(@src,'mobileCartIframe?')]"));
		driver.switchTo().frame(frame);
		String price= driver.findElement(By.xpath("//span[text()='Grand Total']//following-sibling::div")).getText();
		String piceString=price.substring(1, price.length());
	    System.out.println("Grand Total of the shampoo is Rs "+ piceString);
	    
	    //Click Proceed
	    driver.findElement(By.xpath("//div[@class='second-col']/button/span")).click();
	    
	    // Click on Continue as Guest
	    driver.findElement(By.xpath("//button[@type='button']/i")).click();
	    
	    //Check if this grand total is the same in step 14
	   String expectedTotal = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div")).getText();
	   String expectedGrandTotal= expectedTotal.substring(1);
	   System.out.println(expectedGrandTotal);
	  boolean isSameGrandTotal= expectedGrandTotal.equals(piceString);
	  System.out.println("The both Grand total are same is the "+ isSameGrandTotal);
	  
     //Close all windows
	  driver.quit();
	  
		
        
        
      	}

}
