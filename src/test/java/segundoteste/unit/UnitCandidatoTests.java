package segundoteste.unit;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import segundoteste.candidatos.Candidato;
import segundoteste.fases.Recebidos;

class AbsCandidatoTests {

    @Nested
    class testCandidatoCreation {

        @Test
        public void succesfullyCreates() {
            Candidato candidato = new Candidato("John", 123);
            assertNotNull(candidato);
            assertEquals(candidato.getNome(), "John");
            assertEquals(candidato.getCodCandidato(), 123);
        }
    }

    @Nested
    class testFaseAtual {
        @Test
        public void initiallyNull() {
            Candidato candidato = new Candidato("John", 123);
            assertNull(candidato.getFaseAtual());
        }

        @Test
        public void successfullySetsPhase() {
            Candidato candidato = new Candidato("John", 123);
            candidato.setFaseAtual(Recebidos.class.getName());
            assertEquals(candidato.getFaseAtual(), Recebidos.class.getName());
        }
    }
}
