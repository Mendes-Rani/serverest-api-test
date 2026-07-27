package br.com.ranieri.usuarios.funcional;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.utils.DataGenerator;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CriarUsuarioViaPutSucessoTest extends BaseTest {
    @Test
    public void deveCriarUsuarioQuandoIdNaoExistenteTest(){
        DataGenerator dataGenerator = new DataGenerator();
        String nome = dataGenerator.gerarNome();
        String email = dataGenerator.gerarEmail();
        String senha = dataGenerator.gerarSenha();
        String idInexistente = dataGenerator.gerarIdAleatorio();

        String payloadNovoUsuario = "{\n" +
                "  \"nome\": \"" + nome + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + senha + "\",\n" +
                "  \"administrador\": \"false\"\n" +
                "}";

        //System.out.println("Payload novo usuário: " + payloadNovoUsuario);

        String novoIdGerado = given()
                .body(payloadNovoUsuario)
                .contentType(ContentType.JSON)
                .when()
                .put("/usuarios/{_id}", idInexistente)
                .then()
                .statusCode(201)
                .body("message", is("Cadastro realizado com sucesso"))
                .body("_id", notNullValue())
                .extract()
                .path("_id")
                ;
        //System.out.println("novoIdGerado: " + novoIdGerado);

        given()
                .when()
                .get("/usuarios/{_id}", novoIdGerado)
                .then()
                .statusCode(200)
                .body("_id", equalTo(novoIdGerado))
                .body("nome", equalTo(nome))
                .body("email", equalTo(email))
                .body("password", equalTo(senha))
                .body("administrador", equalTo("false"))
                //.log().all()
                ;
    }
}
