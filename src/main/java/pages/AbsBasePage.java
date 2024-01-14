package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.AbsBaseUtils;

public abstract class AbsBasePage<T> extends AbsBaseUtils {

  public AbsBasePage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }
  public abstract T open();
}

