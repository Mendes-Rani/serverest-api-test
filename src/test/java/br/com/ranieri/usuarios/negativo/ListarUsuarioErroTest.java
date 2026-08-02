package br.com.ranieri.usuarios.negativo;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.utils.DataGenerator;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class ListarUsuarioErroTest extends BaseTest {
    @Test
    public void naoDeveListarUsuarioInexistente(){
        DataGenerator dataGenerator = new DataGenerator();
        String idInexistente = dataGenerator.gerarIdAleatorio();

        given()
                .when()
                .get("/usuarios/{_id}", idInexistente)
                .then()
                .statusCode(400)
                .body("message", is("Usuário não encontrado"))
                ;

    }
}
