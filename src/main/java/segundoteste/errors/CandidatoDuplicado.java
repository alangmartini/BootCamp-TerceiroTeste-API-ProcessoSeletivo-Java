package segundoteste.errors;

/**
 * Error disparado quando o candidato já é participante.
 */
public class CandidatoDuplicado extends RuntimeException {
    public CandidatoDuplicado() {
        super("Candidato já participa do processo.");
    }
}
