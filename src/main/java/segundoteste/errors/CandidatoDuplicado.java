package segundoteste.errors;

public class CandidatoDuplicado extends RuntimeException {
    public CandidatoDuplicado() {
        super("Candidato já participa do processo.");
    }
}
