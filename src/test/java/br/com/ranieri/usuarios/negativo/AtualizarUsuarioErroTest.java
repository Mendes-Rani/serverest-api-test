package br.com.ranieri.usuarios.negativo;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.dto.Usuario;
import br.com.ranieri.services.UsuarioService;
import br.com.ranieri.utils.DataGenerator;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class AtualizarUsuarioErroTest extends BaseTest {
    @Test
    public void naoDeveAtualizarUsuarioComEmailJaCadastradoTest(){
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.criarUsuario();

        DataGenerator dataGenerator = new DataGenerator();
        String nomeSegundoUsuario = dataGenerator.gerarNome();
        String emailSegundoUsuario = dataGenerator.gerarEmail();
        String senhaSegundoUsuario = dataGenerator.gerarSenha();


        String payloadCriacaoSegundoUsuario = "{\n" +
                "  \"nome\": \"" + nomeSegundoUsuario + "\",\n" +
                "  \"email\": \"" + emailSegundoUsuario + "\",\n" +
                "  \"password\": \"" + senhaSegundoUsuario + "\",\n" +
                "  \"administrador\": \"false\"\n" +
                "}";
        //System.out.println("Payload segundo usuário: " + payloadCriacaoSegundoUsuario);

        given()
                .body(payloadCriacaoSegundoUsuario)
                .contentType(ContentType.JSON)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("message", is("Cadastro realizado com sucesso"))
                .body("_id", is(notNullValue()))
                ;

        String payloadAtualizacaoPrimeiroUsuario = "{\n" +
                "  \"nome\": \"nome teste\",\n" +
                "  \"email\": \"" + emailSegundoUsuario + "\",\n" +
                "  \"password\": \"senhaTeste123\",\n" +
                "  \"administrador\": \"false\"\n" +
                "}";

        given()
                .body(payloadAtualizacaoPrimeiroUsuario)
                .contentType(ContentType.JSON)
                .when()
                .put("/usuarios/{_id}", usuario.getId())
                .then()
                .statusCode(400)
                .body("message", is("Este email já está sendo usado"))
                //.log().all()
                ;

    }
}
