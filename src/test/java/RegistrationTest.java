import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page.RegPage;
import user.UserClient;
import user.UserCreate;
import static constant.constants.*;
import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class RegistrationTest {
    private WebDriver driver;
    String token;
    private final UserClient userClient = new UserClient();
    private final WebDriverFactory driverFactory = new WebDriverFactory();
    private final String name;
    private final String email;
    private final String password;
    public RegistrationTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    @Before
    public void setUp() {
        driver = driverFactory.get();
    }
    @After
    @Step("Удаление тестовых данных")
    public void deleteUser() {
        UserCreate loginUser = UserClient.getDataCreatedUser(email, password, name);
        token = UserClient.loginUser(loginUser)
                .extract()
                .jsonPath()
                .get("accessToken");
        if (token != null) {
            userClient.userDelete(token);
        }
        driver.quit();
    }
    @Parameterized.Parameters(name = "userName={0}, userEmail = {1}, userPassword={2}")
    public static Object[][] dataForSuccessfulRegistration() {
        return new Object[][]{
                {RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(3)+ "@google.com", RandomStringUtils.randomAlphabetic(7)},
                {RandomStringUtils.randomAlphabetic(3), RandomStringUtils.randomAlphabetic(4)+ "@mail.com", RandomStringUtils.randomAlphabetic(7)},
                {RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(5)+ "@yandex.com", RandomStringUtils.randomAlphabetic(7)},
                {RandomStringUtils.randomAlphabetic(6), RandomStringUtils.randomAlphabetic(6)+ "@vk.com", RandomStringUtils.randomAlphabetic(7)},
                {RandomStringUtils.randomAlphabetic(3), RandomStringUtils.randomAlphabetic(7)+ "@list.com", RandomStringUtils.randomAlphabetic(7)}
        };
    }
    @Test
    @DisplayName("Создание уникального пользователя")
    public void createUserTest() {
        driver.get(REGISTER_PAGE);
        RegPage registrationPage = new RegPage(driver);
        registrationPage.registerNewUser(name, email, password);
        String result = registrationPage.findTextEnter();
        MatcherAssert.assertThat(result, is("Войти"));
    }
}
