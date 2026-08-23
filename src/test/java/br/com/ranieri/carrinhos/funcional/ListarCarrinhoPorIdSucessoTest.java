package br.com.ranieri.carrinhos.funcional;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.services.CarrinhoService;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ListarCarrinhoPorIdSucessoTest extends BaseTest {
    @Test
    public void deveListarCarrinhoPorIdComSucesso(){
        CarrinhoService carrinhoService = new CarrinhoService();
        Response response = carrinhoService.listarCarrinhos();
        String idCarrinho = response.path("carrinhos[0]._id");

        Response buscaId = carrinhoService.listarCarrinhosId(idCarrinho);

        buscaId
                .then()
                .statusCode(200)
                .body("produtos", notNullValue())
                .body("_id", is(idCarrinho))
                ;
    }
}
