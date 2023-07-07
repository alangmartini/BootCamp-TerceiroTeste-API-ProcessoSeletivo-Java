package segundoteste.fases;

import segundoteste.candidatos.Candidato;

import java.util.List;

public interface IFase {
    List<Candidato> getCandidatos();
    void addCandidato(Candidato novoCandidato);

    Candidato removeCandidato(int codCandidatoToRemove);
}
