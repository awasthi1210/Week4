package day1;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HTML1 {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://html.com/tags/table/");
		List <WebElement> row = driver.findElements(By.tagName("tr"));
		List <WebElement> col = driver.findElements(By.tagName("td"));
		
		 
		System.out.println("num of rows are "+row.size() + " Num of columns are "+col.size());
		

	}

}
