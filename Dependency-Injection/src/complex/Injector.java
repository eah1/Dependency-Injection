
package complex;

import common.DependencyException;

/**
 *
 * @author eah1
 * @param <E>
 */
public interface Injector<E> {
    
    public <E> void registerConstant(Class<E> name, E value) 
        throws DependencyException;
    
    public <E> void registerFactory(Class<E> name, Factory<? extends E> creator, 
            Class<E> ... parameters) throws DependencyException;
    
    public <E> E getObject(Class<E> name)
        throws DependencyException;
    
}
