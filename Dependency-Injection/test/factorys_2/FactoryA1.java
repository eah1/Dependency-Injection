/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorys_2;

import complex.Factory;
import common.DependencyException;

import implement.ImplementationA1;
import interfaces.InterfaceB;
import interfaces.InterfaceC;

/**
 *
 * @author eah1
 */
public class FactoryA1 implements Factory<ImplementationA1> {

    @Override
    public <E> E create(Object[] parameters) 
            throws DependencyException {
    
        InterfaceB b;
        InterfaceC c;
        
        try {
            b = (InterfaceB) parameters[0];
            c = (InterfaceC) parameters[1];
        } catch(ClassCastException | ArrayIndexOutOfBoundsException ex){
            throw new DependencyException(ex);
        }
        
        return (E) new ImplementationA1(b, c);
        
    }
    
}
