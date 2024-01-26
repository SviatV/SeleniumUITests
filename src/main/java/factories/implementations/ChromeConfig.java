package factories.implementations;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeConfig implements IBrowserSettings {

  @Override
  public ChromeOptions getBrowserConfiguration() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--start-maximized");
    chromeOptions.addArguments("--homepage=https://about:blank");
    return chromeOptions;
  }
}
