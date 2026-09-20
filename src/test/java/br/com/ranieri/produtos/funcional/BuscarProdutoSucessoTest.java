package br.com.ranieri.produtos.funcional;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.services.ProdutoService;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class BuscarProdutoSucessoTest extends BaseTest {
    @Test
    public void deveBuscarTodosOsProdutosComSucesso(){
        ProdutoService produtoService = new ProdutoService();
        Response produtoResponse = produtoService.listarProdutos();

        produtoResponse
                .then()
                .statusCode(200)
                .body("quantidade", notNullValue())
                .body("produtos", notNullValue())
                ;

        int quantidade = produtoResponse.path("quantidade");
        int tamanhoLista = produtoResponse.path("produtos.size()");

        assertEquals(quantidade, tamanhoLista);

    }
}
