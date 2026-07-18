package br.com.ranieri.base;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public abstract class BaseTest {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "http://localhost:3000";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
