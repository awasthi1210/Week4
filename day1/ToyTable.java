package day1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ToyTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 WebDriverManager.chromedriver().setup();
 WebDriver driver = new ChromeDriver();
 driver.get("http://www.leafground.com/pages/table.html");
 WebElement Table = driver.findElement(By.tagName("table"));
   List<WebElement> each_Row = Table.findElements(By.tagName("tr"));
   List<WebElement> each_Col = Table.findElements(By.tagName("td"));
   
 System.out.println(" Row size is :"+each_Row.size()); 
 System.out.println(" Column size is :"+each_Col.size());
 
 // find the progress value
 String Progress_Value= driver.findElement(By.xpath("//tr[3]/td[2]")).getText();
 System.out.println(Progress_Value);
 
 //Check the vital checkbox for the least progress value
 driver.findElement(By.xpath("(//input[@type='checkbox'])[5]")).click();

 
	}

}
