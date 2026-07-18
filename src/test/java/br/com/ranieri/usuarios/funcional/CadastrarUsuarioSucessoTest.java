package br.com.ranieri.usuarios.funcional;

import br.com.ranieri.base.BaseTest;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.Locale;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CadastrarUsuarioSucessoTest extends BaseTest {
    // Implementar os testes de cadastro de usuário com sucesso
    Faker faker = new Faker(new Locale("pt-BR"));
    String name = faker.name().fullName();
    String email = faker.internet().emailAddress();
    String password = faker.number().digits(6);

    @Test
    public void deveCadastrarUsuarioAdminComSucesso(){
        String payload = "{\n" +
                "  \"nome\": \"" + name + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + password + "\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";
        //System.out.println("Payload: " + payload);
        //System.out.println("--------------------------------");

        String idUsuario = given()
                .body(payload)
                .contentType(ContentType.JSON)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("message", is("Cadastro realizado com sucesso"))
                .extract()
                .path("_id")
                ;
        System.out.println("idUsuario: " + idUsuario);

        given()
                .baseUri(baseURI)
                .when()
                .get("/usuarios/{id}", idUsuario)
                .then()
                .statusCode(200)
                .body("_id", equalTo(idUsuario))
                .body("nome", equalTo(name))
                .body("email", equalTo(email))
                .body("password", equalTo(password))
                .body("administrador", equalTo("true"))
                ;

    }

}
