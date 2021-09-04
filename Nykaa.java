package week4.day2assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(2000);
		
		//Mouseover on Brands and Mouseover on Popular
		WebElement brand = driver.findElementByXPath("//a[text()='brands']");
		WebElement popular = driver.findElementByXPath("//a[text()='Popular']");
		Actions builder = new Actions(driver);
		builder.moveToElement(brand).perform();
		builder.moveToElement(popular).perform();
		Thread.sleep(2000);
		
		//Click L'Oreal Paris
		driver.findElementByXPath("//a[@href='/brands/loreal-paris/c/595?eq=desktop']").click();
		
		//Go to the newly opened window and check the title contains L'Oreal Paris		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(1));
		
		//Click sort By and select customer top rated
		driver.findElementByXPath("//i[@class='fa fa-angle-down']").click();
		driver.findElementByXPath("(//div[@class='control control--radio text-capitalize'])[4]/span").click();
		
		//Click Category and click Shampoo
		driver.findElementByXPath("//div[text()='Category']").click();
		driver.findElementByXPath("//span[text()='Hair']").click();
		driver.findElementByXPath("//span[text()='Hair Care']").click();
		driver.findElementByXPath("//span[text()='Shampoo']").click();
		
		//check whether the Filter is applied with Shampoo
		WebElement filter = driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']/li");
		String fil = filter.getText();
		if(fil.contains("Shampoo")) {
			System.out.println("Filter applied");
		}else {
			System.out.println("Filter not applied");
		}
		
		
		//Click on L'Oreal Paris Colour Protect Shampoo
		Thread.sleep(2000);
		driver.findElementByXPath("//div[text()='Concern']").click();
		driver.findElementByXPath("//span[text()='Color Protection']").click();
		driver.findElementByXPath("(//div[@class='m-content__product-list__title']//span)[1]").click();
		
		//GO to the new window and select size as 175ml
		Set<String> windowHandles2 = driver.getWindowHandles();
		List<String> windowHandlesList2 = new ArrayList<String>(windowHandles2);
		driver.switchTo().window(windowHandlesList2.get(2));
		
		//Print the MRP of the product
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,350)");
		WebElement addtobag = driver.findElementByXPath(
				"(//button[@class='primary-btn nk-btn combo-add-to-btn  atc-simple m-content__product-list__cart-btn  '])[1]");
		WebElement movetoobj = driver.findElementByXPath("(//div[@class='product-list-box card desktop-cart'])[2]");
		
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(movetoobj).perform();
		addtobag.click();
		Thread.sleep(2000);
		//Click on ADD to BAG
		driver.findElementByXPath("(//button[@class='combo-add-to-btn prdt-des-btn js--toggle-sbag nk-btn nk-btn-rnd atc-simple m-content__product-list__cart-btn  '])[1]").click();
		Thread.sleep(2000);
		//Go to Shopping Bag
		driver.findElementByXPath("//div[@class='AddBagIcon']").click();
		
		//print the grand total amount
		String grandtotal = driver.findElementByXPath("//span[text()='Grand Total']/following-sibling::div").getText();
		String gt = grandtotal.replaceAll("[^0-9]", "");
		int grandTotal1 = Integer.parseInt(gt);

		System.out.println("Grandtotal" + grandTotal1);
		Thread.sleep(2000);
		
		//Click Proceed
		WebElement proceed = driver.findElementByXPath("//button[@class='btn full fill no-radius proceed ']");
		Actions act = new Actions(driver);
		act.doubleClick(proceed).perform();
		
		//Click on Continue as Guest
		driver.findElementByXPath("//div[text()='Checkout as guest']/following-sibling::button").click();
		Thread.sleep(2000);
		
		
		//Check if this grand total is the same in step 13
		String grandtotal2 = driver.findElementByXPath("//div[text()='Grand Total']/following-sibling::div/span").getText();
		String gt2 = grandtotal2.replaceAll("[^0-9]", "");
		int grandTotal2 = Integer.parseInt(gt2);

		System.out.println("Grandtotal" + grandTotal2);

		if (grandTotal1 == grandTotal2) {
			System.out.println("Grand Total are same");
		} else

		{
			System.out.println("Grand Total are different");
		
		}
		
		//Close all windows
		driver.quit();
		

	}

}
