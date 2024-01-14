package otus.tests;

import annotations.Page;
import extensions.WebDriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;
import utils.ReadTestData;

@ExtendWith(WebDriverExtension.class)
public class HomePageTest {

  @Page
  public MainPage mainPage;

  private String popularCourseName;

  @Test
  public void checkPopularCourse() {
    popularCourseName = ReadTestData.getProperty("popularCourseName");
    mainPage.open()
            .waitForPageLoaded()
            .checkPopularCourseDisplayed(popularCourseName);
  }

  @Test
  public void checkNotDupsInPopularCourse() {
    popularCourseName = ReadTestData.getProperty("popularCourseName");
    mainPage.open()
            .waitForPageLoaded()
            .checkNoDuplicatesInPopularCourses(popularCourseName);
  }

  @Test
  public void checkSpecializationCourseDisplayed() {
    String specializationCourseName = ReadTestData.getProperty("specializationCourseName");
    mainPage.open()
            .waitForPageLoaded()
            .checkSpecializationCourseDisplayed(specializationCourseName);
  }

  @Test
  public void selectEarliestSpecializationCourse() {
    mainPage.open()
            .waitForPageLoaded()
            .clickEarliestSpecializationCourse()
            .checkCourseTitleDisplayed();
  }

  @Test
  public void selectLatestSpecializationCourse() {
    mainPage.open()
            .waitForPageLoaded()
            .clickLatestSpecializationCourse().checkCourseTitleDisplayed();
  }

  @Test
  public void clickPopularCourseUsingMouse() {
    popularCourseName = ReadTestData.getProperty("popularCourseName");
    mainPage.open()
            .waitForPageLoaded()
            .clickCourseUsingMouse(popularCourseName).checkCourseTitleDisplayed();
  }
}
