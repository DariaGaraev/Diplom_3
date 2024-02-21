package user;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static UserCreate getRandomUser() {
        String name = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String email = RandomStringUtils.randomAlphabetic(10)+ "@google.com";

        return new UserCreate(name, password, email);
    }

}
