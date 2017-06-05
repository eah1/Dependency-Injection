
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

    public Store(){
        this.constant = new HashMap<>();
        this.factory  = new HashMap<>();
    }
    
    protected abstract void addElement(Class<E> name,  E value);
    
    protected abstract Object getElement(Class<E> name) 
            throws DependencyException;
    
    protected abstract boolean checkElement(Class<E> name);
    
}
