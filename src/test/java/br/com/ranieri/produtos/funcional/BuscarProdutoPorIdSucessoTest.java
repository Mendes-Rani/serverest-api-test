package br.com.ranieri.produtos.funcional;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.services.ProdutoService;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class BuscarProdutoPorIdSucessoTest extends BaseTest {
    @Test
    public void deveBuscarProdutoPorIdComSucesso(){
        ProdutoService produtoService = new ProdutoService();
        Response produtoResponse = produtoService.listarProdutos();
        String produtoId = produtoResponse.path("produtos[0]._id");

        Response buscaId = produtoService.listarProdutoPorId(produtoId);

        buscaId
                .then()
                .statusCode(200)
                .body("nome", notNullValue())
                .body("preco",  notNullValue())
                .body("descricao", notNullValue())
                .body("quantidade", notNullValue())
                .body("_id", equalTo(produtoId))
                ;

    }
}
