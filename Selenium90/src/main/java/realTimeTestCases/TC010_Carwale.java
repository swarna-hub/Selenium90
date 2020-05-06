package realTimeTestCases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class TC010_Carwale {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver81.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true"); 
		
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        
         options.merge(cap);
        
        ChromeDriver driver = new ChromeDriver(options);
		
		    driver.get("https://www.carwale.com/");
			
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			WebDriverWait wait = new WebDriverWait(driver, 30 /*timeout in seconds*/);
			
			Actions action = new Actions(driver);
			
			JavascriptExecutor js = (JavascriptExecutor) driver; 
			
			//Click on Used
			
			driver.findElementByXPath("//li[@data-tabs='usedCars']").click();

			
			//Select the City as Chennai
			
			Thread.sleep(2000);

			wait.until(ExpectedConditions.visibilityOf(driver.findElementById("usedCarsList")));

			driver.findElementById("usedCarsList").sendKeys("Chennai");
			
			Thread.sleep(2000);

			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[@cityname='chennai,tamilnadu']")));

			driver.findElementByXPath("//a[@cityname='chennai,tamilnadu']").click();


			
			//Select budget min (8L) and max(12L) and Click Search
			
			Thread.sleep(2000);

		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("minInput")));

			driver.findElementById("minInput").sendKeys("8",Keys.TAB);
			Thread.sleep(2000);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("maxInput")));

			driver.findElementById("maxInput").sendKeys("12",Keys.TAB);

			driver.findElementById("btnFindCar").click();

			
			// Select Cars with Photos under Only Show Cars With
			
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@name='CarsWithPhotos']")));

			
           action.moveToElement(driver.findElementByXPath("//li[@name='CarsWithPhotos']")).click().build().perform();

			Thread.sleep(2000);		
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='selectedFilters']//span[contains(text(),'Cars with Photos')]")));

			action.moveToElement(driver.findElementByXPath("//div[@id='selectedFilters']//span[contains(text(),'Cars with Photos')]")).build();


			// Select Manufacturer as "Hyundai" --> Creta
			Thread.sleep(2000);

			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//ul[@id='makesList']//li[@data-manufacture-en='Hyundai']")));


			WebElement element = driver.findElementByXPath("//ul[@id='makesList']//li[@data-manufacture-en='Hyundai']");


			
            js.executeScript("arguments[0].scrollIntoView();", element);
			js.executeScript("arguments[0].click();", element);

			Thread.sleep(1000);

			
          wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//ul[@class='rootUl']//span[text()='Creta']")));

			WebElement ele = driver.findElementByXPath("//ul[@class='rootUl']//span[text()='Creta']");

		
			js.executeScript("arguments[0].click();", ele);
			
			
			// Select Fuel Type as Petrol
			
			Thread.sleep(2000);
			
			WebElement eleFuel = driver.findElementByXPath("//div[@name='fuel']");
			
			js.executeScript("arguments[0].click()", eleFuel);
			
			Thread.sleep(1000);
			
			WebElement petrol = driver.findElementByXPath("//span[text()='Petrol']");
			
			js.executeScript("arguments[0].click()", petrol);
			
			//Select Best Match as "KM: Low to High"
			
         WebElement BestMatch = driver.findElementById("sort");

			Select se_BestMatch = new Select(BestMatch);

			se_BestMatch.selectByVisibleText("Price: Low to High");

			//Validate the Cars are listed with KMs Low to High
			

			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//span[@class='slkms vehicle-data__item'])[1]")));

			List<WebElement> list_KMS = driver.findElementsByXPath("//span[@class='slkms vehicle-data__item']");

			List<Integer> intKMS = getNumberFromList_Of_WebElements(list_KMS);

			Collections.sort(intKMS);

			System.out.println("Sorted - "+intKMS);

			List<Integer> intKMS_WithoutSort = getNumberFromList_Of_WebElements(list_KMS);

			System.out.println("Without Sorted - "+intKMS_WithoutSort);

			if(intKMS_WithoutSort.equals(intKMS)==true)
			{
				System.out.println("Cars KMS are sorted in a order");
			}
			else
			{
				System.out.println("Cars KMS are not sorted in a order");
			}


			//Add the least KM ran car to Wishlist
			
			for(int i=0;i<intKMS_WithoutSort.size();i++)
			{
				if(intKMS_WithoutSort.get(i).equals(intKMS.get(0)))
				{
					element = driver.findElementByXPath("(//span[@class='shortlist-icon--inactive shortlist'])["+(i+1)+"]");
					action.moveToElement(element).perform();
					js.executeScript("arguments[0].click();", element);
					
					Thread.sleep(2000);
					
					break;
				}
			}

			// Go to Wishlist and Click on More Details
			

			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//li[@data-action='ShortList&CompareWindow_Click']")));
			driver.findElementByXPath("//li[@data-action='ShortList&CompareWindow_Click']").click();

			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[contains(text(),'More details')]")));

			Thread.sleep(2000);

			driver.findElementByXPath("//a[contains(text(),'More details')]").click();

			Set<String> windowHandles = driver.getWindowHandles();

			List<String> lst_windowHandles = new ArrayList(windowHandles);

			if(lst_windowHandles.size()==2)
			{

				driver.switchTo().window(lst_windowHandles.get(1));
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}

			//12) Print all the details under Overview . 13) Close the browser.
			//---------------------------------------------------------------

			List<WebElement> lstDetailsLeft = driver.findElementsByXPath("//div[@id='overview']//div[@class='equal-width text-light-grey']");

			List<WebElement> lstDetailsRight = driver.findElementsByXPath("//div[@id='overview']//div[@class='equal-width dark-text']");

			Map<String,String> map = new LinkedHashMap();

			for(int i=0;i<lstDetailsLeft.size();i++)
			{
				String strLeft = lstDetailsLeft.get(i).getText();
				String strRight = lstDetailsRight.get(i).getText();
				map.put(strLeft, strRight);
			}


			for(Entry<String,String> eachEntry: map.entrySet())
			{
				System.out.print(eachEntry.getKey()+"-----------"+eachEntry.getValue());
				System.out.println();
			}

			driver.quit();


		}

		public static List<Integer> getNumberFromList_Of_WebElements(List<WebElement> lst)
		{

			List<Integer> lstInt = new ArrayList();
			for(int i=0;i<lst.size();i++)
			{
				String text = lst.get(i).getText();
				text = text.replaceAll("\\D", "");
				int intTxt = Integer.parseInt(text);


				lstInt.add(intTxt);
			}

			return lstInt;


		}

}

			

			


	


