package segundoteste;

import segundoteste.candidatos.Candidato;
import segundoteste.errors.CandidatoDuplicado;
import segundoteste.errors.CandidatoNaoEncontrado;
import segundoteste.fases.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

// Chamado de Segundo para seguir os requerimentos do teste
public class Segundo implements IProcessManager {
    // TODO: Ao invés de utilizar a quantidade de cadidatos para gerar o ID, criar um maneira randômica e/ou hasheada.
    static int CandidatosTotais = 0;
    AbsFase recebidos;
    AbsFase qualificados;
    AbsFase aprovados;

    public Segundo() {
        try {
            this.recebidos = FaseFactory.getFase(Recebidos.class);
            this.qualificados = FaseFactory.getFase(Recebidos.class);
            this.aprovados = FaseFactory.getFase(Recebidos.class);
        } catch (Exception e) {
            System.out.println("Erro ao instanciar Fases do Processo");
        }


    }
    public int iniciarProcesso(String nome) throws CandidatoDuplicado {
        int codCandidato = Segundo.CandidatosTotais + 1;
        CandidatosTotais += 1;

        Candidato novoCandidato = new Candidato(nome, codCandidato);
        this.recebidos.addCandidato(novoCandidato);

        return codCandidato;
    }
    public void marcarEntrevista(int codCandidato) throws CandidatoNaoEncontrado {
        Candidato candidatoAEntrevistar = this
                .recebidos.removeCandidato(codCandidato);

        this.qualificados.addCandidato(candidatoAEntrevistar);
    }

    public void aprovarCandidato(int codCandidato) {
        Candidato candidatoAprovado = this
                .qualificados.removeCandidato(codCandidato);

        this.aprovados.addCandidato(candidatoAprovado);
    }

    public void desqualificarCandidato(int codCandidato) throws CandidatoNaoEncontrado {
        Candidato candidatoADesqualificar = encontrarCandidateEmFases(codCandidato);

        AbsFase faseDoCandidato = AbsFase
                .fasesInstanciadas
                .get(candidatoADesqualificar.getFaseAtual().name());

        faseDoCandidato.removeCandidato(codCandidato);
    }

    public Candidato encontrarCandidateEmFases(int codCandidato) {
        Candidato candidatoEncontrado = null;

        for (AbsFase fase : AbsFase.fasesInstanciadas.values()) {
            try {
                Candidato candidato = fase.getCandidato(codCandidato);
                if (candidato != null) {
                    candidatoEncontrado =  candidato;
                }
            } catch (CandidatoNaoEncontrado e) {}
        }

        if (candidatoEncontrado == null) {
            throw new CandidatoNaoEncontrado();
        }

        return candidatoEncontrado;
    }

    public String verificarStatusCandidato(int codCandidato) throws CandidatoNaoEncontrado {
        Candidato candidato = encontrarCandidateEmFases(codCandidato);

        return candidato.getFaseAtual().name();
    }

    public List<String> obterAprovados() {
        List<Candidato> cadidatosAprovados = this.aprovados.getCandidatos();

        List<String> nomesAprovados = cadidatosAprovados
                .stream()
                .map(candidato -> candidato.getNome())
                .toList();

        return nomesAprovados;
    }
}
