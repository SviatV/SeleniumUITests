package factories.implementations;

import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxConfig implements IBrowserSettings{

  @Override
  public FirefoxOptions getBrowserConfiguration() {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.addArguments("browser.startup.homepage", "about:blank");
    return firefoxOptions;
  }
}
