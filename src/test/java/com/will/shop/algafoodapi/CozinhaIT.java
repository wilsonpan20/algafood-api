package com.will.shop.algafoodapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;

import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import com.will.shop.algafoodapi.domain.repository.CozinhaRepository;
import com.will.shop.algafoodapi.domain.repository.RestauranteRepository;
import com.will.shop.algafoodapi.util.DatabaseCleaner;
import com.will.shop.algafoodapi.util.ResourceUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CozinhaIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    private Cozinha cozinhaBrasileira;
    private Cozinha cozinhaTailandesa;
    private Cozinha cozinhaMexicana;
    private Cozinha cozinhaIndiana;

    private String jsonCozinhaCorreta;
    private String jsonCozinhaIncorreta;
    private final int ID_COZINHA_INEXISTENTE = 100;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/v1/cozinhas";

        jsonCozinhaCorreta = ResourceUtils.getContentFromResource(
                "/json/correto/cozinha-chinesa.json");

        jsonCozinhaIncorreta = ResourceUtils.getContentFromResource(
                "/json/incorreto/cozinha-sem-nome.json");

        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarCozinha() {
        given()
                .body(jsonCozinhaCorreta)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarStatus400_QuandoCadastrarCozinhaSemNome() {
        given()
                .body(jsonCozinhaIncorreta)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo("Dados invalidos"));
    }

    @Test
    public void deveRetornarRespostaEstatusCorretos_QuandoConsultarCozinhaExistente() {
        given()
                .pathParam("cozinhaId", cozinhaBrasileira.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(cozinhaBrasileira.getNome()));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
        given()
                .pathParam("cozinhaId", ID_COZINHA_INEXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados() {
        cozinhaTailandesa = new Cozinha();
        cozinhaTailandesa.setNome("Tailandesa");
        cozinhaRepository.save(cozinhaTailandesa);

        cozinhaBrasileira = new Cozinha();
        cozinhaBrasileira.setNome("Brasileira");
        cozinhaRepository.save(cozinhaBrasileira);

        cozinhaMexicana = new Cozinha();
        cozinhaMexicana.setNome("Mexicana");
        cozinhaRepository.save(cozinhaMexicana);

        cozinhaIndiana = new Cozinha();
        cozinhaIndiana.setNome("Indiana");
        cozinhaRepository.save(cozinhaIndiana);
    }
}
