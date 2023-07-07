package segundoteste.validators.nameValidator;

import segundoteste.errors.NomeInvalido;

public class ValidatorNaoVazio implements Validator {
    private Validator next;

    public void setNext(Validator next) {
        this.next = next;
    }

    public void validate(String nome) throws NomeInvalido {
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalido();
        }

        if (next != null) {
            next.validate(nome);
        }
    }
}
