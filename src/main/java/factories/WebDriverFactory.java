package factories;

import exceptions.BrowserNotSupportedException;
import listeners.MouseListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory implements IFactory<WebDriverListener> {
  private final String browserName = System.getProperty("browser");

  @Override
  public WebDriver create() {
    switch (browserName) {
      case "chrome": {
        WebDriver chromeDriver = BrowserFactory.getChromeDriver();
        return new EventFiringDecorator<>(new MouseListener()).decorate(chromeDriver);
      }
      case "firefox": {
        WebDriver firefoxDriver = BrowserFactory.getFirefoxDriver();
        firefoxDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        firefoxDriver.manage().window().maximize();
        return new EventFiringDecorator<>(new MouseListener()).decorate(firefoxDriver);
      }
      default: {
        throw new BrowserNotSupportedException(browserName);
      }
    }
  }
}
