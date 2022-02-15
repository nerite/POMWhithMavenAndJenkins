package wrapper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Reports;

public class GenericWrapper extends Reports{
	
	public RemoteWebDriver driver;
	
	// launch the browser
	public void launchBrowser(String browser, String url) {
		try {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		reportStep("The Browser " + browser + " launched Successfully", "PASS");
		}catch(WebDriverException e) {
			reportStep("The Browser " + browser + " couldn't launch", "FAIL");
			
		}
	}

	public WebElement locateSelector(String selType, String selValue) {
			try {
				switch (selType.toLowerCase()) {
				case "id": return driver.findElement(By.id(selValue));
				case "name": return driver.findElement(By.name(selValue));
				case "class": return driver.findElement(By.className(selValue));
				case "xpath": return driver.findElement(By.xpath(selValue));
				case "link": return driver.findElement(By.linkText(selValue));
				case "partiallink": return driver.findElement(By.partialLinkText(selValue));
				case "tagname": return driver.findElement(By.tagName(selValue));
				case "css": return driver.findElement(By.cssSelector(selValue));
				}
				return null;
			} catch (Exception e) {
				reportStep("The Element not found", "FAIL");
			}
			return null;
	}
	
	public List<WebElement> locateElements(String selectorType, String selValue){
		switch (selectorType.toLowerCase()) {
		case "id": return driver.findElements(By.id(selValue));
		case "name": return driver.findElements(By.name(selValue));
		case "class": return driver.findElements(By.className(selValue));
		case "linktext": return driver.findElements(By.linkText(selValue));
		case "partiallink": return driver.findElements(By.partialLinkText(selValue));
		case "tagname": return driver.findElements(By.tagName(selValue));
		case "xpath": return driver.findElements(By.xpath(selValue));
		case "css": return driver.findElements(By.cssSelector(selValue));
		}
		return null;
	}
	
	public void enterValue(WebElement ele, String data) {
		try {
			ele.sendKeys(data);
			reportStep("The data \""+data+"\" entered Successfully", "PASS");
		} catch (WebDriverException e) {
			reportStep("The data \""+data+"\" couldn't enter", "FAIL");
		}
	}
	
	public void click(WebElement ele) {
		String text = ele.getText();
		try {
			ele.click();
			reportStep("The element \""+text+"\" clicked Successfully", "PASS");
		} catch (WebDriverException e) {
			reportStep("The element \""+text+"\" couldn't click", "FAIL");
		}
	}
	
	public void clear(WebElement ele) {
		String text = ele.getAttribute("value");
		try {
			ele.clear();
			reportStep("The text \"" + text + "\" deleted Successfully", "PASS");
		} catch (Exception e) {
			reportStep("The text \"" + text + "\" couldn't be deleted", "FAIL");
		}
	}
	
	public void clearAndType(WebElement ele, String data) {
		String text = ele.getAttribute("value");
		try {
			ele.clear();
			ele.sendKeys(data);
			reportStep("The old text \""+text+"\" deleted and new text \""+data+"\" was typed Successfully", "PASS");
		} catch (Exception e) {
			reportStep("The old text \""+text+"\" deleted and new text \""+data+"\" couldn't be typed", "FAIL");
		}
	}
	
	public String getElementText(WebElement ele) {
		String text = ele.getText();
		System.out.println("The elements text \"" +text+ "\" retreaved Successfully");
		return text;
	}
	
	public String getBackgroundColor(WebElement ele) {
		String cssValue = ele.getCssValue("color");
		System.out.println("The cssValue \""+cssValue+"\" returns Successfully");
		return cssValue;
	}
	
	public String getTypedText(WebElement ele) {
		String text = ele.getAttribute("value");
		System.out.println("Typed text \"" + text + "\" retreaved Successfylly");
		return text;
	}
	
	public void selectDropdownUsingText(WebElement ele, String value) {
		try {
			new Select(ele).selectByVisibleText(value);
			reportStep("The value \""+value+"\" chosen Successfully", "PASS");
		} catch (Exception e) {
			reportStep("The value \""+value+"\" wasn't selected", "FAIL");
		}
	}
	
	public void selectDropDownUsingIndex(WebElement ele, int index) {
		try {
			new Select(ele).selectByIndex(index);
			reportStep("The index \""+index+"\" chosen Successfully", "PASS");
		} catch (Exception e) {
			reportStep("The index \""+index+"\" wasn't selected", "FAIL");
		}
	}
	
	public void selectDropDownUsingValue(WebElement ele, String value) {
		try {
			new Select(ele).selectByValue(value);
			reportStep("The value \""+value+"\" chosen Successfully", "PASS");
		} catch (Exception e) {
			reportStep("The value \""+value+"\" wasn't selected", "FAIL");
		}
	}
	
	public boolean verifyExatText(WebElement ele, String expectedText) {
		String text = ele.getText();
		boolean bReturn = false;
		if(text.equals(expectedText)) {
			reportStep("Text \""+ text +"\" matched \""+expectedText+"\"", "PASS");
			bReturn = true;
		}else {
			reportStep("Text \""+text+"\" didn't match \""+expectedText+"\"", "FAIL");
		}
		return bReturn;
	}
	
