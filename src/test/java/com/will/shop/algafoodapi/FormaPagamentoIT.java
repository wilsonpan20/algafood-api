package com.will.shop.algafoodapi;

import com.will.shop.algafoodapi.domain.model.FormaPagamento;
import com.will.shop.algafoodapi.domain.repository.FormaPagamentoRepository;
import com.will.shop.algafoodapi.util.DatabaseCleaner;

import com.will.shop.algafoodapi.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class FormaPagamentoIT {
	private static final String VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE = "Violação de regra de negocio";

	private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Dados invalidos";

	private static final int FORMA_PAGAMENTO_ID_INEXISTENTE = 100;

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	private FormaPagamento formaPagamentoDinheiro;

	private String jsonFormaPagamentoCorreto;

	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/v1/forma-pagamentos";

		jsonFormaPagamentoCorreto = ResourceUtils.getContentFromResource("/json/correto/forma-pagamento.json");

		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarRestaurantes() {
		given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarFormaPagamento() {
		given().body(jsonFormaPagamentoCorreto)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.post()
				.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarFormaPagamentoExistente() {
		System.out.println(formaPagamentoDinheiro);
		given().pathParam("formaPagamentoId", formaPagamentoDinheiro.getId())
				.accept(ContentType.JSON)
				.when()
				.get("/{formaPagamentoId}")
				.then()
				.statusCode(HttpStatus.OK.value())
				.body("descricao", equalTo(formaPagamentoDinheiro.getDescricao()));
	}

	@Test
	public void deveRetornarStatus404_QuandoConsultarFormaPagamentoInexistente() {
		given().pathParam("formaPagamentoId", FORMA_PAGAMENTO_ID_INEXISTENTE)
				.accept(ContentType.JSON)
				.when()
				.get("/{formaPagamentoId}")
				.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void prepararDados() {
		formaPagamentoDinheiro = new FormaPagamento();
		formaPagamentoDinheiro.setDescricao("Dinheiro");
		formaPagamentoRepository.save(formaPagamentoDinheiro);

		FormaPagamento formaPagamentoCredito = new FormaPagamento();
		formaPagamentoCredito.setDescricao("Credito");
		formaPagamentoRepository.save(formaPagamentoCredito);

		FormaPagamento formaPagamentoDebito = new FormaPagamento();
		formaPagamentoDebito.setDescricao("Debito");
		formaPagamentoRepository.save(formaPagamentoDebito);

	}
}
