/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorys_2;
import complex.Factory;
import common.DependencyException;

import implement.ImplementationB1;
import interfaces.InterfaceD;

/**
 *
 * @author eah1
 */
public class FactoryB1 implements Factory<ImplementationB1> {

    @Override
    public <E> E create(Object[] parameters) 
            throws DependencyException {
        
        InterfaceD d;
        
        try {
            d = (InterfaceD) parameters[0];
        } catch(ClassCastException | ArrayIndexOutOfBoundsException ex){
            throw new DependencyException(ex);
        }
        
        return (E) new ImplementationB1(d);

    }
    
}
