/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorys_1;

import simple.Factory;
import common.DependencyException;

import implement.ImplementationC1;



/**
 *
 * @author eah1
 */
public class FactoryC1 implements Factory{

    @Override
    public ImplementationC1 create(Object[] parameters) 
            throws DependencyException {
        
        String s;
        
        try {
            s = (String) parameters[0];
        } catch(ClassCastException | ArrayIndexOutOfBoundsException ex){
            throw new DependencyException(ex);
        }
        
        return new ImplementationC1(s);
        
    }
    
}
