package segundoteste.errors;

import java.util.NoSuchElementException;

public class CandidatoNaoEncontrado extends NoSuchElementException {
    public CandidatoNaoEncontrado() {
        super("Candidato não encontrado");
    }
}
