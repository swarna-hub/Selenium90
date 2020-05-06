package realTimeTestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectCase_Ajio {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver81.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true"); 
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        
         options.merge(cap);
        
        ChromeDriver driver = new ChromeDriver(options);
		
	    driver.get("https://www.ajio.com/shop/sale");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver, 30 /*timeout in seconds*/);
		
		Actions action = new Actions(driver);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		
		//2) Enter Bags in the Search field and Select Bags in Women Handbags
		
		driver.findElementByName("searchVal").sendKeys("Bags");
		
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Women Handbags']")));
		
		action.moveToElement(driver.findElementByXPath("//span[text()='Women Handbags']")).click().perform();
		
		Thread.sleep(2000);
		
		
		//3) Click on five grid and Select SORT BY as "What's New"
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='five-grid']")));
		
		driver.findElementByXPath("//div[@class='five-grid']").click();
		
		WebElement sort =driver.findElementByXPath("//select");
		
		Select sortBy= new Select(sort);
		
		sortBy.selectByValue("newn");
		
		Thread.sleep(2000);
		
		// 4)) Enter Price Range Min as 2000 and Max as 5000

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='price']")));
		
		driver.findElementByXPath("//span[text()='price']").click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Enter Price Range']")));
		
		
		driver.findElementById("minPrice").sendKeys("2000");
		

		driver.findElementById("maxPrice").sendKeys("5000");
		
		driver.findElementByXPath("//button[@class='rilrtl-button ic-toparw']").click();
		
		Thread.sleep(2000);
		
		//5) Click on the product "Puma Ferrari LS Shoulder Bag"
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Ferrari LS Shoulder Bag']")));
		
		driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']").click();
		
		
		Set<String> windowHandles = driver.getWindowHandles();
		
		List<String>allWindows=new ArrayList();
		
		allWindows.addAll(windowHandles);
		
		System.out.println("The no of windows opened"+allWindows.size());
		
		//To move to child window
		
		driver.switchTo().window(allWindows.get(1));
		
		//6) Verify the Coupon code for the price above 2690 is applicable for your product, if applicable the get the Coupon Code and Calculate the discount price for the coupon

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='prod-sp']")));
		
		String prodVal=driver.findElementByXPath("//div[@class='prod-sp']").getText();
		
		System.out.println("Product value:"+prodVal);
		
		String newVal = prodVal.replaceAll("\\D", "");
		
		int ProductPrice = Integer.parseInt(newVal);
		
		System.out.println("Product Price:"+ProductPrice);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='promo-desc']")));
		
		String offerMsg=driver.findElementByXPath("//div[@class='promo-desc']").getText();
		
		System.out.println("offerMsg:"+offerMsg);
		
		String offervalstring=offerMsg.substring(19, 27);
		
		System.out.println(offervalstring);
		
		String perValue=offerMsg.substring(11, 13);
		
		System.out.println("Percentage offer value:"+perValue);
		
		int percentage=Integer.parseInt(perValue.substring(0, 1));
		
		
	
		
		String offerstring = offervalstring.replaceAll("\\D", "");
		
		int offerValue= Integer.parseInt(offerstring);
		
		System.out.println("Offer Value:"+offerValue);
		
		String Couponcode =  driver.findElementByXPath("//div[@class='promo-title']").getText();
		
		System.out.println("Couponcode:"+Couponcode);
		
		if(ProductPrice>=offerValue) {
			
			System.out.println("Product Value is greater than Offer Value:"+Couponcode);
			
			//String Couponcode1 =driver.findElementByXPath("//div[text()='Use Code']/br").getText();
		}
		
		else {
			
			System.out.println("Product value is not greater than offer Value");
		}
			
			String discountPrice=driver.findElementByXPath("//div[text()='Get it for ']/span").getText();
			
			String dP=discountPrice.replaceAll("\\D", "");
			
			int discountValue= Integer.parseInt(dP);
			
			System.out.println("Discount PRICE:"+discountValue);
			
			int discountAmount = ProductPrice-discountValue;
			System.out.println("Discount Amount=:"+discountAmount);

		
   
		
		//7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Enter pin-code to know estimated delivery date.']")));
		
		action.moveToElement(driver.findElementByXPath("//span[text()='Enter pin-code to know estimated delivery date.']")).click().perform();;
		
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("560043");
		
		driver.findElementByXPath("//button[@class='edd-pincode-modal-submit-btn']").click();
		
		Thread.sleep(2000);
		
		/*
		 * String delInfo=driver.findElementByXPath(
		 * "//ul[@class='edd-message-success-details']/li/span").getText();
		 * 
		 * System.out.println("Delivery information:"+delInfo);
		 */
		
		//Click on Other Informations under Product Details and Print the Customer Care address, phone and email
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='other-info-toggle']")));
		
		driver.findElementByXPath("//div[@class='other-info-toggle']").click();
		
		Thread.sleep(2000);
		
		String address=driver.findElementByXPath("//span[text()='Customer Care Address']/parent::li[@class='detail-list mandatory-info']/descendant::span[@class='other-info']").getText();

		System.out.println("Address:"+address);
		
		//9) Click on ADD TO BAG and then GO TO BAG
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ic-pdp-add-cart']")));
		
		driver.findElementByXPath("//span[@class='ic-pdp-add-cart']").click();
		

		Thread.sleep(10000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='GO TO BAG']")));
		
		driver.findElementByXPath("//span[text()='GO TO BAG']").click();
		
		
		Thread.sleep(2000);
		
		//10) Check the Order Total before apply coupon

     String BagValue= driver.findElementByXPath("//section[@id='orderTotal']/span[2]").getText();
     
     System.out.println("Bag value with out applying offer:"+BagValue);
     
   //  11) Enter Coupon Code and Click Apply
     
 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("couponCodeInput")));
     
     driver.findElementById("couponCodeInput").sendKeys("EPIC");
     
     Thread.sleep(1000);
     driver.findElementByXPath("//button[text()='Apply']").click();
     
     Thread.sleep(3000);
     
    //12) Verify the Coupon Savings amount(round off if it in decimal) under Order Summary and the matches the amount calculated in Product details
     
 	String CouponPrice = driver.findElementByXPath("(//span[@class='price-value discount-price'])[2]").getText();
 	
 	System.out.println("Coupon Price:"+CouponPrice);
 	String replaceCP = CouponPrice.replaceAll("[:,a-zA-Z ]", "");
 	
 	String subCP=replaceCP.substring(1);
 	float floatCP = Float.parseFloat(subCP);
 	
 	

 	int CouponSavingsAmount=(int)Math.round(floatCP);
 	
 	
 	System.out.println("Coupon Savings amount:"+CouponSavingsAmount);
 	if(CouponSavingsAmount==discountAmount)
 	{
 		System.out.println("coupon Savings amount matches with caluculated discount amount");
 	}
 	
   // 	13) Click on Delete and Delete the item from Bag

    driver.findElementByXPath("//div[text()='Delete']").click();
    
    driver.quit();
	}

	
}
