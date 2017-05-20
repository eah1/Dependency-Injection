
package simple;

import common.DependencyException;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author eah1
 */
public class Container implements Injector{

    private final Map<String, Object> constants;
    private final Map<String, Tuple>  factorys ;
    
    public Container(){
        this.constants = new HashMap<>();
        this.factorys  = new HashMap<>();
    }
    
    @Override
    public void registerConstant(String name, Object value) 
            throws DependencyException { 
        if(!this.constants.containsKey(name)){
            this.constants.put(name, value);
        } else throw new DependencyException("Constant duplicat");
    }

    @Override
    public void registerFactory(String name, Factory creator, String[] parameters) 
            throws DependencyException {
        if (parameters.length > 0 && shearchParameters(parameters)
                && !this.factorys.containsKey(name)) {
            Object[] obj = getParameters(parameters);              
            this.factorys.put(name, new Tuple(creator, obj));                
        } else throw new DependencyException("Factory Duplicat o Parametres"); 
    }

    @Override
    public Object getObject(String name) 
            throws DependencyException {
        Object ob = null;
        if (this.constants.containsKey(name)) {
            ob = this.constants.get(name);   
        } else if (this.factorys.containsKey(name)) {
            Factory factory; Object[] parameters;
            factory    = (Factory) this.factorys.get(name).getFactory();
            parameters = this.factorys.get(name).getParameters();
            ob = factory.create(parameters);
        } else throw new DependencyException("Element No trobat"); 
        return ob;        
    }

    private boolean shearchParameters(String[] parameters) {
        boolean shearch = false;
        for (String parameter : parameters) {
            if (this.constants.containsKey(parameter)) {
                shearch = true;
            } else return false; 
        }
        return shearch;  
    }
    
    private Object[] getParameters(String[] parameters) 
            throws DependencyException{
        int i = 0;
        Object[] obj = new Object[parameters.length];
        for (String parameter : parameters) {
            obj[i] = this.constants.get(parameter);
            i = +1;
        }
        return obj;
    } 

    private static class Tuple {
        
        private Factory factory;
        private Object[] parameters;
        
        private Tuple(Factory factory, Object[] parameters) {
            this.factory    = factory;
            this.parameters = parameters;
        }
        
        private Factory getFactory() {
            return this.factory;      
        }
        
        private Object[] getParameters() {
            return this.parameters;     
        }
        
    }
    
}
