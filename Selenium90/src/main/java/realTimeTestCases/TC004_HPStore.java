package realTimeTestCases;

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
import org.testng.Assert;

public class TC004_HPStore {

	public static void main(String[] args) throws InterruptedException {
		
		// TODO Auto-generated method stub
		
	//Launch the Chrome Browser -> call ChromeDriver
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver81.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true"); 
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        
         options.merge(cap);
        
        ChromeDriver driver = new ChromeDriver(options);
	     WebDriverWait wait = new WebDriverWait(driver, 30 /*timeout in seconds*/);
		
		  Actions action = new Actions(driver);
		
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		
		//1) Go to https://store.hp.com/in-en/
		driver.get("https://store.hp.com/in-en/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//2) Mouse over on Laptops menu and click on Pavilion
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[text()='Laptops'])[1]")));
		WebElement Laptop = driver.findElementByXPath("(//span[text()='Laptops'])[1]");
		
		action.moveToElement(Laptop).perform();
		
		WebElement elePavilion = driver.findElementByXPath("//span[text()='Pavilion']");
		
		wait.until(ExpectedConditions.elementToBeClickable(elePavilion));
		action.moveToElement(elePavilion).click();
		
     //3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		
		WebElement CloseCookies = driver.findElementByXPath("//button[@class='optanon-alert-box-close banner-close-button']");
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(CloseCookies));
		
		CloseCookies.click();
		
		Thread.sleep(2000);
		
		
		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		WebElement Processor = driver.findElementByXPath("(//input[@class='product-filter-checkbox'])[2]");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@class='product-filter-checkbox'])[2]")));
		Processor.click();
		
     //4) Hard Drive Capacity -->More than 1TB
		Thread.sleep(6000);
		WebElement oneTB = driver.findElementByXPath("//span[contains(text(),'More than 1 TB')]");
		oneTB.click();
		System.out.println("1TB clicked");
		
    //5) Select Sort By: Price: Low to High
		Thread.sleep(3000);
		WebElement sort = driver.findElementByXPath("(//select[@id='sorter'])[1]");
		Select ddSort = new Select(sort);
		ddSort.selectByValue("price_asc");
		
   //6) Print the First resulting Product Name and Price
		Thread.sleep(2000);
		WebElement FirstResult = driver.findElementByXPath("(//a[@class='product-item-link'])[1]");
		FirstResult.click();
		
		
      //7) Click on Add to Cart
		WebElement Price = driver.findElementByXPath("(//span[@class='price-wrapper '])[1]");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(Price));
		String value = Price.getAttribute("data-price-amount");
		int expPrice = Integer.parseInt(value);
		WebElement eleAddCart = driver.findElementByXPath("//button[@id='product-addtocart-button']");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(eleAddCart));
		eleAddCart.click();
		Thread.sleep(3000);
		
		
       //8) Click on Shopping Cart icon --> Click on View and Edit Cart
		
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='action showcart']")));
		
		driver.findElementByXPath("//a[@class='action showcart']").click();
		
		driver.findElementByXPath("//a[@class='action primary viewcart']").click();
		
		
     //9) Check the Shipping Option --> Check availability at Pincode
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='pincode']")));
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("600117");
		driver.findElementByXPath("//button[@class='primary standard_delivery-button']").click();
		Thread.sleep(500);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='delivery-days']/dl/dd/span/following-sibling::span")));
		String availability = driver.findElementByXPath("//div[@class='delivery-days']/dl/dd/span/following-sibling::span").getText();
		System.out.println(availability);
		
      //10) Verify the order Total against the product price
		
		String actualPriceAllChars = driver.findElementByXPath("(//span[@class='price'])[7]").getText();
		String actualPrice = actualPriceAllChars.replaceAll("\\D", "");
		int actPrice = Integer.parseInt(actualPrice);
		
		
    //11) Proceed to Checkout if Order Total and Product Price matches
		
		Assert.assertEquals(actPrice, expPrice);
		
		driver.findElementByXPath("(//button[@id='sendIsCAC'])[1]").click();
		
   //12) Click on Place Order
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[text()='Place Order'])[4]")));
		
		driver.findElementByXPath("(//span[text()='Place Order'])[4]").click();
		
   //13) Capture the Error message and Print
		
		String errMsg = driver.findElementByXPath("//div[@class='inside-notice-content-container']/p/b").getText();
		
		System.out.println(errMsg+"\n test case passed\n");
		
   //14) Close Browser
		driver.quit();	
	}


		
	}


