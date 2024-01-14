package utils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadTestData {
  private static final Properties PROPERTIES;

  static {
    PROPERTIES = new Properties();
    try (InputStream inputStream = ReadTestData.class.getClassLoader().getResourceAsStream("testData.properties")){
      if (inputStream != null) {
        PROPERTIES.load(inputStream);
      } else {
        throw new FileNotFoundException("testData.properties not found in classpath");
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to load testData.properties", e);
    }
  }

  public static String getProperty(String key) {
    return PROPERTIES.getProperty(key);
  }
}
