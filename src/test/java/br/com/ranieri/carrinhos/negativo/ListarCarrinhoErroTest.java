package br.com.ranieri.carrinhos.negativo;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.services.CarrinhoService;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class ListarCarrinhoErroTest extends BaseTest {
    @Test
    public void naoDeveFiltrarQuantidadeTotalComValorInvalido(){
        Object [] valoresInvalidos = {"", 0, "abcd"};
        CarrinhoService carrinhoService = new CarrinhoService();

        for(Object valor : valoresInvalidos){
            Response response =
                    carrinhoService.listarCarrinhos("quantidadeTotal", valor);

            response
                    .then()
                    .statusCode(400)
                    ;
            if(valor.equals("") || valor.equals(0)){
                response
                        .then()
                        .body("quantidadeTotal", is("quantidadeTotal deve ser um número positivo"))
                        ;

            } else{
                response
                        .then()
                        .body("quantidadeTotal", is("quantidadeTotal deve ser um número"))
                        ;
            }
        }
    }

    @Test
    public void naoDeveFiltrarPrecoTotalComValorInvalido(){
       Object [] valoresInvalidos = {"", 0, "abcd"};
       CarrinhoService carrinhoService = new CarrinhoService();

       for(Object valor : valoresInvalidos){
           Response response = carrinhoService.listarCarrinhos("precoTotal", valor);

           response
                   .then()
                   .statusCode(400)
                   ;
           if(valor.equals("") || valor.equals(0)){
               response
                       .then()
                       .body("precoTotal", is("precoTotal deve ser um número positivo"))
                       ;
           }else{
               response
                       .then()
                       .body("precoTotal", is("precoTotal deve ser um número"))
                       ;
           }
       }
    }
}
