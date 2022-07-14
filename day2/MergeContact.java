package day2;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		// Launch URL "http://leaftaps.com/opentaps/control/login"
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		// Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		// Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();

		// Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();
		// Click on contacts Button
		driver.findElement(By.xpath("//a[text()= 'Contacts']")).click();
		// Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[text()= 'Merge Contacts']")).click();
		// Click on Widget of From Contact
		driver.findElement(By.xpath("//img[@alt= 'Lookup']")).click();
        //Click on First Resulting Contact
		String parentHandle = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowHandle : windowHandles) {

			if (!windowHandle.equals(parentHandle)) {
				driver.switchTo().window(windowHandle);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@class='x-grid3-body']//table/tbody/tr/td//a")).click();
				driver.switchTo().window(parentHandle);

			}
		}

		Thread.sleep(2000);
		// Click on Widget of To Contact
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		Thread.sleep(2000);
////Click on Second Resulting Contact
		Set<String> windowHandles1 = driver.getWindowHandles();
		for (String windowHandle : windowHandles1) {
			if (!windowHandle.equals(parentHandle)) {
				driver.switchTo().window(windowHandle);
				driver.findElement(By.xpath("(//table[@class='x-grid3-row-table'])[2]/tbody/tr/td/div//a")).click();
				break;
			}

		}
		driver.switchTo().window(parentHandle);
		// Click on Merge button using Xpath Locator
		Thread.sleep(2000);
		driver.findElement(By.linkText("Merge")).click();

		// Accept the Alert
		driver.switchTo().alert().accept();
		// Verify the title of the page
		String title = driver.getTitle();

		if (title.equals("View Contact | opentaps CRM")) {

			System.out.println("You are on the correct page");
		} else {
			System.out.println("You are on an incorrect page :   " + title);
		}

	}
}
