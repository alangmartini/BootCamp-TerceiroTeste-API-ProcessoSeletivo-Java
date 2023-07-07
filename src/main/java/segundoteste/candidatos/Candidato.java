package segundoteste.candidatos;

/**
 * Candidato é uma classe que estende AbsCandidato.
 * Esta classe representa um candidato específico no sistema.
 */
public class Candidato extends AbsCandidato {

    /**
     * Construtor para a classe Candidato.
     * Este construtor inicializa um novo Candidato com um nome e um código especificados.
     *
     * @param nome O nome do candidato como uma String.
     * @param codCandidato O código do candidato como um int.
     */
    public Candidato(
            String nome,
            int codCandidato
    ) {
        this.setNome(nome);
        this.setCodCandidato(codCandidato);
    }
}
