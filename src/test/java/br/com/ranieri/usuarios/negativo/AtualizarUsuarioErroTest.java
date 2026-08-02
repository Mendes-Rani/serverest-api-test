package br.com.ranieri.usuarios.negativo;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.dto.Usuario;
import br.com.ranieri.services.UsuarioService;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class AtualizarUsuarioErroTest extends BaseTest {
    @Test
    public void naoDeveAtualizarUsuarioComEmailJaCadastrado(){

        UsuarioService usuarioService = new UsuarioService();
        Usuario primeiroUsuario = usuarioService.criarUsuario();
        Usuario segundoUsuario = usuarioService.criarUsuario();

        Usuario dadosAtualizacaoPrimeiroUsuario = new Usuario();

        dadosAtualizacaoPrimeiroUsuario.setNome(primeiroUsuario.getNome());
        dadosAtualizacaoPrimeiroUsuario.setEmail(segundoUsuario.getEmail());
        dadosAtualizacaoPrimeiroUsuario.setSenha(primeiroUsuario.getSenha());
        dadosAtualizacaoPrimeiroUsuario.setAdministrador(primeiroUsuario.getAdministrador());

        given()
                .body(dadosAtualizacaoPrimeiroUsuario)
                .contentType(ContentType.JSON)
                .when()
                .put("/usuarios/{_id}", primeiroUsuario.getId())
                .then()
                .statusCode(400)
                .body("message", is("Este email já está sendo usado"))
                ;

    }
}
