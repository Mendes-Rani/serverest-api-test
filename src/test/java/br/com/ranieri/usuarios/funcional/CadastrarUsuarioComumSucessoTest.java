package br.com.ranieri.usuarios.funcional;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.dto.Usuario;
import br.com.ranieri.services.UsuarioService;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class CadastrarUsuarioComumSucessoTest extends BaseTest {
    @Test
    public void deveCadastrarUsuarioComumComSucesso(){
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.criarUsuario();

        given()
                .when()
                .get("/usuarios/{_id}", usuario.getId())
                .then()
                .statusCode(200)
                .body("_id", equalTo(usuario.getId()))
                .body("nome", equalTo(usuario.getNome()))
                .body("email", equalTo(usuario.getEmail()))
                .body("password", equalTo(usuario.getSenha()))
                .body("administrador", equalTo("false"))
                ;

    }
}
