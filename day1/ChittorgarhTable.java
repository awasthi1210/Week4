package day1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChittorgarhTable {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.chittorgarh.com/");
		// Click on stock market
		driver.findElement(By.id("navbtn_stockmarket")).click();
		// Click on NSE bulk Deals
		driver.findElement(By.xpath("//a[text()='NSE Bulk Deals']")).click();
		// Get all the Security names
		driver.findElement(
				By.xpath("//h2[contains(text(),'NSE Bulk Deals From Last Trading Session')]//parent::div//table"));

		// print 3rd columns all row
		Thread.sleep(2000);
		List<WebElement> rows = driver.findElements(By.xpath(
				"//h2[contains(text(),'NSE Bulk Deals From Last Trading Session')]//parent::div//table/tbody/tr"));
		printAllRowForAColumn(rows, 2);
		// Ensure whether there are duplicate Security names
		List<String> colDataInList = appendColumnDataToAList(rows, 2);
		System.out.println("the list contains duplicate :  " + isListContainsDuplicate(colDataInList));

	}

// Create a generic method to print the particular column data

	public static void printAllRowForAColumn(List<WebElement> rows, int colIndex) {
		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.xpath(".//td"));
			String text = cols.get(colIndex).getText();
			System.out.println(text);
		}
	}

	public static List<String> appendColumnDataToAList(List<WebElement> rows, int colIndex) {
		List<String> columnData = new ArrayList<String>();
		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.xpath(".//td"));
			String text = cols.get(colIndex).getText();
			columnData.add(text);
		}
		return columnData;

	}

	public static boolean isListContainsDuplicate(List<String> listToCheck) {
		Set<String> set = new TreeSet<String>(listToCheck);
		System.out.println(set);
		if (set.equals(listToCheck)) {
			return false;
		} else {
			return true;
		}

	}
}
