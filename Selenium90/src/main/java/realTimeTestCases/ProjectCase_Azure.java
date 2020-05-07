package realTimeTestCases;

import java.io.File;
import java.util.HashMap;
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

public class ProjectCase_Azure {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver81.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true"); 
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("plugins.always_open_pdf_externally", true);
        
		chromePrefs.put("download.default_directory", "D:\\downloads");

		ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
                
        ChromeDriver driver = new ChromeDriver(options);
		
	    driver.get("https://azure.microsoft.com/en-in/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver, 30 /*timeout in seconds*/);
		
		Actions action = new Actions(driver);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		
      //2) Click on Pricing

		driver.findElementById("navigation-pricing").click();
		
		//3) Click on Pricing Calculator
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Pricing calculator')]")));
		
		driver.findElementByXPath("//a[contains(text(),'Pricing calculator')]").click();
		
		//4) Click on Containers
		
		Thread.sleep(3000);
		
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[contains(text(),'Containers')])[2]")));
		
		driver.findElementByXPath("(//button[contains(text(),'Containers')])[2]").click();
		
		//5) Select Container Instances

		Thread.sleep(3000);
		
		//7) Select Region as "South India"
		driver.findElementByXPath("(//span[contains(text(),'Container Instances')])[3]").click();
		
		Thread.sleep(4000);
		
		WebElement region=driver.findElementByXPath("(//select[@name='region'])[1]");
		
		Select dropdown=new Select(region);
		
		dropdown.selectByVisibleText("South India");
		
		//8) Set the Duration as 180000 seconds
		
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@name='seconds'])[1]")));
		
		WebElement time=driver.findElementByXPath("(//input[@name='seconds'])[1]");
		
		time.sendKeys(Keys.chord(Keys.CONTROL,"a"),"180000");
		
		//action.moveToElement(driver.findElementByXPath("(//input[@name='seconds'])[1]")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT, Keys.END),"180000").build().perform();

		//9) Select the Memory as 4GB
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//select[@name='memory'])[1]")));
		
      WebElement memory=driver.findElementByXPath("(//select[@name='memory'])[1]");
		
		Select dropdownM=new Select(memory);
		
		dropdownM.selectByVisibleText("4 GB");
		
		
		
		//10) Enable SHOW DEV/TEST PRICING
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("devtest-toggler")));
		
		driver.findElementById("devtest-toggler").click();
		
		
		
		//11) Select Indian Rupee  as currency
		
        WebElement currency=driver.findElementByXPath("//select[@class='select currency-dropdown']");
		
		Select dropdownC=new Select(currency);
		
		dropdownC.selectByValue("INR");
		
		//12) Print the Estimated monthly price

		Thread.sleep(2000);
		
		String MonthlyCost=driver.findElementByXPath("(//div[@class='column large-3 text-right total']/div/span/span)[2]").getText();
		
		
		System.out.println("Print the Monthly Cost:"+MonthlyCost);
		
		
		//13) Click on Export to download the estimate as excel

		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='calculator-button button-transparent export-button']/span")));
		 
		 driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']/span").click();

		 
		//14) Verify the downloded file in the local folder
		 
		 Thread.sleep(2000);
 
		 String downloadpath = "D:\\downloads";
			String filename = "ExportedEstimate.xlsx";
			File dir = new File(downloadpath);
			File[] dir_contents = dir.listFiles();
			boolean flag = false;
			for (int i = 0; i < dir_contents.length; i++) {
				if (dir_contents[i].getName().equals(filename))
					flag = true;
			}

			if (flag == true) {
				System.out.println("Container Instances's File is available in the correct folder");
			} else {
				System.out.println("Container Instances's File is available in the correct folder");
			}
			Thread.sleep(2000);
		
		
		//15) Navigate to Example Scenarios and Select CI/CD for Containers
		
           js.executeScript("window.scrollBy(0,600)");	
           
           Thread.sleep(3000);
           
		 
		 
		WebElement ES=driver.findElementByXPath("//a[contains(text(),'Example Scenarios')]"); 
		
		js.executeScript("arguments[0].scrollIntoView(true);",ES);
		
		js.executeScript("arguments[0].click();", ES);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'CI/CD for Azure Web Apps')]")));
		 
		driver.findElementByXPath("//span[contains(text(),'CI/CD for Azure Web Apps')]").click(); 
		
		//16) Click Add to Estimate
		
		Thread.sleep(2000);
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add to estimate')]")));
		 
		driver.findElementByXPath("//button[contains(text(),'Add to estimate')]").click(); 
		
		
		//  17) Select Indian Rupee  as currency
		
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@class='select currency-dropdown']")));
		
        WebElement currency1=driver.findElementByXPath("//select[@class='select currency-dropdown']");
		
		Select dropdownC1=new Select(currency1);
		
		dropdownC1.selectByValue("INR");
		
		
		//18) Enable SHOW DEV/TEST PRICING
		
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("devtest-toggler")));
		
		driver.findElementById("devtest-toggler").click();
		
		//19) Export the Estimate
		
		Thread.sleep(2000);
		 
 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='calculator-button button-transparent export-button']/span")));
		 
		 driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']/span").click();
		 
		 Thread.sleep(2000);

		downloadpath = "D:\\downloads";
		String filename1 = "ExportedEstimate (1).xlsx";
		File dir1 = new File(downloadpath);
		File[] dir_contents1 = dir1.listFiles();
		boolean flag1 = false;
		for (int i = 0; i < dir_contents1.length; i++) {
			if (dir_contents1[i].getName().equals(filename1))
				flag1 = true;
		}

		if (flag1 == true) {
			System.out.println("Example Scenario's File is available in the correct folder");
		} else {
			System.out.println("Example Scenario's File is not available in the correct folder");
		}
		
		//
	   driver.quit();
	}

}
