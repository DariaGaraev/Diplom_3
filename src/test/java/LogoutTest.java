import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.HomePage;
import page.ProfilePage;
import user.UserClient;
import user.UserCreate;

import static org.hamcrest.CoreMatchers.is;

public class LogoutTest {
    private WebDriver driver;
    private String token;
    private UserClient userClient = new UserClient();
    private final WebDriverFactory driverFactory = new WebDriverFactory();
    @Before
    @Step("Тестовые данные")
    public void setUp() {
        driver = driverFactory.get();
    }
    @After
    @Step("Удаление тестовых данных")
    public void tearDown() {
        if (token != null) {
            userClient.userDelete(token);
        }
        driver.quit();
    }
    @Test
    @DisplayName("Деавторизация пользователя по кнопке Выход")
    public void LogoutButtonTest() {
        userClient = new UserClient();
        UserCreate createUser = new UserCreate("drtrerterfgdf@yandex.ru", "asdasdasd", "Dasdax");
        ValidatableResponse responseCreate = userClient.newUser(createUser);
        token = responseCreate.extract()
                            .jsonPath()
                            .get("accessToken");
        ProfilePage profilePage = new ProfilePage(driver);
        HomePage homePage = new HomePage(driver);
        homePage.clickButtonPersonalAccount();
        profilePage.sendTheLoginForm("drtrerterfgdf@yandex.ru", "asdasdasd");
        homePage.clickButtonPersonalAccount();
        profilePage.clickButtonExit();
        String result = profilePage.checkSuccess();
        MatcherAssert.assertThat(result, is("Войти"));
    }
}
