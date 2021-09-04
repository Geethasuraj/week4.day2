package week4.day2assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(2000);
		
		//Go to Mens Fashion
		Actions builder = new Actions(driver);
		WebElement men = driver.findElementByXPath("(//span[@class='catText'])[6]");
	    builder.moveToElement(men).perform();
		
		
		//Go to Sports Shoes
	    driver.findElementByXPath("//a[@href='https://www.snapdeal.com/products/mens-footwear-sports-shoes']/span").click();
	    
		
		//Get the count of the sports shoes
	    System.out.println(driver.findElementByXPath("//h1[@category='Sports Shoes']/span").getText());
		
		//Click Training shoes
	    driver.findElementByXPath("//div[text()='Training Shoes']").click();
	    
	   // Sort by Low to High
	   driver.findElementByXPath("//i[@class='sd-icon sd-icon-expand-arrow sort-arrow']").click();
	   driver.findElementByXPath("//li[@data-sorttype='plth']").click();
	   
	   Thread.sleep(5000);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,35000)");

		
		Thread.sleep(8000);

		WebElement end = driver.findElementByXPath("//span[@class='btn-yes js-yesFeedback']");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", end);
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, -700)");

		Thread.sleep(5000);
	   
	   //Check if the items displayed are sorted correctly
	   
		List<WebElement> pricelist = driver.findElementsByXPath("//span[@class='lfloat product-price']");
		JavascriptExecutor j4 = (JavascriptExecutor) driver;
		j4.executeScript("window.scrollBy(0,-15000)");
		Thread.sleep(2000);
		List<Integer> newval = new ArrayList<Integer>();

		for (int i = 0; i < pricelist.size(); i++) {
			String pl = pricelist.get(i).getText();
			String gt = pl.replaceAll("[^0-9]", "");
			int price = Integer.parseInt(gt);
			newval.add(price);
		}
		Thread.sleep(1000);
		System.out.println(newval.size());
		List<Integer> sortedlist = new ArrayList<Integer>(newval);
		Collections.sort(sortedlist);
		boolean b = false;
		for (int i = 0; i < newval.size(); i++) {
			if (newval.get(i) == sortedlist.get(i)) {
				b = true;

			} else {
				b = false;
				break;

			}
		}
		if (b == true) {
			System.out.println("Price are sorted");
		} else {
			System.out.println("Price not sorted");
		}

		js1.executeScript("window.scrollBy(0,-2000)");
		Thread.sleep(2000);
	   
	  // mouse over blue shoe
		driver.findElementByXPath("//label[@for='Color_s-Blue']").click();
		Thread.sleep(1000);
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollBy(0,1000)");
		WebElement buleShoe = driver.findElementByXPath("//img[@title='VSS Blue Training Shoes']");
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(buleShoe).perform();
		
	   
	   //click QuickView button
		driver.findElementByXPath("//div[contains(text(),'Quick View')]").click();
	   
	   //Print the cost and the discount percentage
		String shoePrice = driver.findElementByXPath("//span[@class='payBlkBig']").getText();
		String sp = shoePrice.replaceAll("[^0-9]", "");
		int price = Integer.parseInt(sp);
		System.out.println("Cost of the shoe:" + price);
		String dis = driver.findElementByXPath("//span[@class='percent-desc ']").getText();
		String dis1 = dis.replaceAll("[^0-9]", "");
		int discount = Integer.parseInt(dis1);
		System.out.println("discount percentage" + discount + "%");
	  
	  //Take the snapshot of the shoes
	
	  File src = driver.getScreenshotAs(OutputType.FILE);
	  File des = new File("./snaps/snap.png");
	  FileUtils.copyFile(src, des);
	  
	  
	  //Close the current window
	  driver.findElementByXPath("(//i[@class='sd-icon sd-icon-delete-sign'])[3]").click();
	  
	  
	  //Close the main window
	  driver.close();
	  
	
		

	}

}