	public boolean verifyPartialText(WebElement ele, String expectedText) {
		String text = ele.getText();
		boolean bReturn = false;
		if(text.contains(expectedText)) {
			reportStep("Text \""+text+"\" contains partial text \""+expectedText+"\"", "PASS");
			bReturn = true;
		}else {
			reportStep("Text \""+text+"\" doesn't contains partial text \""+expectedText+"\"", "FAIL");
		}
		return bReturn;
	}
	
	public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
		boolean bReturn = false;
		if(ele.getAttribute(attribute).equals(value)) {
			reportStep("The attribute \""+attribute+"\" contains text \""+value, "PASS");
			bReturn = true;
		}else {
			reportStep("The attribute \""+attribute+"\" doesn't contain text \""+value, "FAIL");
		}
		return bReturn; 
	}
	
	public boolean verifyPartialAttribute(WebElement ele, String attribute, String value) {
		boolean bReturn = false;
		if(ele.getAttribute(attribute).contains(value)) {
			System.out.println("The attribute \""+attribute+"\" contains partial text \""+value);
			bReturn = true;
		}else {
			System.out.println("The attribute \""+attribute+"\" doesn't contain partial text \""+value);
		}
		return bReturn; 
	}
	
	public boolean verifyDisplayed(WebElement ele) {
		boolean bReturn = false;
		if(ele.isDisplayed()) {
			System.out.println("The element \""+ele+"\" is visible");
			bReturn = true;
		}else {
			System.out.println("The element \""+ele+"\" is not visible");
		}
		return bReturn;
	}
	
	public boolean verifyEnabled(WebElement ele) {
		boolean bReturn = false;
		if(ele.isEnabled()) {
			System.out.println("The element \""+ele+"\" is Enabled");
			bReturn =true;
		}else {
			System.out.println("The element is \""+ele+"\" not Enabled");
		}
		return bReturn;
	}
	
	public boolean verifySelected(WebElement ele) {
		boolean bReturn = false;
		if(ele.isSelected()) {
			System.out.println("The element \""+ele+"\" is selected");
			bReturn = true;
		}else {
			System.out.println("The element \""+ele+"\" is not selected");
		}
		return bReturn;
	}
	
	public void switchToAlert() {
		driver.switchTo().alert();
		System.out.println("Control switch from HTML to alert Successfully");
	}
	
	public void acceptAlert() {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();
		System.out.println("Alert \""+text+"\" accepted Successfully");
	}
	
	public void dismissAlert() {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.dismiss();
		System.out.println("Alert \""+text+"\" dismissed Successfully");
	}
	
	public String getAlertText() {
		String alTxt = driver.switchTo().alert().getText();
		System.out.println("Control switch from HTML to alert and fetched the Text: "+alTxt);
		return alTxt;
	}
	
	public void enterAlertText(String data) {
		driver.switchTo().alert().sendKeys(data);
		System.out.println("Control switch from HTML to alert and entered the given data is: "+data);
	}
	
	public void switchToWindow(int index) {
		Set<String> allWin = driver.getWindowHandles();
		List<String> allLs = new ArrayList<String>(allWin);
		driver.switchTo().window(allLs.get(index));
		System.out.println("The Window with index: "+index+" switched Successfully");
	}
	
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
		System.out.println("The frame with index: "+index+" switched Successfully");
	}
	
	public void switchToFrame(WebElement ele) {
		driver.switchTo().frame(ele);
		System.out.println("The frame with element: "+ele+" switched Successfully");
	}
	
	public void switchToFrame(String idOrName) {
		driver.switchTo().frame(idOrName);
		System.out.println("The frame with Id or Name: "+idOrName+" switched Successfully");
	}
	
	public void switchOutFrame() {
		driver.switchTo().defaultContent();
		System.out.println("Came out from frames Successfully");
	}
	
	public boolean verifyUrl(String url) {
		boolean bReturn = false;
		if(driver.getCurrentUrl().equals(url)) {
			System.out.println("Page Url: "+url+" matched Successfully");
			bReturn = true;
		}else {
			System.out.println("Page Url: "+url+" didn't match");
		}
		return bReturn;
	}
	
	public boolean verifyTitle(String title) {
		boolean bReturn = false;
		if(driver.getTitle().equals(title)) {
			System.out.println("Page title: \""+title+"\" matched Successfully");
			bReturn = true;
		}else {
			System.out.println("Page title: \""+title+"\" didn't match");
			
		}
		return bReturn;
	}
	
	public boolean verifyPartialTitle(String title) {
		boolean bReturn = false;
		if(driver.getTitle().contains(title)) {
			System.out.println("Page partial title: \""+title+"\" matched Successfully");
			bReturn = true;
		}else {
			System.out.println("Page partial title: \""+title+"\" didn't match");
			
		}
		return bReturn;
	}
	
	public void close() {
		driver.close();
		System.out.println("The browser closed Successfully");
	}
	
	public void quit() {
		driver.quit();
		System.out.println("The browser's all windows and tabs closed Successfully");
	}
	
}
