package br.com.ranieri.funcional;

import br.com.ranieri.base.BaseTest;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class UsuarioTest extends BaseTest {

    @Test
    public void deveListarUsuariosCadastrados(){
        given()
                .baseUri(baseURI)
        .when()
                .get("/usuarios")
        .then()
                .log().all()
                .statusCode(200)
                ;
    }

}
