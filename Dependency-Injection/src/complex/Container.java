
package complex;

import java.util.HashMap;
import java.util.Map;

import common.DependencyException;


/**
 *
 * @author eah1
 * @param <E>
 */
public class Container<E> implements Injector<E>  {

    private Map<Class<?>, Object> constants;
    private Map<Class<?>, Tuple<E>> factorys ;

    public Container(){
        this.constants = new HashMap<>();
        this.factorys  = new HashMap<>();
    }
    
    @Override
    public <E> void registerConstant(Class<E> name, E value) 
            throws DependencyException {
        if(!this.constants.containsKey(name)){
            this.constants.put(name, value);
        } else throw new DependencyException("Constant duplicat");
    }

    @Override
    public <E> void registerFactory(Class<E> name, Factory<? extends E> creator, 
            E[] parameters) throws DependencyException {
        if (parameters.length > 0 && shearchParameters(parameters)
                && !this.factorys.containsKey(name)) {
            Object[] obj = getParameters(parameters);              
            this.factorys.put(name, new Tuple(creator, obj));                
        } else throw new DependencyException("Factory Duplicat o Parametres"); 
    }

    @Override
    public <E> E getObject(Class<E> name) throws DependencyException {
        Object ob = null;
        if (this.constants.containsKey(name)) {
            ob = this.constants.get(name);   
        } else if (this.factorys.containsKey(name)) {
            Factory factory; Object[] parameters;
            factory    = (Factory) this.factorys.get(name).factory;
            parameters = this.factorys.get(name).parameters;
            ob = factory.create(parameters);
        } else throw new DependencyException("Element No trobat"); 
        return (E) ob;  
    }

    private <E> boolean shearchParameters(E[] parameters) {
        boolean shearch = false;
        for (E parameter : parameters) {
            if (this.constants.containsKey(parameter)) {
                shearch = true;
            } else return false; 
        }
        return shearch;
    }

    private <E> Object[] getParameters(E[] parameters) {
        int i = 0;
        Object[] obj = new Object[parameters.length];
        for (E parameter : parameters) {
            obj[i] = this.constants.get(parameter);
            i = +1;
        }
        return obj;
    }
    
    private static class Tuple<E> {

        private Factory<? extends E> factory;
        private E[] parameters;
        
        private Tuple(Factory<? extends E> factory, E[] parameters){
            this.factory    = factory;
            this.parameters = parameters;
        }

    }
    
}
