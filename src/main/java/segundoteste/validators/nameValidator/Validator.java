package segundoteste.validators.nameValidator;

import segundoteste.errors.NomeInvalido;

public interface Validator {
    void setNext(Validator next);
    void validate(String nome) throws NomeInvalido;
}
