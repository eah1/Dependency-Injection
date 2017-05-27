
package simple;

import javafx.util.Pair;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public class Container implements Injector{

    private Store storeF;
    private Store storeC;
    
    public Container(){
        this.storeF = new StoreFactory();
        this.storeC = new StoreConstant();
    }
    
    @Override
    public void registerConstant(String name, Object value) 
            throws DependencyException {
        
        if (!this.storeC.checkElement(name)) {
            this.storeC.addElement(name, value);
        } else throw new DependencyException("Constant duplicada");
    
    }

    @Override
    public void registerFactory(String name, Factory creator, String[] parameters) 
            throws DependencyException {
        
        if (!this.storeF.checkElement(name) && parameters.length > 0) {
            
            Pair<Factory, Object[]> factory;
            factory = new Pair(creator, (Object[])parameters);
            
            this.storeF.addElement(name, factory);
            
        } else throw new DependencyException("Factory duplicada");
        
    }

    @Override
    public Object getObject(String name) 
            throws DependencyException {
        
        if (this.storeC.checkElement(name)) {
            return this.storeC.getElement(name);
        } else if (this.storeF.checkElement(name)) {
            
            Object tuple = this.storeF.getElement(name);
            Pair<Factory, Object[]> factory = (Pair<Factory, Object[]>) tuple;
            
            Object[] argv = this.getParameters(factory.getValue());
            
            return factory.getKey().create(argv);
            
        } else throw new DependencyException("Element no trobat");    
    
    }
    
    private Object[] getParameters(Object[] parameters) 
            throws DependencyException {
        
        Object[] argv = new Object[parameters.length];
        int i = 0;
        
        for (Object parameter : parameters) {
            
            if (this.storeC.checkElement((String) parameter)) {
                argv[i] = this.storeC.getElement((String) parameter);
            } else throw new DependencyException("Error arguments");
            
        }
        
        return argv;
        
    }
    
}
