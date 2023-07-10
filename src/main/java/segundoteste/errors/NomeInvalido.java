package segundoteste.errors;

/**
 * Error disparado quando o nome falha na validação do nome.
 */
public class NomeInvalido extends IllegalArgumentException {
    public NomeInvalido() {
        super("Nome invalido");
    }
}
