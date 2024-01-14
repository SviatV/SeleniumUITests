package extensions;

import annotations.Page;
import factories.WebDriverFactory;
import listeners.MouseListener;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class WebDriverExtension implements BeforeEachCallback, AfterEachCallback {
  private WebDriver driver;

  @Override
  public void afterEach(ExtensionContext extensionContext) {
    if (driver != null) {
      driver.quit();
    }
  }

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    driver = new WebDriverFactory().create();
    MouseListener.setDriver(driver);
    var fieldsToInject = getAnnotatedFields(extensionContext);
    for (Field field : fieldsToInject) {
      if (field.getType().isAssignableFrom(WebDriver.class)) {
        field.setAccessible(true);
        field.set(extensionContext.getTestInstance().get(), driver);
      } else {
        Object pageInstance = field.getType().getDeclaredConstructor(WebDriver.class).newInstance(driver);
        field.set(extensionContext.getTestInstance().get(), pageInstance);
      }
    }
  }

  private Set<Field> getAnnotatedFields(ExtensionContext extensionContext) {
    Set<Field> fields = new HashSet<>();
    Class<?> testClass = extensionContext.getTestClass().get();
    for (Field field : testClass.getDeclaredFields()) {
      if (field.isAnnotationPresent(Page.class)) {
        fields.add(field);
      }
    }
    return fields;
  }
}
