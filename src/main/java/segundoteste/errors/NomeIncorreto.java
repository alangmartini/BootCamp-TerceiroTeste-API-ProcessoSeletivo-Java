package segundoteste.errors;

public class NomeIncorreto extends IllegalArgumentException {
    public NomeIncorreto() {
        super("Nome inválido");
    }
}
