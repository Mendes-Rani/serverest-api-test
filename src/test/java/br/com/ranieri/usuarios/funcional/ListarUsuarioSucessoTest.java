package br.com.ranieri.usuarios.funcional;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.dto.Usuario;
import br.com.ranieri.services.UsuarioService;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ListarUsuarioSucessoTest extends BaseTest {

    @Test
    public void deveListarUsuariosCadastradosComSucesso(){
        given()
        .when()
                .get("/usuarios")
        .then()
                .statusCode(200)
                .body("quantidade", is(notNullValue()))
                ;
    }

    @Test
    public void deveListarUsuarioEspecificoComSucesso(){

        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.criarUsuario();

        given()
        .when()
                .get("/usuarios/{_id}", usuario.getId())
        .then()
                .assertThat()
                .statusCode(200)
                .body("nome", equalTo(usuario.getNome()))
                .body("email", equalTo(usuario.getEmail()))
                .body("password", equalTo(usuario.getSenha()))
                .body("administrador", equalTo(usuario.getAdministrador()))
                .body("_id", equalTo(usuario.getId()))
                ;

    }

}
