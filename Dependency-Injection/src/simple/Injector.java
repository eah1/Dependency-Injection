
package simple;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public interface Injector {
    
    public void registerConstant(String name, Object value) 
        throws DependencyException;
    
    public void registerFactory(String name, Factory creator, String... parameters)
        throws DependencyException;
    
    public Object getObject(String name)
        throws DependencyException;
    
}
