package factories;

import exceptions.BrowserNotSupportedException;
import factories.implementations.ChromeConfig;
import factories.implementations.FirefoxConfig;
import listeners.MouseListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

public class WebDriverFactory implements IFactory<WebDriverListener> {
  private final String browserName = System.getProperty("browser");

  @Override
  public WebDriver create() {
    switch (browserName) {
      case "chrome": {
        return new EventFiringDecorator<>(new MouseListener()).decorate(new ChromeConfig().configure());
      }
      case "firefox": {
        return new EventFiringDecorator<>(new MouseListener()).decorate(new FirefoxConfig().configure());
      }
      default: {
        throw new BrowserNotSupportedException(browserName);
      }
    }
  }
}
