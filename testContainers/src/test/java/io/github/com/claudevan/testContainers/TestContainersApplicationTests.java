package io.github.com.claudevan.testcontainers;


import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ImportAutoConfiguration
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestcontainersApplicationTests {

	@Container
	@ServiceConnection
	private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");
	@LocalServerPort
	private Integer port;

	//Antes de tudo irá inicializar o contêiner PostgreSQL para ser utilizado nos testes.
	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}

	//Após todos os testes irá derrubar o contêiner PostgreSQL.
	@AfterAll
	static void afterAll() {
		postgres.stop();
	}

	//@DynamicPropertySource configura as propriedades do Spring Boot para usar o banco de dados PostgreSQL.
//	@DynamicPropertySource
//	static void configureProperties(DynamicPropertyRegistry registry) {
//		registry.add("spring.datasource.url", postgres::getJdbcUrl);
//		registry.add("spring.datasource.username", postgres::getUsername);
//		registry.add("spring.datasource.password", postgres::getPassword);
//	}

	//Dentro do método setUp(), o RestAssured.baseURI é configurado para apontar para o endereço base da sua aplicação, utilizando a variável port que provavelmente representa a porta em que sua aplicação está sendo executada localmente.
	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}


	@Test
	void connectionEstablished() {
		assertThat(postgres.isCreated()).isTrue();
		assertThat(postgres.isRunning()).isTrue();
	}

	@Test
	void shouldGetCep() {
		given()
				.contentType(ContentType.JSON)
				.when()
				.get("/api/cep/07845010")
				.then()
				.statusCode(200)
				.body("cep", equalTo("07845-010"))
				.body("$", hasKey("logradouro"))
				.body("$", hasKey("bairro"))
				.body("$", hasKey("localidade"));
	}


}
