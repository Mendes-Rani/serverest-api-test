package br.com.ranieri.login.funcional;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.dto.LoginRequest;
import br.com.ranieri.dto.Usuario;
import br.com.ranieri.services.LoginService;
import br.com.ranieri.services.UsuarioService;
import io.restassured.response.Response;
import org.junit.Test;

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

        LoginService loginService = new LoginService();
        Response response = loginService.realizarLogin(loginRequest);

        String tokenAdmin = response
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

        LoginService loginService = new LoginService();
        Response response = loginService.realizarLogin(loginRequest);

        String tokenUser = response
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
