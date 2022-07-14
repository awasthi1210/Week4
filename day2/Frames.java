package day2;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Frames {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.leafground.com/pages/Window.html");

		// Click button to open home page in New Window
		driver.findElement(By.id("home")).click();
		String parentWindowHandle = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println("total windows " + windowHandles.size());
		for (String windowHandle : windowHandles) {
			if (!windowHandle.equals(parentWindowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		driver.close();
		driver.switchTo().window(parentWindowHandle);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[@onclick='openWindows()']")).click();
		String parentOfMultipleWindoes = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		//// Find the number of opened windows
		int sizeOfOpenWindows = allWindowHandles.size() - 1;
		System.out.println(sizeOfOpenWindows);
		// //Close all except this window
		for (String windowHandle : allWindowHandles) {
			if (!windowHandle.equals(parentOfMultipleWindoes)) {
				driver.switchTo().window(windowHandle);
				driver.close();
			}

		}
		// Wait for 2 new Windows to open
		driver.switchTo().window(parentOfMultipleWindoes);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Wait for 5 seconds']")).click();

	}
}
