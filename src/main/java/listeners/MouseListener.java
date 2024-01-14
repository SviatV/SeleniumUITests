package listeners;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class MouseListener implements WebDriverListener {

  private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

  public static void setDriver(WebDriver driver) {
    DRIVER_THREAD_LOCAL.set(driver);
  }

  @Override
  public void beforeClick(WebElement element) {
    WebDriver driver = DRIVER_THREAD_LOCAL.get();
    if (driver instanceof JavascriptExecutor) {
      ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
    }
  }
}
