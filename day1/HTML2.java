package day1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HTML2 {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://html.com/tags/table/");
		List<WebElement> rows = driver.findElements(By.xpath("(//table)[1]/tbody/tr"));
		for(WebElement row: rows) {
//			List<WebElement> cols= row.findElements(By.tagName("td"));
//			for(WebElement col: cols) {
			System.out.println(row.getText());
				
			}
		}
	}


