package factories;

import factories.implementations.ChromeConfig;
import factories.implementations.FirefoxConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
  private final static String BROWSER_VERSION = System.getProperty("browser.version");

  public static WebDriver getChromeDriver() {
    WebDriverManager.chromedriver().browserVersion(BROWSER_VERSION).setup();
    return new ChromeDriver(new ChromeConfig().getBrowserConfiguration());
  }

  public static WebDriver getFirefoxDriver() {
    WebDriverManager.firefoxdriver().browserVersion(BROWSER_VERSION).setup();
    return new FirefoxDriver(new FirefoxConfig().getBrowserConfiguration());
  }
}
