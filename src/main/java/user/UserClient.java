package user;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import page.RegPage;

import static io.restassured.RestAssured.given;

public class UserClient {
    public static final String CREATE_USER = "/api/auth/register";
    public static final String LOGIN_USER = "/api/auth/login";
    public static final String LOGOUT_USER = "api/auth/logout";
    public static RequestSpecification getRequest() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri("https://stellarburgers.nomoreparties.site");
    }
    @Step("Создание пользователя - параметризированный тест")
    public ValidatableResponse createNewUser(RegPage registrationPage) {
        return getRequest()
                .header("Content-type", "application/json")
                .body(registrationPage)
                .when()
                .post(CREATE_USER)
                .then();
    }
    @Step("Создание пользователя")
    public ValidatableResponse newUser(UserCreate userCreate) {
        return getRequest()
                .header("Content-type", "application/json")
                .body(userCreate)
                .when()
                .post(CREATE_USER)
                .then();
    }
    @Step("Логин существующего пользователя")
    public static ValidatableResponse loginUser(UserCreate userLogin){
        return getRequest()
                .header("Content-type", "application/json")
                .body(userLogin)
                .when()
                .post(LOGIN_USER)
                .then();
    }
    @Step("Выход из кабинета")
    public ValidatableResponse logoutUser(String refreshToken, UserCreate userCreate) {
        return getRequest()
                .header("application/json", refreshToken)
                .body(userCreate)
                .when()
                .post(LOGOUT_USER)
                .then();
    }
    @Step("Удаление пользователя")
    public ValidatableResponse userDelete(String accessToken){
        return getRequest()
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth" + "/" + accessToken)
                .then();
    }
    public static UserCreate getDataCreatedUser(String email, String password, String name) {
        UserCreate userCreate = new UserCreate(email,password, name);
        userCreate.setEmail(email);
        userCreate.setPassword(password);
        userCreate.setName(name);
        return userCreate;
    }
}
