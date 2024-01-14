package factories;

import org.openqa.selenium.WebDriver;

public interface IFactory<T> {
  WebDriver create();
}
