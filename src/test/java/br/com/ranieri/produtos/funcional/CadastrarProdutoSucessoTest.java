package br.com.ranieri.produtos.funcional;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.dto.LoginRequest;
import br.com.ranieri.dto.ProdutoRequest;
import br.com.ranieri.dto.Usuario;
import br.com.ranieri.services.LoginService;
import br.com.ranieri.services.ProdutoService;
import br.com.ranieri.services.UsuarioService;
import br.com.ranieri.utils.DataGenerator;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

public class CadastrarProdutoSucessoTest extends BaseTest {
    @Test
    public void deveCadastrarProdutoComSucesso(){
        DataGenerator dataGenerator = new DataGenerator();

        ProdutoRequest produtoRequest = new ProdutoRequest();
        produtoRequest.setNome(dataGenerator.gerarNomeProduto());
        produtoRequest.setPreco(150.00);
        produtoRequest.setDescricao("Teste Vendas");
        produtoRequest.setQuantidade(10);

        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.criarUsuarioAdmin();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(usuario.getEmail());
        loginRequest.setSenha(usuario.getSenha());

        LoginService loginService = new LoginService();
        Response loginResponse = loginService.realizarLogin(loginRequest);

        String tokenAdmin = loginResponse
                .then()
                .statusCode(200)
                .extract()
                .path("authorization")
                ;

        ProdutoService produtoService = new ProdutoService();
        Response cadastroResponse = produtoService.cadastrarProduto(tokenAdmin, produtoRequest);

        cadastroResponse
                .then()
                .statusCode(201)
                .body("message", is("Cadastro realizado com sucesso"))
                .body("_id", notNullValue())
                .body("_id", not(emptyString()))
                ;

    }
}
