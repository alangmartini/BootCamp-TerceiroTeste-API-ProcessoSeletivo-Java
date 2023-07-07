package segundoteste;

import segundoteste.candidatos.Candidato;
import segundoteste.errors.CandidatoDuplicado;
import segundoteste.errors.CandidatoNaoEncontrado;
import segundoteste.errors.NomeInvalido;
import segundoteste.fases.*;
import segundoteste.validators.nameValidator.Validator;
import segundoteste.validators.nameValidator.ValidatorCaracteres;
import segundoteste.validators.nameValidator.ValidatorNaoVazio;
import segundoteste.validators.nameValidator.ValidatorTamanho;

import java.util.List;

// Chamado de Segundo para seguir os requerimentos do teste
public class Segundo implements IProcessManager {
    // TODO: Ao invés de utilizar a quantidade de cadidatos para gerar o ID, criar um maneira randômica e/ou hasheada.
    static int CandidatosTotais = 0;
    AbsFase recebidos;
    AbsFase qualificados;
    AbsFase aprovados;

    public Segundo() {
        try {
            this.recebidos = new Recebidos();
            this.qualificados = new Qualificados();
            this.aprovados = new Aprovados();
        } catch (Exception e) {
            System.out.println("Erro ao instanciar Fases do Processo");
        }


    }
    public synchronized int iniciarProcesso(String nome) throws CandidatoDuplicado, NomeInvalido {
        // Validatores de nome
        Validator validator = new ValidatorNaoVazio();
        Validator validatorChar = new ValidatorCaracteres();
        Validator validatorTam = new ValidatorTamanho();

        validator.setNext(validatorChar);
        validatorChar.setNext(validatorTam);

        int codCandidato = Segundo.CandidatosTotais + 1;
        Segundo.CandidatosTotais += 1;

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
                .get(candidatoADesqualificar.getFaseAtual());

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

        return candidato.getFaseAtual();
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
