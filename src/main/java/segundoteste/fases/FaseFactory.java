package segundoteste.fases;

/**
 * Utilizado para ter certeza que apenas uma instancia de cada tipo de Fase existe.
 *
 * Ex: apenas uma instancia de Aprovados pode existir.
 */
public class FaseFactory {
    public static AbsFase getFase(Class<? extends AbsFase> faseClass) throws Exception {
        if(AbsFase.fasesInstanciadas.containsKey(faseClass.getName())) {
            return AbsFase.fasesInstanciadas.get(faseClass.getName());
        } else {
            return faseClass.getDeclaredConstructor().newInstance();
        }
    }
}
