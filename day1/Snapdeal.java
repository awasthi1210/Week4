package day1;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import com.google.common.collect.Comparators;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();

		// Click on Men's Fashion
		driver.findElement(By.xpath("//span[text()=\"Men's Fashion\"]")).click();

		// Go to Sports Shoes
		driver.findElement(By.xpath("(//span[text()='Sports Shoes'])")).click();
		Thread.sleep(2000);

		// Get the count of the sports shoes
		WebElement count_Of_Shoes = driver
				.findElement(By.xpath("//h1[@category='Sports Shoes for Men']//following-sibling::span"));
		String text = count_Of_Shoes.getText();
		System.out.println(text);
		Thread.sleep(2000);

		// Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		Thread.sleep(2000);
		// Select the dropdown
		driver.findElement(By.xpath("//div[@class='sort-selected']")).click();
		Thread.sleep(2000);

		// Select sort Low to High
		driver.findElement(By.xpath("//ul[@class='sort-value']/li[2]")).click();
		Thread.sleep(2000);

		// Check if the items displayed are sorted correctly
		List<WebElement> training_shoe = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List list = new ArrayList<Integer>();
		
		// Iterate the list and add values
		for (WebElement each_shoe : training_shoe) {
			String attribute = each_shoe.getAttribute("data-price");
			int i = Integer.parseInt(attribute);
			list.add(i);
		}
		// Verify list is sorted or not
		boolean listSorted = isListSorted(true, list);
		System.out.println("isListsorted = " + listSorted);

		// Select the price range (900-1200)
		driver.findElement(By.name("fromVal")).clear();
		driver.findElement(By.name("fromVal")).sendKeys("900");
		driver.findElement(By.name("toVal")).clear();
		driver.findElement(By.name("toVal")).sendKeys("1200");
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();

		// Filter with color Blue
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[@for='Color_s-Blue']")).click();
		boolean checkEnable = driver.findElement(By.xpath("//label[@for='Color_s-Blue']")).isEnabled();
		System.out.println("The blue color enable is " + checkEnable);

		// Click on first resulting Training shoes
		driver.findElement(By.xpath("//p[@title='Force 10 By Liberty Yellow Sports Shoes']")).click();

		// Find the price and discount %
		String parentHandle = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String currentHandle : windowHandles) {
			if (!currentHandle.equals(parentHandle)) {
				driver.switchTo().window(currentHandle);
				break;
			}
		}

		Thread.sleep(2000);
		String price = driver.findElement(By.xpath("//span[@itemprop='price']")).getText();
		String discount = driver.findElement(By.xpath("//span[@class='pdpDiscount ']/span")).getText();
		System.out.println("Price is" + price + "and the dis % is" + discount);

		// ScreenShot of the current page
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File("src/test/resources/ScreenShots/newImage.png");
		FileUtils.copyFile(SrcFile, DestFile);
		driver.quit();

	}

	public static boolean isListSorted(boolean checkForAscending, List<Integer> orignalList) {
		System.out.println("orgininal  list = " + orignalList);
		List<Integer> copiedList = new ArrayList<Integer>(orignalList);
		if (checkForAscending) {
			Collections.sort(copiedList); // sort my list in ascending order

		} else {
			Collections.sort(copiedList, Collections.reverseOrder()); // sort my list in descending order

		}
		System.out.println("copied  list = " + copiedList);

		for (int i = 0; i < orignalList.size(); i++) {
			if (orignalList.get(i) != copiedList.get(i)) {
				return false;
			}
		}
		return true;

	}

}
