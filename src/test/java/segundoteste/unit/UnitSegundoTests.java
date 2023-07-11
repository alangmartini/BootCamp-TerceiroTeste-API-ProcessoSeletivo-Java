package segundoteste.unit;

import org.junit.jupiter.api.Nested;
import segundoteste.Segundo;
import segundoteste.candidatos.Candidato;
import segundoteste.errors.CandidatoDuplicado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import segundoteste.errors.CandidatoNaoEncontrado;
import segundoteste.errors.NomeInvalido;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnitSegundoTests {
    @Test
    void contextLoads() {
    }

    @Nested
    class testIniciarProcesso {
        @Test
        public void succesfullyCreates() {
            Segundo segundo = new Segundo();
            int codCandidato = segundo.iniciarProcesso("John");

            Candidato candidato = segundo.encontrarCandidateEmFases(codCandidato);
            assertEquals(candidato.getFaseAtual(), "Recebidos");
        }

        @Test
        public void throwsErrorOnRepeatedCandidate() throws CandidatoDuplicado {
            Segundo segundo = new Segundo();
            int codCandidato = segundo.iniciarProcesso("John");

            assertNotNull(codCandidato);
            assertThrows(CandidatoDuplicado.class, () -> segundo.iniciarProcesso("John"));
        }

        @Test
        public void throwsErrorOnEmptyCandidateName() {
            Segundo segundo = new Segundo();
            assertThrows(NomeInvalido.class, () -> segundo.iniciarProcesso(""));
        }

        @Test
        public void throwsErrorOnCandidateNameWithInvalidCharacters() {
            Segundo segundo = new Segundo();
            assertThrows(NomeInvalido.class, () -> segundo.iniciarProcesso("   "));
        }

        @Test
        public void throwsErrorOnCandidateNameExceedingMaxLength() {
            Segundo segundo = new Segundo();
            assertThrows(NomeInvalido.class, () -> segundo.iniciarProcesso(
                    "JohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohn"
            ));
        }
    }

    @Nested
    class testMarcarEntrevista {
        @Test
        public void successfullyMovesCandidate() throws CandidatoDuplicado, NomeInvalido, CandidatoNaoEncontrado {
            Segundo segundo = new Segundo();
            int codCandidato = segundo.iniciarProcesso("John");

            Candidato candidato = segundo.encontrarCandidateEmFases(codCandidato);
            assertEquals(candidato.getFaseAtual(), "Recebidos");

            segundo.marcarEntrevista(codCandidato);
            Candidato candidatoEntrevista = segundo.encontrarCandidateEmFases(codCandidato);
            assertEquals("Qualificados", candidatoEntrevista.getFaseAtual());
        }

        @Test
        public void throwsErrorOnNonExistentCandidate() {
            Segundo segundo = new Segundo();
            assertThrows(CandidatoNaoEncontrado.class, () -> segundo.marcarEntrevista(999));
        }
    }


    @Nested
    class testAprovarCandidato {
        @Test
        public void successfullyMovesCandidate() throws CandidatoDuplicado, NomeInvalido, CandidatoNaoEncontrado {
            Segundo segundo = new Segundo();
            int codCandidato = segundo.iniciarProcesso("John");

            Candidato candidato = segundo.encontrarCandidateEmFases(codCandidato);
            assertEquals(candidato.getFaseAtual(), "Recebidos");

            segundo.marcarEntrevista(codCandidato);
            Candidato candidatoEntrevista = segundo.encontrarCandidateEmFases(codCandidato);
            assertEquals(candidatoEntrevista.getFaseAtual(), "Qualificados");

            segundo.aprovarCandidato(codCandidato);
            Candidato candidatoAprovado = segundo.encontrarCandidateEmFases(codCandidato);
            assertEquals(candidatoAprovado.getFaseAtual(), "Aprovados");
        }

        @Test
        public void throwsErrorOnNonExistentCandidate() {
            Segundo segundo = new Segundo();
            assertThrows(CandidatoNaoEncontrado.class, () -> segundo.aprovarCandidato(999));
        }

        @Nested
        class testDesqualificarCandidato {
            @Test
            public void successfullyDisqualifiesCandidate() throws CandidatoDuplicado, NomeInvalido, CandidatoNaoEncontrado {
                Segundo segundo = new Segundo();
                int codCandidato = segundo.iniciarProcesso("John");

                Candidato candidato = segundo.encontrarCandidateEmFases(codCandidato);
                assertEquals(candidato.getFaseAtual(), "Recebidos");

                segundo.marcarEntrevista(codCandidato);
                Candidato candidatoEntrevista = segundo.encontrarCandidateEmFases(codCandidato);
                assertEquals(candidatoEntrevista.getFaseAtual(), "Qualificados");

                segundo.desqualificarCandidato(codCandidato);
                assertThrows(CandidatoNaoEncontrado.class, () -> segundo.encontrarCandidateEmFases(codCandidato));
            }

            @Test
            public void throwsErrorOnNonExistentCandidate() {
                Segundo segundo = new Segundo();
                assertThrows(CandidatoNaoEncontrado.class, () -> segundo.desqualificarCandidato(999));
            }
        }
    }

    @Nested
    class testEncontrarCandidateEmFases {
        @Test
        public void successfullyFindsCandidate() throws CandidatoDuplicado, NomeInvalido, CandidatoNaoEncontrado {
            Segundo segundo = new Segundo();
            int codCandidato = segundo.iniciarProcesso("John");

            Candidato candidato = segundo.encontrarCandidateEmFases(codCandidato);
            assertEquals(candidato.getFaseAtual(), "Recebidos");

            segundo.marcarEntrevista(codCandidato);
            Candidato candidatoEntrevista = segundo.encontrarCandidateEmFases(codCandidato);
            assertEquals(candidatoEntrevista.getFaseAtual(), "Qualificados");

            segundo.aprovarCandidato(codCandidato);
            Candidato candidatoAprovado = segundo.encontrarCandidateEmFases(codCandidato);
            assertEquals(candidatoAprovado.getFaseAtual(), "Aprovados");
        }

        @Test
        public void throwsErrorOnNonExistentCandidate() {
            Segundo segundo = new Segundo();
            assertThrows(CandidatoNaoEncontrado.class, () -> segundo.encontrarCandidateEmFases(999));
        }
    }

	@Nested
	class testEncontrarCandidateEmFasesPorNome {
		@Test
		public void successfullyFindsCandidate() throws CandidatoDuplicado, NomeInvalido, CandidatoNaoEncontrado {
			Segundo segundo = new Segundo();
			String nome = "John";
			int codCandidato = segundo.iniciarProcesso(nome);

			Candidato candidato = segundo.encontrarCandidatoPorNome(nome);
			assertEquals(candidato.getFaseAtual(), "Recebidos");

			segundo.marcarEntrevista(codCandidato);
			Candidato candidatoEntrevista =
				segundo.encontrarCandidatoPorNome(nome);
			assertEquals(candidatoEntrevista.getFaseAtual(), "Qualificados");

			segundo.aprovarCandidato(codCandidato);
			Candidato candidatoAprovado =
				segundo.encontrarCandidatoPorNome(nome);
			assertEquals(candidatoAprovado.getFaseAtual(), "Aprovados");
		}

		@Test
		public void throwsErrorOnNonExistentCandidate() {
			Segundo segundo = new Segundo();
			assertNull(segundo.encontrarCandidatoPorNome("John"));
		}
	}

    @Nested
    class testVerificarStatusCandidato {
        @Test
        public void successfullyReturnsCandidateStatus() throws CandidatoDuplicado, NomeInvalido, CandidatoNaoEncontrado {
            Segundo segundo = new Segundo();
            int codCandidato = segundo.iniciarProcesso("John");

            String status = segundo.verificarStatusCandidato(codCandidato);
            assertEquals(status, "Recebido");

            segundo.marcarEntrevista(codCandidato);
            status = segundo.verificarStatusCandidato(codCandidato);
            assertEquals(status, "Qualificado");

            segundo.aprovarCandidato(codCandidato);
            status = segundo.verificarStatusCandidato(codCandidato);
            assertEquals(status, "Aprovado");
        }

		@Test
	    public void succesfullyHandlesSchar() throws CandidatoDuplicado,
		    NomeInvalido,
		    CandidatoNaoEncontrado {
		    Segundo segundo = new Segundo();
		    int codCandidato = segundo.iniciarProcesso("John");
			Candidato candidato =
				segundo.encontrarCandidateEmFases(codCandidato);

			candidato.setFaseAtual("Recebidos");
		    String status = segundo.verificarStatusCandidato(codCandidato);
		    assertEquals(status, "Recebido");

		    candidato.setFaseAtual("Recebido");
		    status = segundo.verificarStatusCandidato(codCandidato);
		    assertEquals(status, "Recebido");
	    }

        @Test
        public void throwsErrorOnNonExistentCandidate() {
            Segundo segundo = new Segundo();
            assertThrows(CandidatoNaoEncontrado.class, () -> segundo.verificarStatusCandidato(999));
        }
    }

    @Nested
    class testObterAprovados {
        @Test
        public void successfullyReturnsApprovedCandidates() throws CandidatoDuplicado, NomeInvalido, CandidatoNaoEncontrado {
            Segundo segundo = new Segundo();
            int codCandidato1 = segundo.iniciarProcesso("John");
            int codCandidato2 = segundo.iniciarProcesso("Doe");

            segundo.marcarEntrevista(codCandidato1);
            segundo.marcarEntrevista(codCandidato2);

            segundo.aprovarCandidato(codCandidato1);
            segundo.aprovarCandidato(codCandidato2);

            List<String> aprovados = segundo.obterAprovados();
            assertTrue(aprovados.contains("John"));
            assertTrue(aprovados.contains("Doe"));
            assertEquals(aprovados.size(), 2);
        }

        @Test
        public void returnsEmptyListWhenNoApprovedCandidates() {
            Segundo segundo = new Segundo();
            List<String> aprovados = segundo.obterAprovados();
            assertTrue(aprovados.isEmpty());
        }
    }

}
