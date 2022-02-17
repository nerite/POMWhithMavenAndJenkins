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
		String text = "";
		try {
			text = ele.getText();
			ele.click();
			reportStep("The element \""+text+"\" clicked Successfully", "PASS");
		} catch (WebDriverException e) {
			reportStep("The element \""+text+"\" couldn't click", "FAIL");
		}
	}
	
	public void clear(WebElement ele) {
		String text = "";
		try {
			text = ele.getAttribute("value");
			ele.clear();
			reportStep("The text \"" + text + "\" deleted Successfully", "PASS");
		} catch (Exception e) {
			reportStep("The text \"" + text + "\" couldn't be deleted", "FAIL");
		}
	}
	
	public void clearAndType(WebElement ele, String data) {
		String text = "";
		try {
			text = ele.getAttribute("value");
			ele.clear();
			ele.sendKeys(data);
			reportStep("The old text \""+text+"\" deleted and new text \""+data+"\" was typed Successfully", "PASS");
		} catch (Exception e) {
			reportStep("The old text \""+text+"\" deleted and new text \""+data+"\" couldn't be typed", "FAIL");
		}
	}
	
	public String getElementText(WebElement ele) {
		String text = null;
		try {
			text = ele.getText();
			reportStep("The elements text \"" +text+ "\" retreaved Successfully", "PASS");
		}catch (Exception e) {
			reportStep("The elements text \"" +text+ "\" couldn't be retreaved", "FAIL");
		}
			return text;
	}
	
	public String getBackgroundColor(WebElement ele) {
		String cssValue = null;
		try {
			cssValue = ele.getCssValue("color");
			reportStep("The cssValue \""+cssValue+"\" returns Successfully", "PASS");
		} catch (Exception e) {
			reportStep("The cssValue \""+cssValue+"\" doesn't return", "FAIL");
		}
		return cssValue;
	}
	
	public String getTypedText(WebElement ele) {
		String text = null;
		try {
			text = ele.getAttribute("value");
			reportStep("Typed text \"" + text + "\" retreaved Successfylly", "PASS");
		} catch (Exception e) {
			reportStep("Typed text \"" + text + "\" couldn't be retreaved", "FAIL");
		}
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
		try{ if(text.equals(expectedText)) {
				reportStep("Text \""+ text +"\" matched \""+expectedText+"\"", "PASS");
				bReturn = true;
			 }
		}catch (Exception e) {
			reportStep("Text \""+text+"\" didn't match \""+expectedText+"\"", "FAIL");
		}
		return bReturn;
	}
	
	public boolean verifyPartialText(WebElement ele, String expectedText) {
		String text = ele.getText();
		boolean bReturn = false;
		try { if(text.contains(expectedText)) {
				reportStep("Text \""+text+"\" contains partial text \""+expectedText+"\"", "PASS");
				bReturn = true;
				}
		}catch (Exception e) {
			reportStep("Text \""+text+"\" doesn't contains partial text \""+expectedText+"\"", "FAIL");
		}
		return bReturn;
	}
	
	public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
		boolean bReturn = false;
		try { if(ele.getAttribute(attribute).equals(value)) {
				reportStep("The attribute \""+attribute+"\" contains text \""+value, "PASS");
				bReturn = true;
				}
		}catch (Exception e) {
			reportStep("The attribute \""+attribute+"\" doesn't contain text \""+value, "FAIL");
		}
		return bReturn; 
	}
	
	public boolean verifyPartialAttribute(WebElement ele, String attribute, String value) {
		boolean bReturn = false;
		try { if(ele.getAttribute(attribute).contains(value)) {
				reportStep("The attribute \""+attribute+"\" contains partial text \""+value, "PASS");
				bReturn = true;
			}
		}catch (Exception e) {
			reportStep("The attribute \""+attribute+"\" doesn't contain partial text \""+value, "FAIL");
		}
		return bReturn; 
	}
	
	public boolean verifyDisplayed(WebElement ele) {
		boolean bReturn = false;
		try { if(ele.isDisplayed()) {
				reportStep("The element \""+ele+"\" is visible", "PASS");
				bReturn = true;
			}
		}catch (Exception e) {
			reportStep("The element \""+ele+"\" is not visible", "FAIL");
		}
		return bReturn;
	}
	
	public boolean verifyEnabled(WebElement ele) {
		boolean bReturn = false;
		try { if(ele.isEnabled()) {
				reportStep("The element \""+ele+"\" is Enabled", "PASS");
				bReturn = true;
			}
		}catch (Exception e) {
			reportStep("The element \""+ele+"\" is not Enabled", "FAIL");
		}
		return bReturn;
	}
	
	public boolean verifySelected(WebElement ele) {
		boolean bReturn = false;
		try { if(ele.isSelected()) {
				reportStep("The element \""+ele+"\" is selected", "PASS");
				bReturn = true;
			}
		}catch (Exception e) {
			reportStep("The element \""+ele+"\" is not selected", "FAIL");
		}
		return bReturn;
	}
	
	public void switchToAlert() {
		try {
			driver.switchTo().alert();
			reportStep("Control switched from HTML to alert Successfully", "PASS");
		} catch (Exception e) {
			reportStep("Control didn't switched from HTML to alert", "FAIL");
		}
	}
	
	public void acceptAlert() {
		String text = "";
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.accept();
			reportStep("Alert \""+text+"\" accepted Successfully", "PASS");
		} catch (Exception e) {
			reportStep("Alert \""+text+"\" wasn't accepted", "FAIL");
		}
	}
	
	public void dismissAlert() {
		String text = "";
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.dismiss();
			reportStep("Alert \""+text+"\" dismissed Successfully", "PASS");
		} catch (Exception e) {
			reportStep("Alert \""+text+"\" wasn't dismissed", "FAIL");
		}
	}
	
	public String getAlertText() {
		String alTxt = null;
		try {
			alTxt = driver.switchTo().alert().getText();
			reportStep("Control switch from HTML to alert and fetched the Text: "+alTxt, "PASS");
		} catch (Exception e) {
			reportStep("Control couldn't switch from HTML to alert and fetch the Text: "+alTxt, "FAIL");
		}
		return alTxt;
	}
	
	public void enterAlertText(String data) {
		try {
			driver.switchTo().alert().sendKeys(data);
			reportStep("Control switch from HTML to alert and entered the given data is: "+data, "PASS");
		} catch (Exception e) {
			reportStep("Control couldn't switch from HTML to alert and enter given data: "+data, "FAIL");
		}
	}
	
	public void switchToWindow(int index) {
		try {
			Set<String> allWin = driver.getWindowHandles();
			List<String> allLs = new ArrayList<String>(allWin);
			driver.switchTo().window(allLs.get(index));
			reportStep("The Window with index: "+index+" switched Successfully", "PASS");
		} catch (Exception e) {
			reportStep("The Window with index: "+index+" couldn't be switched", "FAIL");
		}
	}
	
	public void switchToFrame(int index) {
		try {
			driver.switchTo().frame(index);
			reportStep("The frame with index: "+index+" switched Successfully", "PASS");
		} catch (Exception e) {
			reportStep("The frame with index: "+index+" couldn't be switched", "FAIL");
		}
	}
	
	public void switchToFrame(WebElement ele) {
		try {
			driver.switchTo().frame(ele);
			reportStep("The frame with element: "+ele+" switched Successfully", "PASS");
		} catch (Exception e) {
			reportStep("The frame with element: "+ele+" couldn't be switched", "FAIL");
		}
	}
	
	public void switchToFrame(String idOrName) {
		try {
			driver.switchTo().frame(idOrName);
			reportStep("The frame with Id or Name: "+idOrName+" switched Successfully", "PASS");
		} catch (Exception e) {
			reportStep("The frame with Id or Name: "+idOrName+" couldn't be switched", "FAIL");
		}
	}
	
	public void switchOutFrame() {
		try {
			driver.switchTo().defaultContent();
			reportStep("Came out from frames Successfully", "PASS");
		} catch (Exception e) {
			reportStep("Couldn't came out from frames", "FAIL");
		}
	}
	
	public boolean verifyUrl(String url) {
		boolean bReturn = false;
		try { if(driver.getCurrentUrl().equals(url)) {
				reportStep("Page Url: "+url+" matched Successfully", "PASS");
				bReturn = true;
				}
		}catch (Exception e) {
			reportStep("Page Url: "+url+" didn't match", "FAIL");
		}
		return bReturn;
	}
	
	public boolean verifyTitle(String title) {
		boolean bReturn = false;
		try { if(driver.getTitle().equals(title)) {
				reportStep("Page title: \""+title+"\" matched Successfully", "PASS");
				bReturn = true;
			}
		}catch (Exception e) {
			reportStep("Page title: \""+title+"\" didn't match", "FAIL");
			
		}
		return bReturn;
	}
	
	public boolean verifyPartialTitle(String title) {
		boolean bReturn = false;
		try { if(driver.getTitle().contains(title)) {
				reportStep("Page partial title: \""+title+"\" matched Successfully", "PASS");
				bReturn = true;
				}
		}catch (Exception e) {
			reportStep("Page partial title: \""+title+"\" didn't match", "FAIL");
			
		}
		return bReturn;
	}
	
	public void close() {
		try {
			driver.close();
			reportStep("The browser closed Successfully", "PASS");
		} catch (Exception e) {
			reportStep("The browser couldn't be closed", "FAIL");
		}
	}
	
	public void quit() {
		try {
			driver.quit();
			reportStep("The browser's all windows and tabs closed Successfully", "PASS");
		} catch (Exception e) {
			reportStep("The browser's all windows and tabs couldn't be closed", "FAIL");
		}
	}
	
}
