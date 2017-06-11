
package simple;

import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public abstract class Store {

    protected Map<String, Object> constant;
    protected Map<String, Pair<Factory, Object[]>> factory ;
    
    /**
     * Construcctor de la classe Store.
     * Iniciarem els Maps de les Constants i de les Factorys.
     */
    public Store(){
        this.constant = new HashMap<>();
        this.factory  = new HashMap<>();
    }
    
    /**
     * Afegirem elements al Map.
     * @param name Clau del Element.
     * @param value Valor que guadarem.
     */
    protected abstract void addElement(String name, Object value);
    
    /**
     * Retornarem el element que ens demanen.
     * @param name Clau del Element.
     * @return Objecte que tindrem al Map.
     * @throws DependencyException 
     */
    protected abstract Object getElement(String name) 
            throws DependencyException;
    
    /**
     * Comprovarem que el element esta al Map.
     * @param name Clau del Element.
     * @return Boolea.
     */
    protected abstract boolean checkElement(String name);
    
}
