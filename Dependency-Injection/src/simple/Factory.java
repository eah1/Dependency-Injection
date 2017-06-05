
package simple;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public interface Factory {
    
    Object create(Object... parameters)
        throws DependencyException;
    
}
