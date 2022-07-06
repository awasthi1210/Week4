package day1;

import java.util.List;

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
		driver.findElement(By.id("navbtn_stockmarket")).click();
		driver.findElement(By.xpath("//a[text()='NSE Bulk Deals']")).click();
		driver.findElement(
				By.xpath("//h2[contains(text(),'NSE Bulk Deals From Last Trading Session')]//parent::div//table"));

		// print 3rd columns all row
		Thread.sleep(2000);
		List<WebElement> rows = driver.findElements(By.xpath(
				"//h2[contains(text(),'NSE Bulk Deals From Last Trading Session')]//parent::div//table/tbody/tr"));
		printAllRowForAColumn(rows,2);
		

		

	}

	
	public static void printAllRowForAColumn(List<WebElement>rows,int colIndex) {
			for(WebElement row: rows) {
				List<WebElement> cols = row.findElements(By.xpath(".//td"));
				System.out.println(cols.get(colIndex).getText());
			}
	}
}
