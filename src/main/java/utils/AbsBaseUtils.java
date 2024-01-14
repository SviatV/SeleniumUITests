package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbsBaseUtils {
  protected WebDriver driver;
  protected WebDriverWait wait;
  public AbsBaseUtils(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofMillis(500));
  }
}
