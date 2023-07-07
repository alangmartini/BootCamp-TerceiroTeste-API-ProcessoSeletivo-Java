package segundoteste.errors;

public class NomeInvalido extends IllegalArgumentException {
    public NomeInvalido() {
        super("Nome inválido");
    }
}
