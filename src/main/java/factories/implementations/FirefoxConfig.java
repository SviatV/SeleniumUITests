package factories.implementations;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxConfig implements IBrowserSettings{

  private final String browserVersion = System.getProperty("browser.version");
  @Override
  public WebDriver configure() {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.addArguments("-fullscreen");
    firefoxOptions.addArguments("browser.startup.homepage", "about:blank");
    WebDriverManager.firefoxdriver().browserVersion(browserVersion).setup();
    return new FirefoxDriver(firefoxOptions);
  }
}
