package segundoteste;

import java.util.List;

public interface IProcessManager {
    public int iniciarProcesso(String nome);
    public void marcarEntrevista(int codCandidato);

    public void desqualificarCandidato(int codCandidato);

    public String verificarStatusCandidato(int codCandidato);

    public void aprovarCandidato(int codCandidato);

    public List<String> obterAprovados();
}
