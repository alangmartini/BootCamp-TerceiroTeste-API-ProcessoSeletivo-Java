package segundoteste.candidatos;

import lombok.Getter;
import lombok.Setter;
import segundoteste.fases.EnumFase;

@Getter
@Setter
public abstract class AbsCandidato {
    private EnumFase faseAtual;
    private int codCandidato;
    private String nome;
}