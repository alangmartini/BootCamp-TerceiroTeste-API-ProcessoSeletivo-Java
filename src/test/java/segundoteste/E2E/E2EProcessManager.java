package segundoteste.E2E;

import org.junit.jupiter.api.Test;
import segundoteste.Segundo;
import segundoteste.errors.CandidatoNaoEncontrado;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class E2EProcessManager {

    @Test
    public void fullProcess() throws Exception {
        // Creating an instance of Segundo
        Segundo segundo = new Segundo();

        int johnId = segundo.iniciarProcesso("John");
        int janeId = segundo.iniciarProcesso("Jane");
        int jackId = segundo.iniciarProcesso("Jack");

        segundo.marcarEntrevista(johnId);
        segundo.marcarEntrevista(janeId);

        segundo.aprovarCandidato(johnId);

        segundo.desqualificarCandidato(jackId);

        String johnStatus = segundo.verificarStatusCandidato(johnId);
        assertEquals(johnStatus, "Aprovados");

        String janeStatus = segundo.verificarStatusCandidato(janeId);
        assertEquals(janeStatus, "Qualificados");

        assertThrows(CandidatoNaoEncontrado.class, () -> segundo.verificarStatusCandidato(jackId));

        List<String> aprovados = segundo.obterAprovados();
        assertTrue(aprovados.contains("John"));
        assertFalse(aprovados.contains("Jane"));
        assertFalse(aprovados.contains("Jack"));
    }
}
