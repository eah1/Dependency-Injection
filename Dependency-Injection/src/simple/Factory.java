
package simple;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public interface Factory {
    
    /**
     * Creara la Factory amb els parametres que tindra.
     * @param parameters Valors dels parametres que tindra la Factory.
     * @return Objecte creat de la Factory.
     * @throws DependencyException 
     */
    public Object create(Object... parameters)
        throws DependencyException;
    
}
