package br.com.ranieri.services;

import br.com.ranieri.dto.ProdutoRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ProdutoService {
    public Response cadastrarProduto(String token, ProdutoRequest produtoRequest){

        Response response = given()
                .contentType(ContentType.JSON)
                .body(produtoRequest)
                .header("Authorization", token)
                .when()
                .post("/produtos")
                ;
        return response;
    }
}
