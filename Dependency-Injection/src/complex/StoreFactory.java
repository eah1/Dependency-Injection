
package complex;

import javafx.util.Pair;

import common.DependencyException;

/**
 *
 * @author eah1
 * @param <E>
 */
public class StoreFactory<E> extends Store<E> {
    
    public StoreFactory(){
        super();
    }

    @Override
    protected void addElement(Class<E> name, E value) {
        Pair<Factory<? extends E>, E[]> factory = (Pair<Factory<? extends E>, E[]>) value;
        super.factory.put(name, factory);
    }

    @Override
    protected Object getElement(Class<E> name) throws DependencyException {
        return (Pair<Factory<? extends E>, E[]>) super.factory.get(name);
    }

    @Override
    protected boolean checkElement(Class<E> name) {
        return super.factory.containsKey(name);
    }

}
