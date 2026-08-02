package br.com.ranieri.usuarios.negativo;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.dto.Usuario;
import br.com.ranieri.services.UsuarioService;
import br.com.ranieri.utils.DataGenerator;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class CadastrarUsuarioNegativoTest extends BaseTest {
    @Test
    public void naoDeveCadastrarUsuarioComEmailJaCadastrado(){

        UsuarioService usuarioService = new UsuarioService();
        Usuario primeiroUsuario = usuarioService.criarUsuario();

        DataGenerator dataGenerator = new DataGenerator();

        Usuario dadosCriacaoSegundoUsuario = new Usuario();
        dadosCriacaoSegundoUsuario.setNome(dataGenerator.gerarNome());
        dadosCriacaoSegundoUsuario.setEmail(primeiroUsuario.getEmail());
        dadosCriacaoSegundoUsuario.setSenha(dataGenerator.gerarSenha());
        dadosCriacaoSegundoUsuario.setAdministrador("false");

        given()
                .body(dadosCriacaoSegundoUsuario)
                .contentType(ContentType.JSON)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(400)
                .body("message", is("Este email já está sendo usado"))
                ;

    }
}
