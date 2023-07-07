package segundoteste.validators.nameValidator;

import segundoteste.errors.NomeIncorreto;

public class ValidatorNaoVazio {
    private Validator next;

    public void setNext(Validator next) {
        this.next = next;
    }

    public void validate(String nome) throws NomeIncorreto {
        if (nome == null || nome.isEmpty()) {
            throw new NomeIncorreto();
        }

        if (next != null) {
            next.validate(nome);
        }
    }
}
