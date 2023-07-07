package segundoteste.fases;

import segundoteste.candidatos.Candidato;
import segundoteste.errors.CandidatoDuplicado;
import segundoteste.errors.CandidatoNaoEncontrado;

import java.util.*;

/**
 * AbsFase é uma classe abstrata que implementa a interface IFase.
 * Representa uma fase genérica do processo de seleção de candidatos.
 * Mantém uma lista de candidatos participantes e oferece métodos para manipulá-los.
 */
public abstract class AbsFase implements IFase {
    public static Map<String, AbsFase> fasesInstanciadas = new HashMap<>();
    List<Candidato> candidatos = new ArrayList<>();

    /**
     * Construtor para a classe AbsFase.
     * Adiciona a nova instância de fase ao mapa de fasesInstanciadas.
     */
    protected AbsFase() {
        fasesInstanciadas.put(this.getClass().getSimpleName(), this);
    }

    /**
     * Este método retorna a lista de candidatos nesta fase.
     *
     * @return Uma lista de candidatos.
     */
    @Override
    public List<Candidato> getCandidatos(){
        return candidatos;
    }

    /**
     * Este método retorna um candidato específico, dado o código do candidato.
     *
     * @param codCandidato O código do candidato.
     * @return Um objeto Candidato que representa o candidato encontrado.
     * @throws CandidatoNaoEncontrado se o candidato com o código dado não for encontrado.
     */
    public Candidato getCandidato(int codCandidato) throws CandidatoNaoEncontrado {
        Candidato candidate = candidatos.stream()
                .filter(candidato -> candidato.getCodCandidato() == codCandidato)
                .findFirst()
                .orElseThrow(CandidatoNaoEncontrado::new);

        return candidate;
    }

    /**
     * Este método adiciona um novo candidato à lista de candidatos desta fase.
     *
     * @param novoCandidato Um objeto Candidato que representa o novo candidato.
     * @throws CandidatoDuplicado se já existir um candidato com o mesmo nome ou código.
     */
    @Override
    public void addCandidato(Candidato novoCandidato) throws CandidatoDuplicado {
        boolean isExistente = candidatos
                .stream()
                .anyMatch(
                        candidato -> (
                                candidato.getCodCandidato() == novoCandidato.getCodCandidato()
                                        || candidato.getNome() == novoCandidato.getNome()
                        ));

        if (isExistente) {
            throw new CandidatoDuplicado();
        }

        novoCandidato.setFaseAtual(this.getClass().getSimpleName());
        candidatos.add(novoCandidato);
    }

    /**
     * Este método remove um candidato da lista de candidatos desta fase, dado o código do candidato.
     *
     * @param codCandidatoToRemove O código do candidato a ser removido.
     * @return Um objeto Candidato que representa o candidato removido.
     * @throws CandidatoNaoEncontrado se o candidato com o código dado não for encontrado.
     */
    @Override
    public Candidato removeCandidato(int codCandidatoToRemove) throws CandidatoNaoEncontrado {
        Candidato candidateToRemove = this.getCandidato(codCandidatoToRemove);

        candidatos.removeIf(candidato -> candidato.getCodCandidato() == codCandidatoToRemove);

        return candidateToRemove;
    }
}
