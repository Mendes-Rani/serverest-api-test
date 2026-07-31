package br.com.ranieri.login.negativo;

import br.com.ranieri.base.BaseTest;
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
        DataGenerator dataGenerator = new DataGenerator();
        String emailInvalido = dataGenerator.gerarEmail();
        String senhaInvalida = dataGenerator.gerarSenha();

        String dadosLogin = "{\n" +
                "  \"email\": \"" + emailInvalido + "\",\n" +
                "  \"password\": \"" + senhaInvalida + "\"\n" +
                "}";

        given()
                .body(dadosLogin)
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .statusCode(401)
                .body("message", is("Email e/ou senha inválidos"))
                //.log().all()
                ;

    }

    @Test
    public void naoDeveFazerLoginComSenhaInvalida(){
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.criarUsuario();

        DataGenerator dataGenerator = new DataGenerator();
        String senhaInvalida = dataGenerator.gerarSenha();

        String dadosLogin = "{\n" +
                "  \"email\": \"" + usuario.getEmail() + "\",\n" +
                "  \"password\": \"" + senhaInvalida + "\"\n" +
                "}\n";

        given()
                .body(dadosLogin)
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .statusCode(401)
                .body("message", is("Email e/ou senha inválidos"))
                //.log().all()
                ;



    }

    @Test
    public void naoDeveFazerLoginComEmailESenhaInvalidos(){
        DataGenerator dataGenerator = new DataGenerator();
        String emailInvalido = dataGenerator.gerarEmail();
        String senhaInvalida = dataGenerator.gerarSenha();

        String dadosLogin = "{\n" +
                "  \"email\": \"" + emailInvalido + "\",\n" +
                "  \"password\": \"" + senhaInvalida + "\"\n" +
                "}\n";

        given()
                .body(dadosLogin)
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .statusCode(401)
                .body("message", is("Email e/ou senha inválidos"))
                //.log().all()
                ;

    }
}
