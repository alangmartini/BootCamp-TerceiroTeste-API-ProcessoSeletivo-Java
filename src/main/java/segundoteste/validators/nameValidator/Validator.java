package segundoteste.validators.nameValidator;

import segundoteste.errors.NomeIncorreto;

public interface Validator {
    void setNext();
    void validate(String nome) throws NomeIncorreto;
}
