
package simple;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public class StoreConstant  extends Store {
    
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
    protected void addElement(String name, Object value) {
        super.constant.put(name, value);
    }

    /**
     * Metode per obtenir els valors de les Contants.
     * @param name Nom que fara de referencia per fer la busqueda.
     * @return Objecte, que tindra el valor de la Contants.
     * @throws DependencyException 
     */
    @Override
    protected Object getElement(String name) throws DependencyException {
        return super.constant.get(name);
    }

    /**
     * Metode per comprobar si es troba una Constant.
     * @param name Nom que fara de referencia per fer la busqueda.
     * @return Boolea.
     */
    @Override
    protected boolean checkElement(String name) {
        return super.constant.containsKey(name);
    }
    
}
