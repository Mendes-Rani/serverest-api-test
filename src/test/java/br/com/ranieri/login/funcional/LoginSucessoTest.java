package br.com.ranieri.login.funcional;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.dto.LoginRequest;
import br.com.ranieri.dto.Usuario;
import br.com.ranieri.services.UsuarioService;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

public class LoginSucessoTest extends BaseTest {
    @Test
    public void deveRealizarLoginAdminComSucesso(){
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.criarUsuarioAdmin();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(usuario.getEmail());
        loginRequest.setSenha(usuario.getSenha());

        String tokenAdmin = given()
                .body(loginRequest)
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .body("message", is("Login realizado com sucesso"))
                .body("authorization", notNullValue())
                .body("authorization", not(emptyString()))
                .extract()
                .path("authorization")
                ;
    }

    @Test
    public void deveRealizarLoginUsuarioComumComSucesso(){
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.criarUsuario();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(usuario.getEmail());
        loginRequest.setSenha(usuario.getSenha());

        String tokenUser = given()
                .body(loginRequest)
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .body("message", is("Login realizado com sucesso"))
                .body("authorization", notNullValue())
                .body("authorization", not(emptyString()))
                .extract()
                .path("authorization")
                ;

    }
}
