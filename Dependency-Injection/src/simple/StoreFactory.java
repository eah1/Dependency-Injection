
package simple;

import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public class StoreFactory extends Store {
    
    protected Map<String, Pair<Factory, Object[]>> factory ;
    
    public StoreFactory(){
        super();
        this.factory  = new HashMap<>();
    }
    
    @Override
    protected void addElement(String name, Object value) {
        Pair<Factory, Object[]> factory = (Pair<Factory, Object[]>) value;
        this.factory.put(name, factory);
    }

    @Override
    protected Object getElement(String name) throws DependencyException {
        return (Pair<Factory, Object[]>) this.factory.get(name);
    }

    @Override
    protected boolean checkElement(String name) {
        return this.factory.containsKey(name);
    }

}
