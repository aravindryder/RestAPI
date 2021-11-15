package com.BaseClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base_Class {

	public static WebDriver wd;
	
	public static String value;

	public static WebDriver Browser(String url) {

		if (url.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");
			wd = new ChromeDriver();
		}

		else if (url.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\Driver\\firefoxdriver.exe");
			wd = new FirefoxDriver();
		}

		else if (url.equalsIgnoreCase("opera")) {
			System.setProperty("webdriver.opera.driver", 
					System.getProperty("user.dir") + "\\Driver\\operadriver.exe");
			wd = new OperaDriver();
		}

		else if (url.equalsIgnoreCase("safari")) {
			System.setProperty("webdriver.safari.driver",
					System.getProperty("user.dir") + "\\Driver\\safaridriver.exe");
			wd = new SafariDriver();
		}

		else if (url.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", 
					System.getProperty("user.dir") + "\\Driver\\edgedriver.exe");
			wd = new EdgeDriver();
		}

		else if (url.equalsIgnoreCase("internetExplore")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\Driver\\internetexploredriver.exe");
			wd = new InternetExplorerDriver();
		}
		
		else {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");
			ChromeOptions co = new ChromeOptions();
			co.addArguments("incognito");
			wd = new ChromeDriver(co);
		}

		wd.manage().window().maximize();
		return wd;
	}

	public static void Get_URL(String s) {
		wd.get(s);
	}
	
	public static void sleep(int i) throws Throwable {
		Thread.sleep(i);
	}
	
	public static void Pass(WebElement w, String s) {
		w.sendKeys(s);
	}

	public static void click(WebElement c) {
		c.click();
	}
	
	public static void navigate_back() {
		wd.navigate().back();
	}
	
	public static void navigate_forward() {
		wd.navigate().forward();
	}
	
	public static void navigate_to(String s) {
		wd.navigate().to(s);
	}
	
	public static void navigate_refresh() {
		wd.navigate().refresh();
	}
	
	public static void alert() {
		wd.switchTo().alert();
	}
	
	public static void Text(WebElement we) {
		we.getText();
	}
	
	public static void GetTitle(String s) {
		wd.getTitle();
	}
	
	public static void GetCurrentUrl(WebElement we, String url) {
		wd.getCurrentUrl();
	}
	
	public static void GetPageSource(String s) {
		wd.getPageSource();
	}
	
	public static void Attribute(WebElement we, String url) {
		we.getAttribute(url);
	}
	
	public static void display(WebElement Display) {
		Display.isDisplayed();
	}
	
	public static void enabled(WebElement Enable) {   
		Enable.isEnabled();
	}
	
	public static void selected(WebElement Selected) {
		Selected.isSelected();
	}
	
	public static void Drop_Down(WebElement we, String str, String vt) {
		
		Select s = new Select(we);
		
		if (str.equalsIgnoreCase("byvalue")) {
			s.selectByValue(vt);	
		}
		else if (str.equalsIgnoreCase("byvisibletext")){
			s.selectByVisibleText(vt);	
		}
		else if (str.equalsIgnoreCase("byindex")) {
			int parseInt = Integer.parseInt(vt);
			s.selectByIndex(parseInt);	
		}
		else {
			s.isMultiple();
		}
	}
	
	public static String excel_particular_Data(String path, int Sheet, int RowData, int column) throws Throwable {

		File f = new File(System.getProperty("user.dir") + "\\Excel\\" + path);
		FileInputStream fis = new FileInputStream(f);		
		Workbook w = new XSSFWorkbook(fis);				//--------> UpCasting
		Sheet sheetAt = w.getSheetAt(Sheet);
		
		Row row = sheetAt.getRow(RowData);
		Cell cell = row.getCell(column);
		CellType cellType = cell.getCellType();
		
		if (cellType.equals(CellType.STRING)) {
			
			value = cell.getStringCellValue();
			
		} else if (cellType.equals(CellType.NUMERIC)) {
			
			double numericCellValue = cell.getNumericCellValue();
			
			value = Double.toString(numericCellValue);
		} 
		
		return value;
	}
	
	public static void implicity() {
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public static void explict(WebElement we) {
		WebDriverWait wait = new WebDriverWait(wd, 30);
		wait.until(ExpectedConditions.visibilityOf(we));
	}
	
	public static void Scroll_Down() {
		JavascriptExecutor js = (JavascriptExecutor) wd;
		js.executeScript("window.scroll(0, 1000)", "");
	}
	
	public static void Scroll_ToEnd() {
		JavascriptExecutor js = (JavascriptExecutor) wd;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)", "");
	}
	
	public static void Scroll_Up() {
		JavascriptExecutor js = (JavascriptExecutor) wd;
		js.executeScript("window.scroll(0, 0)", "");
	}
	
	public static void Action_Mouse(WebElement we) {
		Actions a = new Actions(wd);
		a.contextClick(we).build().perform();
	}
	
	public static void Action_Move(WebElement we) {
		Actions a = new Actions(wd);
		a.moveToElement(we).build().perform();
	}
	
	public static void Robot1() throws AWTException {
		Robot r1 = new Robot();
		r1.keyPress(KeyEvent.VK_DOWN);
		r1.keyRelease(KeyEvent.VK_DOWN);
	}
	
	public static void Robot2() throws AWTException {
		Robot r2 = new Robot();
		r2.keyPress(KeyEvent.VK_ENTER);
		r2.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public static void windowshandling(String s) {
		wd.switchTo().window(s);
	}
	
	public static void snap(String filepath) throws Exception {
		TakesScreenshot shot = (TakesScreenshot) wd;
		File sou = shot.getScreenshotAs(OutputType.FILE);
		File Des = new File(System.getProperty("user.dir") + "\\ScreenShot\\" + filepath);
		FileUtils.copyFile(sou, Des);
	}

	public static void close() {
		wd.close();
	}

	public static void quit() {
		wd.quit();
	}
}