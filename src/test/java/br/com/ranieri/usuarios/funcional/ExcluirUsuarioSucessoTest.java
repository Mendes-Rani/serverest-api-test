package br.com.ranieri.usuarios.funcional;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.dto.Usuario;
import br.com.ranieri.services.UsuarioService;
import br.com.ranieri.utils.DataGenerator;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class ExcluirUsuarioSucessoTest extends BaseTest {
    @Test
    public void deveExcluirUsuarioExistenteComSucessoTest(){
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.criarUsuario();

        given()
                .when()
                .delete("/usuarios/{_id}", usuario.getId())
                .then()
                .statusCode(200)
                .body("message", is("Registro excluído com sucesso"))
                ;

        given()
                .when()
                .get("/usuarios/{_id}", usuario.getId())
                .then()
                .statusCode(400)
                .body("message", is("Usuário não encontrado"))
                ;
    }

    @Test
    public void naoDeveExcluirUsuarioInexistenteTest() {
        DataGenerator dataGenerator = new DataGenerator();
        String idInexistente = dataGenerator.gerarIdAleatorio();

        given()
                .when()
                .delete("/usuarios/{_id}", idInexistente)
                .then()
                .statusCode(200)
                .body("message", is("Nenhum registro excluído"))
                ;

    }


}
