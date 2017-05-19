
package factorys_1;

import common.DependencyException;
import simple.Factory;

import implement.ImplementationB1;
import interfaces.InterfaceD;

/**
 *
 * @author eah1
 */
public class FactoryB1 implements Factory{

    @Override
    public ImplementationB1 create(Object[] parameters) 
            throws DependencyException {
        
        InterfaceD d;
        
        try {
            d = (InterfaceD) parameters[0];
        } catch(ClassCastException | ArrayIndexOutOfBoundsException ex){
            throw new DependencyException(ex);
        }
        
        return new ImplementationB1(d);
        
    }
    
}
