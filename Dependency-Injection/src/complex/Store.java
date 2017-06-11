
package complex;

import common.DependencyException;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

/**
 *
 * @author eah1
 * @param <E>
 */
public abstract class Store <E>{

    protected Map<Class<E>, E> constant;
    protected Map<Class<?>, Pair<Factory<? extends E>, E[]>> factory ;
    
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
    protected abstract void addElement(Class<E> name,  E value);
    
    /**
     * Retornarem el element que ens demanen.
     * @param name Clau del Element.
     * @return Objecte que tindrem al Map.
     * @throws DependencyException 
     */
    protected abstract Object getElement(Class<E> name) 
            throws DependencyException;
    
    /**
     * Comprovarem que el element esta al Map.
     * @param name Clau del Element.
     * @return Boolea.
     */
    protected abstract boolean checkElement(Class<E> name);
    
}
