
package simple;

import java.util.HashMap;
import java.util.Map;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public class StoreConstant  extends Store {
    
    private Map<String, Object> constant;
    
    public StoreConstant(){
        super();
        this.constant = new HashMap<>();
    }
    
    @Override
    protected void addElement(String name, Object value) {
        this.constant.put(name, value);
    }

    @Override
    protected Object getElement(String name) throws DependencyException {
        return this.constant.get(name);
    }

    @Override
    protected boolean checkElement(String name) {
        return this.constant.containsKey(name);
    }
    
}
