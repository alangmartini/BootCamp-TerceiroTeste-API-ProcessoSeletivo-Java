package segundoteste.errors;

import java.util.NoSuchElementException;

/**
 * Error disparado quando o candidato não é encontrado na fase procurada.
 */
public class CandidatoNaoEncontrado extends NoSuchElementException {
    public CandidatoNaoEncontrado() {
        super("Candidato não encontrado");
    }
}
