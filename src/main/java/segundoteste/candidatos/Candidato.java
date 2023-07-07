package segundoteste.candidatos;


import segundoteste.fases.EnumFase;
import segundoteste.fases.IFase;
import segundoteste.fases.Recebidos;

public  class Candidato extends AbsCandidato {
    public Candidato(
            String nome,
            int codCandidato
    ) {
        this.setNome(nome);
        this.setCodCandidato(codCandidato);
    }
}
