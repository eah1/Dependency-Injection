
package factorys_1;

import common.DependencyException;
import simple.Factory;

import implement.ImplementationA1;
import interfaces.InterfaceB;
import interfaces.InterfaceC;

/**
 *
 * @author eah1
 */
public class FactoryA1 implements Factory{

    @Override
    public ImplementationA1 create(Object[] parameters) 
            throws DependencyException {
        
        InterfaceB b;
        InterfaceC c;
        
        try {
            b = (InterfaceB) parameters[0];
            c = (InterfaceC) parameters[1];
        } catch(ClassCastException | ArrayIndexOutOfBoundsException ex){
            throw new DependencyException(ex);
        }
        
        return new ImplementationA1(b, c);
        
    }
    
}
