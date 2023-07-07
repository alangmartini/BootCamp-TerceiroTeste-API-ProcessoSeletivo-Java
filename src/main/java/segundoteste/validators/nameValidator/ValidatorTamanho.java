package segundoteste.validators.nameValidator;

import segundoteste.errors.NomeInvalido;

public class ValidatorTamanho implements Validator{
    private Validator next;

    public void setNext(Validator next) {
        this.next = next;
    }

    public void validate(String nome) throws NomeInvalido {
        if (nome.length() < 1 || nome.length() > 50) {
            throw new NomeInvalido();
        }

        if (this.next != null) {
            next.validate(nome);
        }
    }
}
