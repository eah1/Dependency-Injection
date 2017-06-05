
package simple;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public class StoreConstant  extends Store {
    
    public StoreConstant(){
        super();
    }
    
    @Override
    protected void addElement(String name, Object value) {
        super.constant.put(name, value);
    }

    @Override
    protected Object getElement(String name) throws DependencyException {
        return super.constant.get(name);
    }

    @Override
    protected boolean checkElement(String name) {
        return super.constant.containsKey(name);
    }
    
}
