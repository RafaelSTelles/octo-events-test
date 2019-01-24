package br.com.rafaelstelles;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OctoEventsApplication.class)
@TestPropertySource("classpath:application-it.properties")
@ActiveProfiles("it")
@Transactional
@AutoConfigureMockMvc
public class AbstractIntegrationTest {

	private static final int PORT = 5433;
	private static final String DATABASE = "octo_events_it";
	private static EmbeddedPostgres embeddedPostgres;

	@BeforeClass
	public static void initialise() throws Exception {
		if (embeddedPostgres != null) {
			return;
		}
		embeddedPostgres = EmbeddedPostgres.builder()
				.setPort(PORT).start();

		try (Connection conn = embeddedPostgres.getPostgresDatabase().getConnection()) {
			try (Statement statement = conn.createStatement()) {
				statement.execute("create database " + DATABASE);
			}
		}
	}

	@Test
	public void test() {
	}

}
