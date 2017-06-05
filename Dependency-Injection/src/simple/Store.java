
package simple;

import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public abstract class Store {

    protected Map<String, Object> constant;
    protected Map<String, Pair<Factory, Object[]>> factory ;
    
    public Store(){
        this.constant = new HashMap<>();
        this.factory  = new HashMap<>();
    }
    
    protected abstract void addElement(String name, Object value);
    
    protected abstract Object getElement(String name) 
            throws DependencyException;
    
    protected abstract boolean checkElement(String name);
    
}
