package segundoteste.validators.nameValidator;

import segundoteste.errors.NomeInvalido;

public class ValidatorCaracteres implements Validator {
    private Validator next;

    public void setNext(Validator next) {
        this.next = next;
    }

    public void validate(String nome) throws NomeInvalido {
        if (!nome.matches(".*[a-zA-Z\\p{L}'].+")) {
            throw new NomeInvalido();
        }

        if (this.next != null) {
            next.validate(nome);
        }
    }
}

