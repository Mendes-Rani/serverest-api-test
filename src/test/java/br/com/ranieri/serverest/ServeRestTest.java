package com.aula.pb.inicio;

import java.util.Date;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ServeRestTest {

    @BeforeClass
    public static void setup(){
        baseURI = "http://localhost:3000";
        port = 3000;
    }

    @Test
    public void deveBuscarListaUsuarios() {
        given()
                .when()
                    .get("/usuarios")
                .then()
                .log().all()
                    .assertThat()
                    .statusCode(200)
//                .and()
//                    .body("quantidade", equalTo(1))
        ;
    }

    @Test
    public void deveRetornarUsuarioExistente(){
        given()
                .when()
                .get("/usuarios/{id}", "0uxuPY0cbmQhpEz1")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("nome", equalTo("Fulano da Silva"))
                .and()
                .body("email", equalTo("fulano@qa.com"))
                ;
    }

    @Test
    public void deveCriarNovoUsuario(){
        Date date = new Date();
        String email = date.getTime() + "@qa.com.br";

        String payload = "{\n" +
                "  \"nome\": \"Vitor Sales\",\n" +
                "  \"email\":\"" + email + "\",\n" +
                "  \"password\": \"teste\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        given()
                .body(payload)
                .contentType("application/json")
                .when()
                .post("/usuarios")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .and()
                .body("message",  equalTo("Cadastro realizado com sucesso"))
                ;


    }

    @Test
    public void deveAtualizarUsuario(){
        String payload = "{\n" +
                "  \"nome\": \"Teste Mendes Alterado via RestAssured\",\n" +
                "  \"email\": \"mendesteste@qa.com.br\",\n" +
                "  \"password\": \"teste\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        given()
                .body(payload)
                .contentType(ContentType.JSON)
                .when()
                .put("usuarios/{id}", "jIOT3F9QQaJUBWj5")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body("message", equalTo("Registro alterado com sucesso"))
                ;
    }

    @Test
    public void deveExcluirUsuario(){
        Date date = new Date();
        String email = date.getTime() + "@qa.com.br";

        String payload = "{\n" +
                "  \"nome\": \"Vitor Sales\",\n" +
                "  \"email\":\"" + email + "\",\n" +
                "  \"password\": \"teste\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        String id_para_excluir = given()
                .body(payload)
                .contentType("application/json")
                .when()
                .post("/usuarios")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .and()
                .body("message",  equalTo("Cadastro realizado com sucesso"))
                .and()
                .extract()
                .path("_id")
        ;

        given()
                .when()
                .delete("/usuarios/{id}", id_para_excluir)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body("message", equalTo("Registro excluído com sucesso"))
                ;
    }
}
