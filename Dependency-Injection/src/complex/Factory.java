
package complex;

import common.DependencyException;

/**
 *
 * @author eah1
 * @param <E>
 */
public interface Factory<E> {
    
    public <E> E create(Object... parameters) throws DependencyException;
    
}
