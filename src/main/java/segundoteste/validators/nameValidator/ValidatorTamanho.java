package segundoteste.validators.nameValidator;

import segundoteste.errors.NomeIncorreto;

public class ValidatorTamanho {
    private Validator next;

    private void setNext(Validator next) {
        this.next = next;
    }

    private void validate(String nome) throws NomeIncorreto {
        if (nome.length() < 1 || nome.length() > 50) {
            throw new NomeIncorreto();
        }

        if (this.next != null) {
            next.validate(nome);
        }
    }
}
