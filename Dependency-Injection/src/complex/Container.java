
package complex;

import javafx.util.Pair;

import common.DependencyException;

/**
 *
 * @author eah1
 * @param <E>
 */
public class Container<E> implements Injector<E>  {

    private Store storeF;
    private Store storeC;

    public Container(){
        this.storeF = new StoreFactory();
        this.storeC = new StoreConstant();
    }
    
    @Override
    public <E> void registerConstant(Class<E> name, E value) 
            throws DependencyException {
        
        if (!this.storeC.checkElement(name)) {
            this.storeC.addElement(name, value);
        } else throw new DependencyException("Constant duplicada");
        
    }

    @Override
    public <E> void registerFactory(Class<E> name, Factory<? extends E> creator, 
           Class<E>... parameters) throws DependencyException {
        
        E[] args = (E[]) parameters;
        
        if (!this.storeF.checkElement(name) && args.length > 0) {
            
            Pair<Factory<? extends E>, E> factory;
            factory = new Pair(creator, (Object[])args);
            
            this.storeF.addElement(name, factory);
            
        } else throw new DependencyException("Factory duplicada");
        
    }

    @Override
    public <E> E getObject(Class<E> name) throws DependencyException {
        
        Object ob = null;
        
        if (this.storeC.checkElement(name)) {
            return (E) this.storeC.getElement(name);
        } else if (this.storeF.checkElement(name)) {
            
            E tuple = (E) this.storeF.getElement(name);
            Pair<Factory<? extends E>, E> factory = (Pair<Factory<? extends E>, E>) tuple;
            E[] argv = this.getParameters((E[]) factory.getValue());
            
            return factory.getKey().create(argv);
            
        } else throw new DependencyException("Element no trobat");
    }
    
    private <E> E[] getParameters(E[] parameters) 
            throws DependencyException {

        E[] argv = (E[]) new Object[parameters.length];
        Integer i = 0;
        
        for (E parameter : parameters) {
            
            if (this.storeC.checkElement((Class) parameter)) {
                argv[i] = (E) this.storeC.getElement((Class) parameter);
                i = i + 1;
            } else throw new DependencyException("Error arguments");
            
        }
        
        return argv;
        
    }
      
}
