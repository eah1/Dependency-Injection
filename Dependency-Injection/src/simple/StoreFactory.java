
package simple;

import javafx.util.Pair;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public class StoreFactory extends Store {
    
    
    
    public StoreFactory(){
        super();
    }
    
    @Override
    protected void addElement(String name, Object value) {
        Pair<Factory, Object[]> factory = (Pair<Factory, Object[]>) value;
        super.factory.put(name, factory);
    }

    @Override
    protected Object getElement(String name) throws DependencyException {
        return (Pair<Factory, Object[]>) super.factory.get(name);
    }

    @Override
    protected boolean checkElement(String name) {
        return super.factory.containsKey(name);
    }

}
