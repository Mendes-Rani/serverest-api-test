package br.com.ranieri.usuarios.funcional;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.dto.Usuario;
import br.com.ranieri.utils.DataGenerator;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CriarUsuarioViaPutSucessoTest extends BaseTest {
    @Test
    public void deveCriarUsuarioQuandoIdNaoExistente(){
        DataGenerator dataGenerator = new DataGenerator();

        String idInexistente = dataGenerator.gerarIdAleatorio();

        Usuario usuarioNovo = new Usuario();
        usuarioNovo.setNome(dataGenerator.gerarNome());
        usuarioNovo.setEmail(dataGenerator.gerarEmail());
        usuarioNovo.setSenha(dataGenerator.gerarSenha());
        usuarioNovo.setAdministrador("false");

        String novoIdGerado = given()
                .body(usuarioNovo)
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

        given()
                .when()
                .get("/usuarios/{_id}", novoIdGerado)
                .then()
                .statusCode(200)
                .body("_id", equalTo(novoIdGerado))
                .body("nome", equalTo(usuarioNovo.getNome()))
                .body("email", equalTo(usuarioNovo.getEmail()))
                .body("password", equalTo(usuarioNovo.getSenha()))
                .body("administrador", equalTo(usuarioNovo.getAdministrador()))
                ;

    }
}
