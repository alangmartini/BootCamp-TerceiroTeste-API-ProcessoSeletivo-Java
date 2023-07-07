package segundoteste.fases;

import segundoteste.candidatos.Candidato;

import java.util.List;

/**
 * IFase é uma interface que define os métodos que devem ser implementados pelas fases do processo de seleção de candidatos.
 */
public interface IFase {

    /**
     * Este método deve retornar uma lista de candidatos em uma fase específica.
     *
     * @return Uma lista de objetos Candidato.
     */
    List<Candidato> getCandidatos();

    /**
     * Este método deve adicionar um novo candidato à fase.
     *
     * @param novoCandidato Um objeto Candidato que representa o novo candidato a ser adicionado à fase.
     */
    void addCandidato(Candidato novoCandidato);

    /**
     * Este método deve remover um candidato da fase, dado o código do candidato.
     *
     * @param codCandidatoToRemove O código do candidato a ser removido.
     * @return Um objeto Candidato que representa o candidato removido.
     */
    Candidato removeCandidato(int codCandidatoToRemove);
}
