package segundoteste;

import java.util.List;

/**
 * IProcessManager é uma interface que define os métodos que devem ser implementados pelo gerenciador do processo de seleção.
 */
public interface IProcessManager {

    /**
     * Este método deve iniciar o processo para um determinado candidato, dado seu nome.
     *
     * @param nome O nome do candidato.
     * @return O código do candidato.
     */
    public int iniciarProcesso(String nome);

    /**
     * Este método deve marcar uma entrevista para um determinado candidato, dado seu código.
     *
     * @param codCandidato O código do candidato.
     */
    public void marcarEntrevista(int codCandidato);

    /**
     * Este método deve desqualificar um determinado candidato do processo, dado seu código.
     *
     * @param codCandidato O código do candidato.
     */
    public void desqualificarCandidato(int codCandidato);

    /**
     * Este método deve verificar e retornar o status atual de um determinado candidato, dado seu código.
     *
     * @param codCandidato O código do candidato.
     * @return O status atual do candidato.
     */
    public String verificarStatusCandidato(int codCandidato);

    /**
     * Este método deve aprovar um determinado candidato no processo, dado seu código.
     *
     * @param codCandidato O código do candidato.
     */
    public void aprovarCandidato(int codCandidato);

    /**
     * Este método deve retornar uma lista com os nomes de todos os candidatos aprovados.
     *
     * @return Uma lista com os nomes dos candidatos aprovados.
     */
    public List<String> obterAprovados();

	/**
	 * Este método reseta o ProcessManager.
	 */
	public void reset();
}
