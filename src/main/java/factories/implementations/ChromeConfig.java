package factories.implementations;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeConfig implements IBrowserSettings {

  private final String browserVersion = System.getProperty("browser.version");
  @Override
  public WebDriver configure() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--start-maximized");
    chromeOptions.addArguments("--homepage=https://about:blank");
    WebDriverManager.chromedriver().browserVersion(browserVersion).setup();
    return new ChromeDriver(chromeOptions);
  }
}
