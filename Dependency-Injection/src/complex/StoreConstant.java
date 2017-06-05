
package complex;

import common.DependencyException;

/**
 *
 * @author eah1
 * @param <E>
 */
public class StoreConstant<E>  extends Store<E> {
    
    public StoreConstant(){
        super();
    }

    @Override
    protected void addElement(Class<E> name, E value) {
        super.constant.put(name, value);
    }

    @Override
    protected Object getElement(Class<E> name) throws DependencyException {
        return super.constant.get(name);
    }

    @Override
    protected boolean checkElement(Class<E> name) {
        return super.constant.containsKey(name);
    }
 
}
