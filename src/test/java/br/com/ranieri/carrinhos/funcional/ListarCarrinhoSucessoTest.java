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

    @Test
    public void deveFiltrarPorIDComSucesso(){

        CarrinhoService carrinhoService = new CarrinhoService();
        Response response = carrinhoService.listarCarrinhos();

        String id = response.path("carrinhos[0]._id");
        Response consultaId = carrinhoService.listarCarrinhos("_id", id);

        consultaId
                .then()
                .statusCode(200)
                .body("quantidade", is(1))
                .body("carrinhos", notNullValue())
                .body("carrinhos[0]._id", is(id))
                ;

        int quantidade = consultaId.path("quantidade");
        int tamanhoLista = consultaId.path("carrinhos.size()");
        assertEquals(quantidade, tamanhoLista);

    }

    @Test
    public void deveFiltrarIDUsuarioComSucesso(){
        CarrinhoService carrinhoService = new CarrinhoService();
        Response response = carrinhoService.listarCarrinhos();

        String idUsuario = response.path("carrinhos[0].idUsuario");

        Response consultaIDUsuario = carrinhoService.listarCarrinhos("idUsuario", idUsuario);

        consultaIDUsuario
                .then()
                .statusCode(200)
                .body("quantidade", is(1))
                .body("carrinhos", notNullValue())
                .body("carrinhos[0].idUsuario", is(idUsuario))
                ;

        int quantidade = consultaIDUsuario.path("quantidade");
        int tamanhoLista = consultaIDUsuario.path("carrinhos.size()");
        assertEquals(quantidade, tamanhoLista);

    }

    @Test
    public void deveFiltrarPrecoTotalComSucesso(){
        CarrinhoService carrinhoService = new CarrinhoService();
        Response response = carrinhoService.listarCarrinhos();
        int precoTotal = response.path("carrinhos[0].precoTotal");

        Response consultaPrecoTotal = carrinhoService.listarCarrinhos("precoTotal", precoTotal);

        consultaPrecoTotal
                .then()
                .statusCode(200)
                .body("quantidade", is(1))
                .body("carrinhos", notNullValue())
                .body("carrinhos[0].precoTotal", is(precoTotal))
                ;
        int quantidade = consultaPrecoTotal.path("quantidade");
        int tamanhoLista = consultaPrecoTotal.path("carrinhos.size()");
        assertEquals(quantidade, tamanhoLista);

    }

    @Test
    public void deveFiltrarQuantidadeTotalComSucesso(){
        CarrinhoService carrinhoService = new CarrinhoService();
        Response response = carrinhoService.listarCarrinhos();
        int quantidadeTotal = response.path("carrinhos[0].quantidadeTotal");

        Response consultaQuantidadeTotal = carrinhoService.listarCarrinhos("quantidadeTotal", quantidadeTotal);

        consultaQuantidadeTotal
                .then()
                .statusCode(200)
                .body("quantidade", is(1))
                .body("carrinhos", notNullValue())
                .body("carrinhos[0].quantidadeTotal", is(quantidadeTotal))
                ;

        int quantidade = consultaQuantidadeTotal.path("quantidade");
        int tamanhoLista = consultaQuantidadeTotal.path("carrinhos.size()");
        assertEquals(quantidade, tamanhoLista);

    }

}
