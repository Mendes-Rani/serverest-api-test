package br.com.ranieri.usuarios.funcional;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.dto.Usuario;
import br.com.ranieri.services.UsuarioService;
import br.com.ranieri.utils.DataGenerator;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AtualizarUsuarioSucessoTest extends BaseTest {
    @Test
    public void deveAtualizarUsuarioExistenteComSucesso(){
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.criarUsuario();

        DataGenerator dataGenerator = new DataGenerator();
        String novoNome = dataGenerator.gerarNome();
        String novoEmail = dataGenerator.gerarEmail();
        String novaSenha = dataGenerator.gerarSenha();

        Usuario usuarioAtualizado = new Usuario();

        usuarioAtualizado.setNome(novoNome);
        usuarioAtualizado.setEmail(novoEmail);
        usuarioAtualizado.setSenha(novaSenha);
        usuarioAtualizado.setAdministrador("false");

        given()
                .body(usuarioAtualizado)
                .contentType(ContentType.JSON)
                .when()
                .put("/usuarios/{_id}", usuario.getId())
                .then()
                .statusCode(200)
                .body("message", is("Registro alterado com sucesso"))
                ;

        given()
                .when()
                .get("/usuarios/{_id}", usuario.getId())
                .then()
                .statusCode(200)
                .body("_id", equalTo(usuario.getId()))
                .body("nome", equalTo(novoNome))
                .body("email", equalTo(novoEmail))
                .body("password", equalTo(novaSenha))
                .body("administrador", equalTo("false"))
                ;

    }

}
