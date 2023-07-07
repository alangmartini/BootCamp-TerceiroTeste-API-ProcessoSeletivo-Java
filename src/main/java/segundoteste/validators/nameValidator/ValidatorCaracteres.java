package segundoteste.validators.nameValidator;

import segundoteste.errors.NomeIncorreto;

public class ValidatorCaracteres {
    private Validator next;

    private void setNext(Validator next) {
        this.next = next;
    }

    private void validate(String nome) throws NomeIncorreto {
        if (!nome.matches("[a-zA-Z'-]+")) {
            throw new NomeIncorreto();
        }

        if (this.next != null) {
            next.validate(nome);
        }
    }
}

