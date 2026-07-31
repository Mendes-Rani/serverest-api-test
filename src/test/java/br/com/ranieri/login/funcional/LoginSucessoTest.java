package br.com.ranieri.login.funcional;

import br.com.ranieri.base.BaseTest;
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

        String dadosLogin = "{\n" +
                "  \"email\": \"" + usuario.getEmail() + "\",\n" +
                "  \"password\": \"" + usuario.getSenha() + "\"\n" +
                "}";

        //System.out.println("Dados de login: " + dadosLogin);

        String tokenAdmin = given()
                .body(dadosLogin)
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .body("message", is("Login realizado com sucesso"))
                .body("authorization", is(notNullValue()))
                .body("authorization", not(emptyString()))
                .extract()
                .path("authorization")
                //.log().all()
                ;
    }

    @Test
    public void deveRealizarLoginUsuarioComumComSucesso(){
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.criarUsuario();

        String dadosLogin = "{\n" +
                "  \"email\": \""+ usuario.getEmail() + "\",\n" +
                "  \"password\": \"" + usuario.getSenha() + "\"\n" +
                "}";

        String tokenUser = given()
                .body(dadosLogin)
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .body("message", is("Login realizado com sucesso"))
                .body("authorization", is(notNullValue()))
                .body("authorization", not(emptyString()))
                //.log().all()
                .extract()
                .path("authorization")
                ;

    }
}
