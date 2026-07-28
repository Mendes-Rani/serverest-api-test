package br.com.ranieri.usuarios.negativo;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.utils.DataGenerator;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class CadastrarUsuarioNegativoTest extends BaseTest {
    @Test
    public void naoDeveCadastrarUsuarioComEmailExistenteTest(){

        DataGenerator dataGenerator = new DataGenerator();
        String primeiroNome = dataGenerator.gerarNome();
        String primeiroEmail = dataGenerator.gerarEmail();
        String primeiraSenha = dataGenerator.gerarSenha();

        String segundoNome = dataGenerator.gerarNome();
        String segundaSenha = dataGenerator.gerarSenha();

        String payloadUsuarioOriginal = "{\n" +
                "  \"nome\": \"" + primeiroNome + "\",\n" +
                "  \"email\": \"" + primeiroEmail + "\",\n" +
                "  \"password\": \"" + primeiraSenha + "\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        String payloadUsuarioDuplicado = "{\n" +
                "  \"nome\": \"" + segundoNome + "\",\n" +
                "  \"email\": \"" + primeiroEmail + "\",\n" +
                "  \"password\": \"" + segundaSenha + "\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        //System.out.println("Payload 1: " + payloadUsuarioOriginal);
        //System.out.println("Payload 2: " + payloadUsuarioDuplicado);

        given()
                .body(payloadUsuarioOriginal)
                .contentType(ContentType.JSON)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("message", is("Cadastro realizado com sucesso"))
                //.log().all()
                ;
        //System.out.println("--------------------------------");

        given()
                .body(payloadUsuarioDuplicado)
                .contentType(ContentType.JSON)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(400)
                .body("message", is("Este email já está sendo usado"))
                //.log().all()
        ;

    }
}
