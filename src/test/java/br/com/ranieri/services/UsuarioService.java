package br.com.ranieri.services;

import br.com.ranieri.dto.Usuario;
import br.com.ranieri.utils.DataGenerator;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class UsuarioService {
    private DataGenerator dataGenerator = new DataGenerator();

    public Usuario criarUsuario(boolean administrador){
        String nome = dataGenerator.gerarNome();
        String email = dataGenerator.gerarEmail();
        String senha = dataGenerator.gerarSenha();

        String tipoAdministrador = String.valueOf(administrador);

        Usuario usuario = new Usuario();

        String payload = "{\n" +
                "  \"nome\": \"" + nome + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + senha + "\",\n" +
                "  \"administrador\": \"" + tipoAdministrador + "\"\n" +
                "}";

        String id = given()
                .body(payload)
                .contentType(ContentType.JSON)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("message", is("Cadastro realizado com sucesso"))
                .body("_id", is(notNullValue()))
                .extract()
                .path("_id")
                ;

        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setAdministrador(tipoAdministrador);

        return usuario;

    }

    public Usuario criarUsuario(){
        return criarUsuario(false);
    }

    public Usuario criarUsuarioAdmin(){
        return criarUsuario(true);
    }

}
