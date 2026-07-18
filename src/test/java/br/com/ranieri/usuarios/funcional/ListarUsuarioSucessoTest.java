package br.com.ranieri.usuarios.funcional;

import br.com.ranieri.base.BaseTest;
import io.restassured.path.json.JsonPath;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ListarUsuarioSucessoTest extends BaseTest {
    // Implementar os testes de listagem de usuário com sucesso

    @Test
    public void deveListarUsuariosCadastrados(){
        given()
                .baseUri(baseURI)
        .when()
                .get("/usuarios")
        .then()
                .statusCode(200)
                .body("quantidade", is(notNullValue()))
                ;
    }

    @Test
    public void deveListarUsuarioEspecifico(){

        String response = get("/usuarios").asString(); // Retorna a resposta da requisição como uma String
        JsonPath jsonPath = new JsonPath(response); // Cria um objeto JsonPath a partir da resposta
        String id_usuario = jsonPath.getString("usuarios[0]._id"); // Pega o id do primeiro usuário da lista

        //System.out.println("id_usuario: " + id_usuario);

        given()
                .baseUri(baseURI)
        .when()
                .get("/usuarios/{_id}", id_usuario)
        .then()
                //.log().all()
                .assertThat()
                .statusCode(200)
                .body("nome", is(notNullValue()))
                .body("email", is(notNullValue()))
                .body("password", is(notNullValue()))
                .body("administrador", is(notNullValue()))
                ;

    }

}
