package segundoteste.candidatos;

/**
 * AbsCandidato é uma classe abstrata que representa um candidato no sistema.
 * Cada candidato tem um código único, um nome e uma fase atual em que se encontra.
 */
public abstract class AbsCandidato {
    private String faseAtual = "Recebido";
    private int codCandidato;
    private String nome;

    /**
     * Este método retorna a fase atual do candidato.
     *
     * @return A fase atual do candidato como uma string.
     */
    public String getFaseAtual() {
        return faseAtual;
    }

    /**
     * Este método define a fase atual do candidato.
     *
     * @param faseAtual Uma string que representa a nova fase atual do candidato.
     */
    public void setFaseAtual(String faseAtual) {
        this.faseAtual = faseAtual;
    }

    /**
     * Este método retorna o código do candidato.
     *
     * @return O código do candidato como um inteiro.
     */
    public int getCodCandidato() {
        return codCandidato;
    }

    /**
     * Este método define o código do candidato.
     *
     * @param codCandidato Um inteiro que representa o novo código do candidato.
     */
    public void setCodCandidato(int codCandidato) {
        this.codCandidato = codCandidato;
    }

    /**
     * Este método retorna o nome do candidato.
     *
     * @return O nome do candidato como uma string.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Este método define o nome do candidato.
     *
     * @param nome Uma string que representa o novo nome do candidato.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
