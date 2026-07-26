package br.com.ranieri.services;

import br.com.ranieri.utils.DataGenerator;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class UsuarioService {
    private DataGenerator dataGenerator = new DataGenerator();

    public String criarUsuario(){
        String nome = dataGenerator.gerarNome();
        String email = dataGenerator.gerarEmail();
        String senha = dataGenerator.gerarSenha();

        String payload = "{\n" +
                "  \"nome\": \"" + nome + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + senha + "\",\n" +
                "  \"administrador\": \"false\"\n" +
                "}";

        return given()
                .body(payload)
                .contentType(ContentType.JSON)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("message", is("Cadastro realizado com sucesso"))
                .body("_id", is(notNullValue()))
                .extract()
                .path("_id")
                ;

    }
}
