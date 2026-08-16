package br.com.ranieri.services;

import br.com.ranieri.dto.LoginRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class LoginService {
    public Response realizarLogin(LoginRequest loginRequest) {

        Response response = given()
                .body(loginRequest)
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                ;
        return response;
    }
}
