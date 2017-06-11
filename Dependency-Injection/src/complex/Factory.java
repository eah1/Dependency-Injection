
package complex;

import common.DependencyException;

/**
 *
 * @author eah1
 * @param <E>
 */
public interface Factory<E> {
    
    /**
     * Creara la Factory amb els parametres que tindra.
     * @param <E>
     * @param parameters Valors dels parametres que tindra la Factory.
     * @return Objecte creat de la Factory.
     * @throws DependencyException 
     */
    public <E> E create(Object... parameters) throws DependencyException;
    
}
