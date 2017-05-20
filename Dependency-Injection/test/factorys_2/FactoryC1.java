
package factorys_2;

import complex.Factory;
import common.DependencyException;

import implement.ImplementationC1;

/**
 *
 * @author eah1
 */
public class FactoryC1 implements Factory<ImplementationC1> {

    @Override
    public <E> E create(Object[] parameters) 
            throws DependencyException {
        
        String s;
        
        try {
            s = (String) parameters[0];
        } catch(ClassCastException | ArrayIndexOutOfBoundsException ex){
            throw new DependencyException(ex);
        }
        
        return (E) new ImplementationC1(s);
        
    }
    
}
