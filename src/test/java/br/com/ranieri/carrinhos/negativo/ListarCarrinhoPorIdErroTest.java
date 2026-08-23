package br.com.ranieri.carrinhos.negativo;

import br.com.ranieri.base.BaseTest;
import br.com.ranieri.services.CarrinhoService;
import br.com.ranieri.utils.DataGenerator;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class ListarCarrinhoPorIdErroTest extends BaseTest {
    @Test
    public void naoDeveRetornarCarrinhoPorIdInexistenteOuFormatoInvalido() {
        DataGenerator dataGenerator = new DataGenerator();
        String idAleatorio = dataGenerator.gerarIdAleatorio();
        String[] idsInvalidos = {
                idAleatorio,
                "123456789012345",
                "12345678901234567"
        };

        CarrinhoService carrinhoService = new CarrinhoService();

        for (String id : idsInvalidos) {
            Response response = carrinhoService.listarCarrinhosId(id);

            response
                    .then()
                    .statusCode(400)
            ;
            if (id.equals(idAleatorio)) {
                response
                        .then()
                        .body("message", is("Carrinho não encontrado"))
                        .log().all()
                ;

            } else if (id.equals("123456789012345") || id.equals("12345678901234567")) {
                response
                        .then()
                        .body("id", is("id deve ter exatamente 16 caracteres alfanuméricos"))
                        .log().all()
                ;
            }
        }
    }
}
