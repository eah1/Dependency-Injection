
package simple;

import javafx.util.Pair;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public class StoreFactory extends Store {
    
    
    /**
     * Constructor de la classe StoreFactory, heredara els metodes de la classe
     * Store. 
     */
    public StoreFactory(){
        super();
    }
    
    /**
     * Metode per registrar Factorys.
     * @param name Nom en que es guardara la Factory.
     * @param value Valor que tindra la Factory, que en aquest cas sera una Pair.
     */
    @Override
    protected void addElement(String name, Object value) {
        super.factory.put(name, (Pair<Factory, Object[]>) value);
    }

    /**
     * Metode per obtenir els valors de les Factorys.
     * @param name Nom que fara de referencia per fer la busqueda.
     * @return Objecte tipus Pair.
     * @throws DependencyException 
     */
    @Override
    protected Object getElement(String name) throws DependencyException {
        return (Pair<Factory, Object[]>) super.factory.get(name);
    }

    /**
     * Metode per comprobar si es troba una Factorys.
     * @param name Nom que fara de referencia per fer la busqueda.
     * @return Boolea.
     */
    @Override
    protected boolean checkElement(String name) {
        return super.factory.containsKey(name);
    }

}
