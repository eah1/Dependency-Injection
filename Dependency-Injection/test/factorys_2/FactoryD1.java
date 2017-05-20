
package factorys_2;

import common.DependencyException;
import complex.Factory;

import implement.ImplementationD1;


/**
 *
 * @author eah1
 */
public class FactoryD1 implements Factory<ImplementationD1> {

    @Override
    public <E> E create(Object[] parameters) 
            throws DependencyException {
        
        int i;
        
        try {
            i = (int) parameters[0];
        } catch (ClassCastException | ArrayIndexOutOfBoundsException ex) {
            throw new DependencyException(ex);
        }
        
        return (E) new ImplementationD1(i);
    
    
    }
    
}
