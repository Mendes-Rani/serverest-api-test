package br.com.ranieri.services;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class CarrinhoService {
    public Response listarCarrinhos(){
        Response response = given()
                .when()
                .get("/carrinhos")
                ;

        return response;
    }

    public Response listarCarrinhos(String nomeFiltro, Object valorFiltro){
        Response response = given()
                .queryParam(nomeFiltro , valorFiltro)
                .when()
                .get("/carrinhos")
                ;
        return response;
    }

}
