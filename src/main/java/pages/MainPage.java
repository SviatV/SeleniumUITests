package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainPage extends AbsBasePage {
  private final String path = System.getProperty("base.url");
  private final static Pattern DATE_MONTH_PATTERN = Pattern.compile("С (\\d{1,2} \\p{IsCyrillic}+) \\d+ месяцев?");

  @FindBy(xpath = "//body/div[@id='__next']/div[1]/div[1]/div[1]/a[1]/img[1]")
  private WebElement logo;

  @FindBy(xpath = "//body/div[@id='__next']/div[1]/main[1]/div[1]/section[1]/div[1]/*//h5")
  private List<WebElement> popularCourseNames;

  @FindBy(xpath = "//body/div[@id=\'__next\']/div[1]/main[1]/div[1]/section[2]/div[1]/*//h5")
  private List<WebElement> specializationCourseNames;

  @FindBy(xpath = "//body/div[@id='__next']/div[1]/main[1]/div[1]/section[2]/div[1]/*//a//div//span")
  private List<WebElement> specializationCourseDates;

  @FindBy(xpath = "//h1")
  private WebElement courseTitle;

  @FindBy(xpath = "//h5")
  private WebElement courseTitleHomePage;

  @FindBy(xpath = "//h5[contains(text(),'С#')]")
  private WebElement courseCSharp;

  @FindBy(xpath = "//h1[contains(text(),'C# Developer')]")
  private WebElement headerCSharpCoursePage;

  public MainPage(WebDriver driver) {
    super(driver);
  }

  public MainPage open() {
    driver.get(path);
    return new MainPage(driver);
  }

  public MainPage waitForPageLoaded() {
    wait.until(ExpectedConditions.visibilityOf(logo));
    return this;
  }

  public void checkPopularCourseDisplayed(String popularCourse) {
    Stream<WebElement> actualElements = filterPopularCourses(popularCourse).stream().distinct();
    Optional<WebElement> firstElement = actualElements.findFirst();
    Assertions.assertTrue(firstElement.get().isDisplayed(), "The course is not displayed");
  }

  public void checkSpecializationCourseDisplayed(String specializationCourse) {
    Stream<WebElement> actualElements = filterSpecializationCourses(specializationCourse).stream().distinct();
    Optional<WebElement> firstElement = actualElements.findFirst();
    Assertions.assertTrue(firstElement.get().isDisplayed(), "The course is not displayed");
  }

  public void checkNoDuplicatesInPopularCourses(String popularCourse) {
    int actualSizeOfArray = filterPopularCourses(popularCourse).size();
    Assertions.assertEquals(1, actualSizeOfArray, "The size of the array > 1. Looks like there are duplicates");
  }

  public MainPage clickEarliestSpecializationCourse() {
    LocalDate earliestDate = findEarliestDate();
    if (earliestDate != null) {
      List<WebElement> dates = findSpecializationCourseDates(earliestDate);
      dates.get(0).click();
    }
    return this;
  }

  public MainPage clickLatestSpecializationCourse() {
    LocalDate latestDate = findLatestDate();
    if (latestDate != null) {
      List<WebElement> dates = specializationCourseDates.stream()
              .filter(element -> {
                LocalDate extractDate = extractDatesMonth(element.getText());
                return extractDate != null && extractDate.equals(latestDate);
              }).toList();
      if (!dates.isEmpty()) {
        dates.get(0).click();
      }
    }
    return this;
  }

  public MainPage clickCourseUsingMouse(String popularCourse) {
    actions.click(filterPopularCourses(popularCourse).get(0)).perform();
    return this;
  }

  public void checkCourseTitleDisplayed() {
    Assertions.assertTrue(courseTitle.isDisplayed(), "Course title is not displayed");
  }

  private List<WebElement> findSpecializationCourseDates(LocalDate earliestDate) {
    return specializationCourseDates.stream()
            .filter(element -> {
              LocalDate extractDate = extractDatesMonth(element.getText());
              return extractDate != null && extractDate.equals(earliestDate);
            })
            .toList();
  }

  private List<WebElement> filterPopularCourses(String popularCourse) {
    return popularCourseNames.stream().filter(element -> element.getText().equals(popularCourse)).collect(Collectors.toList());
  }

  private List<WebElement> filterSpecializationCourses(String specializationCourse) {
    return specializationCourseNames.stream().filter(element -> element.getText().contains(specializationCourse)).collect(Collectors.toList());
  }

  private LocalDate findEarliestDate() {
    return specializationCourseDates.stream()
            .map(WebElement::getText)
            .filter(this::matchesDateMonthPattern)
            .map(this::extractDatesMonth)
            .reduce((date1, date2) -> date1.isBefore(date2) ? date1 : date2)
            .orElse(null);
  }

  private LocalDate findLatestDate() {
    return specializationCourseDates.stream()
            .map(WebElement::getText)
            .filter(this::matchesDateMonthPattern)
            .map(this::extractDatesMonth)
            .reduce((date1, date2) -> date1.isBefore(date2) ? date2 : date1)
            .orElse(null);
  }

  private boolean matchesDateMonthPattern(String text) {
    return DATE_MONTH_PATTERN.matcher(text).matches();
  }

  private LocalDate extractDatesMonth(String text) {
    Matcher matcher = DATE_MONTH_PATTERN.matcher(text);
    if (matcher.find()) {
      String dateText = matcher.group(1);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", new Locale("ru"));
      MonthDay monthDay = MonthDay.parse(dateText, formatter);
      if (monthDay != null) {
        return Year.now().atMonth(monthDay.getMonth()).atDay(monthDay.getDayOfMonth());
      }
    }
    return null;
  }
}

