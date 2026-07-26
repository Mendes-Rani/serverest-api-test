package br.com.ranieri.usuarios.funcional;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.utils.DataGenerator;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CadastrarUsuarioComumSucessoTest extends BaseTest {
    @Test
    public void deveCadastrarUsuarioComumSucesso(){
        DataGenerator dataGenerator = new DataGenerator();
        String nome = dataGenerator.gerarNome();
        String email = dataGenerator.gerarEmail();
        String senha = dataGenerator.gerarSenha();

        String payload = "{\n" +
                "  \"nome\": \"" + nome + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + senha + "\",\n" +
                "  \"administrador\": \"false\"\n" +
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
        //System.out.println("idUsuario: " + idUsuario);

        given()
                .when()
                .get("/usuarios/{_id}", idUsuario)
                .then()
                .statusCode(200)
                .body("_id", equalTo(idUsuario))
                .body("nome", equalTo(nome))
                .body("email", equalTo(email))
                .body("password", equalTo(senha))
                .body("administrador", equalTo("false"))
                //.log().all()
                ;

    }
}
