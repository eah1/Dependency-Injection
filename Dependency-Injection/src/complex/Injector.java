
package complex;

import common.DependencyException;

/**
 *
 * @author eah1
 * @param <E>
 */
public interface Injector<E> {
    
    <E> void registerConstant(Class<E> name, E value) 
        throws DependencyException;
    
    <E> void registerFactory(Class<E> name, Factory<? extends E> creator, E[] parameters)
        throws DependencyException;
    
    <E> E getObject(Class<E> name)
        throws DependencyException;
    
}
