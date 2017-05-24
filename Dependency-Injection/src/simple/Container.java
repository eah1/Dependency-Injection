
package simple;

import common.DependencyException;
import java.util.ArrayList;
import java.util.List;

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
            ArrayList<Object> argvs = new ArrayList<Object>();
            for (String argv : parameters) {
                if (this.storeC.checkElement(argv)){ 
                    argvs.add(this.storeC.getElement(argv));
                } else throw new DependencyException("Constant no trobada");    
            }
            Object[] factory = {creator, argvs};
            this.storeF.addElement(name, factory);
        } else throw new DependencyException("Factory duplicada");
    }

    @Override
    public Object getObject(String name) 
            throws DependencyException {
        Object ob = null;
        if (this.storeC.checkElement(name)) {
            ob = this.storeC.getElement(name);   
        } else if (this.storeF.checkElement(name)) {
            ob = this.storeF.getElement(name);
        } else throw new DependencyException("Element no trobat"); 
        return ob;        
    }
    
}
