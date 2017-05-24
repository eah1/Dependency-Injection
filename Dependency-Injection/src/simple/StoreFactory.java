
package simple;

import java.util.HashMap;
import java.util.Map;

import common.DependencyException;
import java.util.ArrayList;

/**
 *
 * @author eah1
 */
public class StoreFactory extends Store {
    
    
    protected Map<String, Tuple> factory ;
    
    public StoreFactory(){
        super();
        this.factory  = new HashMap<>();
    }
    
    @Override
    protected void addElement(String name, Object value) {
        Object[] factory = (Object[]) value;
        Object[] parameters = new Object[((ArrayList<Object>) factory[1]).size()];
        parameters = ((ArrayList<Object>) factory[1]).toArray(parameters);
        this.factory.put(name, new Tuple((Factory)factory[0], (Object[]) parameters));
    }

    @Override
    protected Object getElement(String name) throws DependencyException {
        Tuple tuple = (Tuple) this.factory.get(name);
        return tuple.getCreateFactory();
    }

    @Override
    protected boolean checkElement(String name) {
        if (this.factory.containsKey(name)) {
            return true;
        } else return false;
    }

}
