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

/**
 * A class named 'Segundo' for managing a multi-phase candidate process. It implements the IProcessManager interface.
 * It provides methods for initiating a process for a candidate, scheduling an interview, approving a candidate,
 * disqualifying a candidate, checking a candidate's status, and obtaining a list of approved candidates.
 *
 * TODO: Instead of using the total number of candidates to generate the ID, a random and/or hashed way should be created.
 */
public class Segundo implements IProcessManager {
    static int CandidatosTotais = 0;
    AbsFase recebidos;
    AbsFase qualificados;
    AbsFase aprovados;


    /**
     * Construtor, inicializa os tres estágios do process: Recebidos, Qualificados, Aprovados.
     */
    public Segundo() {
        try {
            this.recebidos = new Recebidos();
            this.qualificados = new Qualificados();
            this.aprovados = new Aprovados();
        } catch (Exception e) {
            System.out.println("Erro ao instanciar Fases do Processo");
        }
    }

	public void reset() {
		AbsFase.fasesInstanciadas.clear();
		Segundo.CandidatosTotais = 0;
	}

    /**
     * Inicia o processo para um candidato. Realiza uma validação de nome.
     * @param nome O nome do candidato
     * @return Código do candidato criado.
     * @throws CandidatoDuplicado se um candidato com o mesmo nome já existe.
     * @throws NomeInvalido se o nome for inválido.
     */
    public synchronized int iniciarProcesso(String nome) throws CandidatoDuplicado, NomeInvalido {
        // Validatores de nome
        Validator validator = new ValidatorNaoVazio();
        Validator validatorChar = new ValidatorCaracteres();
        Validator validatorTam = new ValidatorTamanho();

        validator.setNext(validatorChar);
        validatorChar.setNext(validatorTam);
        validator.validate(nome);

	    Candidato possivelCandidato = this.encontrarCandidatoPorNome(nome);

	    if (possivelCandidato != null) {
		    throw new CandidatoDuplicado();
	    }

        int codCandidato = Segundo.CandidatosTotais + 1;
	    Segundo.CandidatosTotais += 1;

        Candidato novoCandidato = new Candidato(nome, codCandidato);

        this.recebidos.addCandidato(novoCandidato);

        return codCandidato;
    }

    /**
     * Marca uma entrevista com o candidato.
     * @param codCandidato O código do candidato.
     * @throws CandidatoNaoEncontrado se nenhum candidato com o codCandidato foi encontrado.
     */
    public void marcarEntrevista(int codCandidato) throws CandidatoNaoEncontrado {
        Candidato candidatoAEntrevistar = this
                .recebidos.removeCandidato(codCandidato);

        this.qualificados.addCandidato(candidatoAEntrevistar);
    }

    /**
     * Aprova um candidato.
     * @param codCandidato O código do candidato.
     */
    public void aprovarCandidato(int codCandidato) {
        Candidato candidatoAprovado = this
                .qualificados.removeCandidato(codCandidato);

        this.aprovados.addCandidato(candidatoAprovado);
    }

    /**
     * Desqualifica um candidato.
     * @param codCandidato O código do candidato.
     * @throws CandidatoNaoEncontrado se nenhum candidato com o codCandidato foi encontrado.
     */
    public void desqualificarCandidato(int codCandidato) throws CandidatoNaoEncontrado {
        Candidato candidatoADesqualificar = encontrarCandidateEmFases(codCandidato);

        AbsFase faseDoCandidato = AbsFase
                .fasesInstanciadas
                .get(candidatoADesqualificar.getFaseAtual());

        faseDoCandidato.removeCandidato(codCandidato);
    }

    /**
     * Encontra um candidato através das diferentes fases.
     * @param codCandidato O código do candidato.
     * @return O candidato, caso seja encontrado, ou throw uma exceção.
     * @throws CandidatoNaoEncontrado se nenhum candidato com o codCandidato foi encontrado.
     */
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

    /**
     * Encontra um candidato através das diferentes fases pelo nome.
     * @param nome Nome do candidato.
     * @return O candidato, caso seja encontrado, ou throw uma exceção.
     * @throws CandidatoNaoEncontrado se nenhum candidato com o codCandidato foi encontrado.
     */
    public Candidato encontrarCandidatoPorNome(String nome) {
        Candidato candidatoEncontrado = null;

        for (AbsFase fase : AbsFase.fasesInstanciadas.values()) {
            try {
                Candidato candidato = fase.getCandidatoByNome(nome);
                if (candidato != null) {
                    candidatoEncontrado =  candidato;
                }
            } catch (CandidatoNaoEncontrado e) {}
        }


        return candidatoEncontrado;
    }

    /**
     * Verifica a faseAtual de um candidato.
     * @param codCandidato O código do candidato.
     * @return A fase atual do candidato como String.
     * @throws CandidatoNaoEncontrado se nenhum candidato com o codCandidato foi encontrado.
     */
    public String verificarStatusCandidato(int codCandidato) throws CandidatoNaoEncontrado {
        Candidato candidato = encontrarCandidateEmFases(codCandidato);
		System.out.println(candidato.getNome());
        String faseAtual = candidato.getFaseAtual();
        String faseAtualTrimmed = faseAtual;

        if (faseAtual.endsWith("s")) {
            faseAtualTrimmed = faseAtual.substring(0, faseAtual.length() - 1);
        }


        return faseAtualTrimmed;
    }

    /**
     * Obtém uma lista de candidatos aprovados
     * @return Uma lista de Strings com nome dos aprovados.
     */
    public List<String> obterAprovados() {
        List<Candidato> cadidatosAprovados = this.aprovados.getCandidatos();

        List<String> nomesAprovados = cadidatosAprovados
                .stream()
                .map(candidato -> candidato.getNome())
                .toList();

        return nomesAprovados;
    }
}
