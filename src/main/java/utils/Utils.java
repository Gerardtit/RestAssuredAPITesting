package utils;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

public class Utils {
    public static String randomString = RandomStringUtils.randomAlphabetic(6);

    public static Response response;
    public static String getFirstName() {
        return ("John"+randomString);
    }

    public static String getLastName() {
        return ("Lenon"+randomString);
    }

    public static String getEmail() {
        return ("John"+randomString+"@gmail.com");
    }

    public static String getPassword() {
        return ("pass"+randomString);
    }

    public static void setResponse(Response resp) {
        response = resp;
    }

    public static Response getResponse() {
        return response;
    }
}
