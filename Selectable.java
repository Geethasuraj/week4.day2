package week4.day2assignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selectable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/selectable.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		Actions builder = new Actions(driver);
		
		WebElement item1 = driver.findElementByXPath("//li[text()='Item 1']");
		WebElement item6 = driver.findElementByXPath("//li[text()='Item 6']");
		
		builder.clickAndHold(item1).moveToElement(item6).release().perform();
		

	}

}
