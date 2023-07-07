package segundoteste.unit;

import org.junit.jupiter.api.*;
import segundoteste.candidatos.Candidato;
import segundoteste.errors.CandidatoDuplicado;
import segundoteste.errors.CandidatoNaoEncontrado;
import segundoteste.fases.AbsFase;
import segundoteste.fases.Aprovados;

import static org.junit.jupiter.api.Assertions.*;

public class UnitAbsFaseTests {

    @Nested
    class TestAbsFase {
        @Test
        public void successfullyAddCandidate() throws CandidatoDuplicado {
            AbsFase fase = new Aprovados();
            Candidato candidato = new Candidato("John", 1);

            fase.addCandidato(candidato);

            assertEquals(fase.getCandidato(1), candidato);
        }

        @Test
        public void throwErrorOnAddingRepeatedCandidate() throws CandidatoDuplicado {
            AbsFase fase = new Aprovados();
            Candidato candidato = new Candidato("John", 1);

            fase.addCandidato(candidato);

            assertThrows(CandidatoDuplicado.class, () -> fase.addCandidato(candidato));
        }

        @Test
        public void successfullyRemoveCandidate() throws CandidatoDuplicado, CandidatoNaoEncontrado {
            AbsFase fase = new Aprovados();
            Candidato candidato = new Candidato("John", 1);

            fase.addCandidato(candidato);
            fase.removeCandidato(1);

            assertThrows(CandidatoNaoEncontrado.class, () -> fase.getCandidato(1));
        }

        @Test
        public void throwErrorOnRemovingNonexistentCandidate() {
            AbsFase fase = new Aprovados();

            assertThrows(CandidatoNaoEncontrado.class, () -> fase.removeCandidato(1));
        }
    }

}
