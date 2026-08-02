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

        Usuario usuario = new Usuario();

        usuario.setNome(dataGenerator.gerarNome());
        usuario.setEmail(dataGenerator.gerarEmail());
        usuario.setSenha(dataGenerator.gerarSenha());
        usuario.setAdministrador(String.valueOf(administrador));


        String id = given()
                .body(usuario)
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

        return usuario;

    }

    public Usuario criarUsuario(){
        return criarUsuario(false);
    }

    public Usuario criarUsuarioAdmin(){
        return criarUsuario(true);
    }

}
