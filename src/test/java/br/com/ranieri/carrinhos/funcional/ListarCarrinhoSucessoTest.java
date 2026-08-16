package br.com.ranieri.carrinhos.funcional;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.services.CarrinhoService;
import br.com.ranieri.utils.DataGenerator;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class ListarCarrinhoSucessoTest extends BaseTest {
    @Test
    public void deveListarCarrinhosComSucesso(){

        CarrinhoService carrinhoService = new CarrinhoService();
        Response response = carrinhoService.listarCarrinhos();

        response
                .then()
                .statusCode(200)
                .body("quantidade", notNullValue())
                .body("carrinhos", notNullValue())
                ;

        int quantidade = response.path("quantidade");
        int tamanhoLista = response.path("carrinhos.size()");

        assertEquals(quantidade, tamanhoLista);

    }

    @Test
    public void deveRetornarListaVaziaComSucesso(){
        DataGenerator dataGenerator = new DataGenerator();

        CarrinhoService carrinhoService = new CarrinhoService();
        Response response = carrinhoService.listarCarrinhos("idUsuario", dataGenerator.gerarIdAleatorio());

        response
                .then()
                .statusCode(200)
                .body("quantidade", is(0))
                .body("carrinhos", empty())
                ;

    }

}
