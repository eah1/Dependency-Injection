
package simple;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public abstract class Store {

    public Store(){
        
    }
    
    protected abstract void addElement(String name, Object value);
    
    protected abstract Object getElement(String name) 
            throws DependencyException;
    
    protected abstract boolean checkElement(String name);
    
}
