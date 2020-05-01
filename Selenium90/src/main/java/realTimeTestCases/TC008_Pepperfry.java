package realTimeTestCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC008_Pepperfry {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver81.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true"); 
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        
         options.merge(cap);
        
        ChromeDriver driver = new ChromeDriver(options);
		
	    driver.get("https://www.pepperfry.com/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver, 30 /*timeout in seconds*/);
		
		Actions action = new Actions(driver);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		
		//MouseOver on Furniture
		
		action.moveToElement(driver.findElementByXPath("//div[@id='menu_wrapper']//a[starts-with(text(),'Furniture')]")).perform();
		
		//To click office chairs in Chairs category
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Office Chairs')]")));
		
		action.moveToElement(driver.findElementByXPath("//a[contains(text(),'Office Chairs')]")).click().perform();
		
		
		//Click Executive chairs
		
		driver.findElementByXPath("//h5[contains(text(),'Executive Chairs')]").click();
		
		//Change the height 
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("clipFilterDimensionHeightValue")));
		
		driver.findElementByClassName("clipFilterDimensionHeightValue").clear();
		
		driver.findElementByClassName("clipFilterDimensionHeightValue").sendKeys("50",Keys.ENTER);
		
		
		//Adding Poise Executive chair to wishlist
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Poise Executive Chair in Black Colour']")));
		
		Thread.sleep(2000);
		
		driver.findElementByXPath("//a[@title='Poise Executive Chair in Black Colour']//ancestor::div[@class='clip-crd-10x11 pf-white padding-2x1']//descendant::a[@id='clip_wishlist_']").click();
		
		//Mouseover on Homeware 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='menu_wrapper']//a[starts-with(text(),'Homeware')]")));
		
		action.moveToElement(driver.findElementByXPath("//div[@id='menu_wrapper']//a[starts-with(text(),'Homeware')]")).perform();
		
		
		
		//Click pressure cooker
	
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[starts-with(text(),'Pressure Cookers')]")));
		
		action.moveToElement(driver.findElementByXPath("//a[starts-with(text(),'Pressure Cookers')]")).click().perform();
		
		
		//Select Prestige as Brand
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Prestige')]")));
		
		driver.findElementByXPath("//label[contains(text(),'Prestige')]").click();
		
		//Select Capacity as 1-3 Ltr

		Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'1 Ltr - 3 Ltr')]")));
		
		driver.findElementByXPath("//label[contains(text(),'1 Ltr - 3 Ltr')]").click();
		
		//Adding  "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']")));
		
		Thread.sleep(2000);
		
		driver.findElementByXPath("//a[@title='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']/ancestor::div[@class='clip-dtl-ttl row']//following-sibling::div[@class='row clip-dtl-brand']/descendant::a[@id='clip_wishlist_']").click();
		
		//Verify the wishlist count
		
		 js.executeScript("window.scrollBy(0,-300)");	
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='wishlist_bar']")));
		 
		String wishlistcount= driver.findElementByXPath("//div[@class='wishlist_bar']/span").getText();
		
		System.out.println("The count of wishlist ="+wishlistcount);
		
		if(wishlistcount.equalsIgnoreCase("2")) {
			
			System.out.println("The No of items added is  :"+wishlistcount);
		}
		 
		else {
			
			System.out.println("The No of items is :"+wishlistcount);
			
		}
		
		
		//Navigate to wishlist
		
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='wishlist_bar']/a")));
		 
		 driver.findElementByXPath("//div[@class='wishlist_bar']/a").click();
		 
		 
		 //Click to Add to Cart the pressure cooker
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr By')]"))); 
		 
		 driver.findElementByXPath("//a[contains(text(),'Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr By')]//ancestor::div[@class='item_details']//descendant::a[@class='addtocart_icon']").click();
		 
		 System.out.println("The Nakshatra Pressure cooker is added to the cart");
		 
		 //Check the availability of pincode
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='srvc_pin_text']")));
		 
		 driver.findElementByXPath("//input[@class='srvc_pin_text']").sendKeys("600128");
		 
		 
		 //Click the check link
		 
		 driver.findElementByXPath("//a[@class='check_available']").click();
		 		
 
		 //Click on Proceed to pay link 
		 
		 Thread.sleep(2000);
		 
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Proceed to pay securely')]")));
		 
		 driver.findElementByXPath("//a[contains(text(),'Proceed to pay securely')]").click();
		 
		 
		 Thread.sleep(2000);
		 
		 //To click pay
		 
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='PLACE ORDER'])[1]")));
		 
		 driver.findElementByXPath("(//a[text()='PLACE ORDER'])[1]").click();
		 
		 
		 //To click Order summary
		 
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='nCheckout__accrodian-header-left']/span")));
		 
		 driver.findElementByXPath("//div[@class='nCheckout__accrodian-header-left']/span").click();
	
		 
		 Thread.sleep(2000);
		 
		 //To take a screenshot
		 
		    File src = driver.findElementByXPath("//div[@class='slick-list draggable']//li").getScreenshotAs(OutputType.FILE);
			File dst = new File("./Screenshotsa/snap1.png");
			FileUtils.copyFile(src, dst);
		 
		
		 
		 driver.quit();
	}

}
