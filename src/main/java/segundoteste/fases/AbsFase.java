package segundoteste.fases;

import segundoteste.candidatos.Candidato;
import segundoteste.errors.CandidatoDuplicado;
import segundoteste.errors.CandidatoNaoEncontrado;

import java.util.*;

public abstract  class AbsFase implements IFase{
    public static Map<String, AbsFase> fasesInstanciadas = new HashMap<>();
    List<Candidato> candidatos = new ArrayList<>();

    /**
     * Adiciona a nova instancia à fasesInstanciadas.
     *
     * FaseFactory é o responsável por manter apenas uma única instancia de cada implementação de AbsFase.
     */
    protected AbsFase() {
        fasesInstanciadas.put(this.getClass().getSimpleName(), this);
    }

    @Override
    public List<Candidato> getCandidatos(){
        return candidatos;
    }

    public Candidato getCandidato(int codCandidato) throws CandidatoNaoEncontrado {
        Candidato candidate = candidatos.stream()
                .filter(candidato -> candidato.getCodCandidato() == codCandidato)
                .findFirst()
                .orElseThrow(CandidatoNaoEncontrado::new);

        return candidate;
    }
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
    @Override
    public Candidato removeCandidato(int codCandidatoToRemove) throws CandidatoNaoEncontrado {
        Candidato candidateToRemove = this.getCandidato(codCandidatoToRemove);

        candidatos.removeIf(candidato -> candidato.getCodCandidato() == codCandidatoToRemove);

        return candidateToRemove;


    }
}
