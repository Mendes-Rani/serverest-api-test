package br.com.ranieri.login.negativo;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.dto.LoginRequest;
import br.com.ranieri.dto.Usuario;
import br.com.ranieri.services.UsuarioService;
import br.com.ranieri.utils.DataGenerator;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class LoginErroTest extends BaseTest {
    @Test
    public void naoDeveFazerLoginComEmailInvalido(){
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.criarUsuario();

        DataGenerator dataGenerator = new DataGenerator();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(dataGenerator.gerarEmail());
        loginRequest.setSenha(usuario.getSenha());

        given()
                .body(loginRequest)
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .statusCode(401)
                .body("message", is("Email e/ou senha inválidos"))
                ;

    }

    @Test
    public void naoDeveFazerLoginComSenhaInvalida(){
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.criarUsuario();

        String senhaInvalida = "SenhaInvalida123";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(usuario.getEmail());
        loginRequest.setSenha(senhaInvalida);

        given()
                .body(loginRequest)
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .statusCode(401)
                .body("message", is("Email e/ou senha inválidos"))
                ;

    }

    @Test
    public void naoDeveFazerLoginComEmailESenhaInvalidos(){
        DataGenerator dataGenerator = new DataGenerator();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(dataGenerator.gerarEmail());
        loginRequest.setSenha(dataGenerator.gerarSenha());

        given()
                .body(loginRequest)
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .statusCode(401)
                .body("message", is("Email e/ou senha inválidos"))
                ;
    }
}
