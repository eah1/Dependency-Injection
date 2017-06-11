
package complex;

import common.DependencyException;

/**
 *
 * @author eah1
 * @param <E>
 */
public class StoreConstant<E>  extends Store<E> {
    
    /**
     * Constructor de la classe StoreConstant, heredara els metodes de la classe
     * Store.
     */
    public StoreConstant(){
        super();
    }

    /**
     * Metode per registrar Constants.
     * @param name Nom en que es guardara la Constant.
     * @param value Valor que tindra la Constant.
     */
    @Override
    protected void addElement(Class<E> name, E value) {
        super.constant.put(name, value);
    }

    /**
     * Metode per obtenir els valors de les Contants.
     * @param name Nom que fara de referencia per fer la busqueda.
     * @return Objecte, que tindra el valor de la Contants.
     * @throws DependencyException 
     */
    @Override
    protected Object getElement(Class<E> name) throws DependencyException {
        return super.constant.get(name);
    }

    /**
     * Metode per comprobar si es troba una Constant.
     * @param name Nom que fara de referencia per fer la busqueda.
     * @return Boolea.
     */
    @Override
    protected boolean checkElement(Class<E> name) {
        return super.constant.containsKey(name);
    }
 
}
