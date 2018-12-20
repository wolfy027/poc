package tests.calculator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class ValidationTest {
	public static AndroidDriver<AndroidElement> driver = null;
	public int SCREENSHOT_COUNTER = 0;

	@BeforeSuite
	private void setAppium() {
		/*
		 * driver = getAndroidDriver("One_Plus3T", "emulator-5554", "7.1.1", "Android",
		 * "uiautomator2", "/app/EriBank.apk", ".LoginActivity",
		 * "http://127.0.0.1:4723/wd/hub");
		 */

		driver = getAndroidDriverForDeviceFarm();

		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		System.out
				.println("Android Version\t:\t" + driver.getCapabilities().getCapability("platformVersion").toString());
		if (!driver.getCapabilities().getCapability("platformVersion").toString().startsWith("4")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
			driver.getCapabilities().merge(capabilities);
		} else {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
			driver.getCapabilities().merge(capabilities);
		}
	}

	@Test(priority = 1, enabled = true)
	public void enterCredentials() {
		driver.findElement(By.id("com.experitest.ExperiBank:id/usernameTextField")).sendKeys("company");
		takeScreenshot("Entered Username", driver);
		driver.findElement(By.id("com.experitest.ExperiBank:id/passwordTextField")).sendKeys("company");
		takeScreenshot("Entered Password", driver);
	}

	@Test(priority = 2, enabled = true)
	public void login() {
		driver.findElement(By.id("com.experitest.ExperiBank:id/usernameTextField")).sendKeys("company");
		takeScreenshot("Entered Username", driver);
		driver.findElement(By.id("com.experitest.ExperiBank:id/passwordTextField")).sendKeys("company");
		takeScreenshot("Entered Password", driver);
		driver.findElement(By.id("com.experitest.ExperiBank:id/loginButton")).click();
		takeScreenshot("Clicked Login Button", driver);

		driver.findElement(By.id("com.experitest.ExperiBank:id/logoutButton")).click();
		takeScreenshot("Logged Out", driver);
	}

	@Test(priority = 3, enabled = true)
	public void componentTesting() {
		driver.findElement(By.id("com.experitest.ExperiBank:id/usernameTextField")).sendKeys("company");
		takeScreenshot("Entered Username", driver);
		driver.findElement(By.id("com.experitest.ExperiBank:id/passwordTextField")).sendKeys("company");
		takeScreenshot("Entered Password", driver);
		driver.findElement(By.id("com.experitest.ExperiBank:id/loginButton")).click();
		takeScreenshot("Clicked Login Button", driver);

		driver.findElement(By.id("com.experitest.ExperiBank:id/makePaymentButton")).click();
		takeScreenshot("Clicked Make Payment Button", driver);
		driver.findElement(By.id("com.experitest.ExperiBank:id/cancelButton")).click();
		takeScreenshot("Clicked Cancel Button", driver);

		driver.findElement(By.id("com.experitest.ExperiBank:id/mortageRequestButton")).click();
		takeScreenshot("Clicked Mortage Request Button", driver);
		driver.findElement(By.id("com.experitest.ExperiBank:id/cancelButton")).click();
		takeScreenshot("Clicked Cancel Button", driver);

		driver.findElement(By.id("com.experitest.ExperiBank:id/expenseReportButton")).click();
		takeScreenshot("Clicked Expense Report Button", driver);
		driver.findElement(By.id("com.experitest.ExperiBank:id/backButton")).click();
		takeScreenshot("Clicked Cancel Button", driver);

		driver.findElement(By.id("com.experitest.ExperiBank:id/logoutButton")).click();
		takeScreenshot("Logged Out", driver);
	}

	@Test(priority = 4, enabled = true)
	public void makePayment() {
		driver.findElement(By.id("com.experitest.ExperiBank:id/usernameTextField")).sendKeys("company");
		takeScreenshot("Entered Username", driver);
		driver.findElement(By.id("com.experitest.ExperiBank:id/passwordTextField")).sendKeys("company");
		takeScreenshot("Entered Password", driver);
		driver.findElement(By.id("com.experitest.ExperiBank:id/loginButton")).click();
		takeScreenshot("Clicked Login Button", driver);

		driver.findElement(By.id("com.experitest.ExperiBank:id/makePaymentButton")).click();
		takeScreenshot("Clicked Make Payment Button", driver);

		driver.findElement(By.id("com.experitest.ExperiBank:id/phoneTextField")).sendKeys("9910060389");
		takeScreenshot("Entered Phone Number", driver);
		driver.findElement(By.id("com.experitest.ExperiBank:id/nameTextField")).sendKeys("John Wick");
		takeScreenshot("Entered Name", driver);
		driver.findElement(By.id("com.experitest.ExperiBank:id/amountTextField")).sendKeys("1");
		takeScreenshot("Entered Amount", driver);
		driver.findElement(By.id("com.experitest.ExperiBank:id/countryButton")).click();
		takeScreenshot("Clicked Country Button", driver);
		driver.findElement(By.xpath(
				"//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.TextView[1]"))
				.click();
		takeScreenshot("Selected First Country", driver);
		driver.findElement(By.id("com.experitest.ExperiBank:id/sendPaymentButton")).click();
		takeScreenshot("Send Payment", driver);
		driver.findElement(By.id("android:id/button1")).click();
		takeScreenshot("Confirm", driver);

		driver.findElement(By.id("com.experitest.ExperiBank:id/logoutButton")).click();
		takeScreenshot("Logged Out", driver);
	}

	@AfterMethod
	public void resetScreenshotCounter() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SCREENSHOT_COUNTER = 0;
	}

	@AfterSuite
	public void tearDown() throws Exception {
		driver.quit();
	}

	public AndroidDriver<AndroidElement> getAndroidDriver(String deviceName, String udid, String androidVersion,
			String platformName, String automationName, String apkPath, String appActivity, String appiumUrl) {
		AndroidDriver<AndroidElement> driver = null;
		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("udid", udid);
		capabilities.setCapability(CapabilityType.VERSION, androidVersion);
		capabilities.setCapability("platformName", platformName);
		capabilities.setCapability("automationName", automationName);
		capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + apkPath);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
		capabilities.setCapability("newCommandTimeout", 10000);
		try {
			driver = new AndroidDriver<AndroidElement>(new URL(appiumUrl), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}

	public boolean takeScreenshot(final String name, WebDriver driver) {
		String invokingMethodName = new Exception().getStackTrace()[1].getMethodName();
		String screenshotFileName = invokingMethodName + "_" + (++SCREENSHOT_COUNTER) + "_" + name;
		String screenshotDirectory = System.getProperty("appium.screenshots.dir",
				System.getProperty("java.io.tmpdir", ""));
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		return screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", screenshotFileName)));
	}

	public static AndroidDriver<AndroidElement> getAndroidDriverForDeviceFarm() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
		try {
			return new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
