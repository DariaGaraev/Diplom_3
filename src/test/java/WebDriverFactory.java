import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    private String browserName = "chrome";
    public WebDriver get() {
        WebDriver driver;
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get("https://stellarburgers.nomoreparties.site");
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\yandexdriver.exe");
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get("https://stellarburgers.nomoreparties.site");
                break;
            default:
                throw new RuntimeException("Некорректное название браузера");
        }
        return driver;
    }
}
