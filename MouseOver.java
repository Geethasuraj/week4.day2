package week4.day2assignment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MouseOver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/mouseOver.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		Actions builder = new Actions(driver);
		WebElement leaf = driver.findElement(By.xpath("//a[@class='btnMouse']"));
		builder.moveToElement(leaf).perform();

		List<WebElement> link = driver.findElements(By.tagName("a"));
		for (WebElement links : link) {
			System.out.println(links.getText() + " is" + links.getAttribute(" href"));
		}

		driver.findElement(By.xpath("(//a[@href='#'])[3]")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

}
